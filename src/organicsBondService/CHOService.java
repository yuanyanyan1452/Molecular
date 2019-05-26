package organicsBondService;

import java.util.LinkedList;

import organicsUtil.BondType;
import organicsUtil.FuncGroupType;
import organicsUtil.GetFuncGroupStrFormula;
import organicsUtil.InputMoleFormula;
/*
 * 一元醛
 * 二元醛
 */
public class CHOService implements TransformService{
	@Override
	public LinkedList<String> transformMoleFormula(String moleFormula) {
		//脂肪族的醛
		LinkedList<Integer> list=InputMoleFormula.getNumber(moleFormula);
		LinkedList<String> bonds=new LinkedList<String>();
		int cNumber=list.get(0);
		int hNumber=list.get(1);
		int oNumber=list.get(2);
		if(oNumber==1) {//一元醛
		if(cNumber*2==hNumber) {//该醛基连烷烃
			int hNum=2;
			for(int i=cNumber;i>=2;i--) {
				if(i==2)bonds.add("C"+i+" C1 "+BondType.CCTeSingleBond);
				else bonds.add("C"+i+" C"+(i-1)+" "+BondType.CCTeSingleBond);
			}
			for(int i=1;i<=cNumber;i++) {
				if(i==1&&i==cNumber) {
					bonds.add("C"+i+" H"+(hNum++)+" "+BondType.CH120SingleBond);
				}else if(i==1&&i!=cNumber) {
				}else if(i==cNumber){
					for(int j=0;j<3;j++)bonds.add("C"+i+" H"+(hNum++)+" "+BondType.CHTeSingleBond);
				}else {
					for(int j=0;j<2;j++)bonds.add("C"+i+" H"+(hNum++)+" "+BondType.CHTeSingleBond);
				}
			}
			bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.CHO));
			return bonds;
		}else if((cNumber*2)-2==hNumber&&cNumber>=3) {//该醛基连烯烃
			int hNum=1;
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1)bonds.add("c"+i+" C"+(i)+" "+BondType.CC120DoubleBen);
				else if(i==2&&i==cNumber-1)bonds.add("C"+(i-1)+" C"+i+" "+BondType.CC120SingleBond);
				else if(i==2&&i!=cNumber-1)bonds.add("C"+(i-1)+" C"+(i)+" "+BondType.CC120SingleBond);
				else if(i==cNumber-1){
					bonds.add("C"+(i-1)+" C"+i+" "+BondType.CCTeSingleBond);
				}else bonds.add("C"+(i-1)+" C"+(i)+" "+BondType.CCTeSingleBond);
			}
			bonds.add("C"+(cNumber-1)+" o1 "+BondType.CO120DoubleBond);
			bonds.add("C"+(cNumber-1)+" h1 "+BondType.CH120SingleBond);
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1) {
					for(int j=0;j<2;j++)bonds.add("c"+i+" H"+(hNum++)+" "+BondType.CH120SingleBond);
				}else if(i==2)bonds.add("C"+(i-1)+" H"+(hNum++)+" "+BondType.CH120SingleBond);
				else {
					for(int j=0;j<2;j++)bonds.add("C"+(i-1)+" H"+(hNum++)+" "+BondType.CHTeSingleBond);
				}
			}
			return bonds;
		}else if((cNumber*2-4)==hNumber&&cNumber>=3) {//该醛基连炔烃
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180TripleBond);
				else if(i==2&&i==cNumber-1)bonds.add("C"+i+" c1 "+BondType.CC180SingleBond);
				else if(i==2&&i!=cNumber-1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180SingleBond);
				else if(i==cNumber-1) {
					bonds.add("C"+i+" c1 "+BondType.CCTeSingleBond);
				}else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
			}
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1)bonds.add("C"+i+" H "+BondType.CH180SingleBond);
				else if(i==2)continue;
				else for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
			}
			bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.CHO));
			return bonds;
		}else if((cNumber*2-8)==hNumber&&cNumber>=7) {//芳香酚(一个苯环一个醛基)//该醛基连苯环
			bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing));
			for(int i=6;i<=cNumber;i++) {
				if(i==6)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
				else if(i==cNumber)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
				else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
			}
			bonds.add("C"+cNumber+" O1 "+BondType.CO120DoubleBond);
			for(int i=1;i<=cNumber-1;i++) {
				if(i<=5)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
				else if(i!=6) {
					for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}
			}
			return bonds;
		}
		}else if(oNumber==2&&cNumber>=2) {//二元醛
			
			if((cNumber*2-2)==hNumber) {//中间连烷烃
				if(cNumber==2) {
					bonds.add("c1 c2 "+BondType.CC120SingleBond);
				}else {
					for(int i=1;i<=cNumber-2;i++) {
						if(i==cNumber-2) {
							bonds.add("C"+i+" c2 "+BondType.CC120SingleBond);
						}else {
							bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
						}
						bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
						bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
					bonds.add("c1 C1 "+BondType.CC120SingleBond);
				}
			}else if((cNumber*2-4)==hNumber&&cNumber>=4) {//中间连烯烃
				for(int i=1;i<=cNumber-2;i++) {
					if(i==1||i==2)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					else {
						bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
						bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
				if(cNumber==4) {
					bonds.add("c1 C1 "+BondType.CC120SingleBond);
					bonds.add("C1 C2 "+BondType.CC120DoubleBond);
					bonds.add("C2 c2 "+BondType.CC120SingleBond);
				}else {
					for(int i=1;i<=cNumber-2;i++) {
						if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120DoubleBond);
						else if(i==2)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
						else if(i==cNumber-2)bonds.add("C"+i+" c2 "+BondType.CC120SingleBond);
						else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
					}
					bonds.add("c1 C1 "+BondType.CC120SingleBond);
				}
			}else if((cNumber*2-6)==hNumber&&cNumber>=4) {//中间连炔烃
				if(cNumber==4) {
					bonds.add("c1 C1 "+BondType.CC180SingleBond);
					bonds.add("C1 C2 "+BondType.CC180TripleBond);
					bonds.add("C2 c2 "+BondType.CC180SingleBond);
				}else {
					bonds.add("c1 C1 "+BondType.CC180SingleBond);
					for(int i=1;i<=cNumber-2;i++) {
						if(i==1) {
							bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180TripleBond);
						}else if(i==2)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180SingleBond);
						else if(i==cNumber-2) {
							bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
							bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
							bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
						}else {
							bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
							bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
							bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
						}
					}
				}
			}else if((cNumber*2-10)==hNumber&&cNumber>=8) {////中间连苯环
				bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing));
				if(cNumber==8) {
					for(int i=1;i<=6;i++) {
						if(i==2)bonds.add("c1 C2 "+BondType.CC120SingleBond);
						else if(i==6)bonds.add("C"+i+" c2 "+BondType.CC120SingleBond);
						else bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					}
				}else {
					for(int i=1;i<=6;i++) {
						if(i==2)bonds.add("c1 C2 "+BondType.CC120SingleBond);
						else if(i==6)bonds.add("C"+i+" C7 "+BondType.CC120SingleBond);
						else bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					}
					for(int i=7;i<=cNumber-2;i++) {
						if(i==cNumber-2) {
							bonds.add("C"+i+" c2 "+BondType.CC120SingleBond);
						}else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
						bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
						bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}
			if(!bonds.isEmpty())bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.CHO2));
			return bonds;
		}
		return bonds;
	}
	
}
