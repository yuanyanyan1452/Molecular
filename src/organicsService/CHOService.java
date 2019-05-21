package organicsService;

import java.util.LinkedList;

import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;
import organicsUtil.BondType;
/*
 * 醛
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
		if(cNumber*2==hNumber&&oNumber==1) {
			for(int i=1;i<=cNumber-1;i++) {
				if(i==cNumber-1)bonds.add("C"+i+" c1 "+BondType.CCTeSingleBond);
				else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
			}
			for(int i=1;i<=cNumber;i++) {
				if(i==1&&i==cNumber) {
					bonds.add("c"+i+" H "+BondType.CH120SingleBond);
				}else if(i==1&&i!=cNumber) {
					for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}else if(i==cNumber){
					break;
				}else {
					for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}
			}
			bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.CHO));
			return bonds;
		}else if((cNumber*2)-2==hNumber&&cNumber>=3) {
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120DoubleBen);
				else if(i==2&&i==cNumber-1)bonds.add("C"+i+" c1 "+BondType.CC120SingleBond);
				else if(i==2&&i!=cNumber-1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
				else if(i==cNumber-1){
					bonds.add("C"+i+" c1 "+BondType.CCTeSingleBond);
				}else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
			}
			bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.CHO));
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1) {
					for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
				}else if(i==2)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
				else {
					for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}
			}
			return bonds;
		}else if((cNumber*2-4)==hNumber&&cNumber>=3) {
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
		}else if((cNumber*2-8)==hNumber&&cNumber>=7) {//芳香酚(一个苯环一个醛基)
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
		return bonds;
	}
	
}
