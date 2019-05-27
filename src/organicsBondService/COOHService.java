package organicsBondService;

import java.util.LinkedList;

import organicsUtil.BondType;
import organicsUtil.FuncGroupType;
import organicsUtil.GetFuncGroupStrFormula;
import organicsUtil.InputMoleFormula;
/*
 * 酸
 */
public class COOHService implements TransformService{

	@Override
	public LinkedList<String> transformMoleFormula(String moleFormula) {
		LinkedList<String> bonds=new LinkedList<String>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		if(oNumber==2) {
			if(cNumber*2==hNumber) {//连烷烃
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				for(int i=1;i<=cNumber;i++) {
					if(i==1&&i==cNumber)bonds.add("C"+i+" H4 "+BondType.CH120SingleBond);
					else if(i!=1&&i==cNumber) {
						for(int j=0;j<3;j++)bonds.add("C"+i+" H"+(2*i+j)+" "+BondType.CHTeSingleBond);
					}else if(i!=1) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H"+(2*i+j)+" "+BondType.CHTeSingleBond);
					}
				}
				bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.COOH));
			}else if((cNumber*2-2)==hNumber) {//连烯烃
				for(int i=cNumber;i>=2;i--) {
					if(i==cNumber)bonds.add("C"+i+" C"+(i-1)+" "+BondType.CC120SingleBond);
					else if(i==cNumber-2)bonds.add("C"+i+" C"+(i-1)+" "+BondType.CC120SingleBond);
					else if(i==cNumber-1)bonds.add("C"+i+" C"+(i-1)+" "+BondType.CC120DoubleBond);
					else bonds.add("C"+i+" C"+(i-1)+" "+BondType.CCTeSingleBond);
				}
				int hCount=1;
				for(int i=1;i<=cNumber;i++) {
					if(i==1) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H"+(hCount++)+" "+BondType.CH120SingleBond);
					}else if(i==2)bonds.add("C"+i+" H"+(hCount++)+" "+BondType.CH120SingleBond);
					else if(i!=cNumber) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H"+(hCount++)+" "+BondType.CHTeSingleBond);
					}
				}
				bonds.add("C"+cNumber+" O1 "+BondType.CO120DoubleBond);
				bonds.add("C"+cNumber+" O2 "+BondType.CO120SingleBond);
				bonds.add("O2 h1 "+BondType.OH90Bond);
			}else if((cNumber*2-4)==hNumber) {//连炔烃
				bonds.add("#一元酸");
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("c"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else if(i==cNumber-1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180TripleBond);
					else if(i==cNumber-2)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				for(int i=1;i<=cNumber;i++) {
					if(i==cNumber)bonds.add("C"+i+" H "+BondType.CH180SingleBond);
					else if(i!=1&&i!=cNumber-1) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-8)==hNumber&&cNumber>=7) {//连苯环
				bonds.add("#一元酸");
				bonds.clear();
				bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing));
				for(int i=6;i<=cNumber-1;i++) {
					if(i==6)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("C"+cNumber+" O1 "+BondType.CO120DoubleBond);
				bonds.add("C"+cNumber+" O2 "+BondType.CO120SingleBond);
				bonds.add("O2 H "+BondType.OH90Bond);
				for(int i=1;i<=cNumber-1;i++) {
					if(i<=5)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					else if(i!=6) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}

		}
		return bonds;
	}

}
