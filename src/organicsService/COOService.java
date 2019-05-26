package organicsService;

import java.util.LinkedList;

import organicsUtil.BondType;
import organicsUtil.FuncGroupType;
import organicsUtil.GetFuncGroupStrFormula;
import organicsUtil.InputMoleFormula;
/*
 * 酯
 */
public class COOService implements TransformService{

	@Override
	public LinkedList<String> transformMoleFormula(String moleFormula) {
		LinkedList<String> bonds=new LinkedList<String>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		if(oNumber==2) {
			if(cNumber*2==hNumber&&cNumber>=3) {
				bonds.add("#一元酯");
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else if(i==2)bonds.add("C"+i+" O1 "+BondType.CO120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("O1 C3 "+BondType.COTeSingleBond);
				bonds.add("C2 O2 "+BondType.CO120DoubleBond);
				for(int i=1;i<=cNumber;i++) {
					if(i==1||i==cNumber) {
						for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}else if(i!=2) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-2)==hNumber&&cNumber>=4) {
				bonds.add("#一元酯");
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120DoubleBond);
					else if(i==2)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else if(i==3)bonds.add("C"+i+" O1 "+BondType.CO120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("O1 C4 "+BondType.COTeSingleBond);
				bonds.add("C3 O2 "+BondType.CO120DoubleBond);
				for(int i=1;i<=cNumber;i++) {
					if(i==1) {
						for(int j=0;j<2;j++) {
							bonds.add("C"+i+" H "+BondType.CH120SingleBond);
						}
					}else if(i==2)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					else if(i==cNumber) {
						for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}else if(i!=3) {
						bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-4)==hNumber&&cNumber>=4) {
				bonds.add("#一元酯");
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180TripleBond);
					else if(i==2)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180SingleBond);
					else if(i==3)bonds.add("C"+i+" O1 "+BondType.CO120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("O1 C4 "+BondType.COTeSingleBond);
				bonds.add("C3 O2 "+BondType.CO120SingleBond);
				for(int i=1;i<=cNumber;i++) {
					if(i==1)bonds.add("C"+i+" H "+BondType.CH180SingleBond);
					else if(i==cNumber) {
						for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}else if(i!=2&&i!=3) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-8)==hNumber&&cNumber>=8) {
				bonds.add("#一元酯");
				bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing));
				for(int i=6;i<=cNumber-1;i++) {
					if(i==6)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else if(i==7)bonds.add("C"+i+" O1 "+BondType.CO120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("O1 C8 "+BondType.CO120SingleBond);
				bonds.add("C7 O2 "+BondType.CO120DoubleBond);
				for(int i=1;i<=cNumber;i++) {
					if(i<=5)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					else if(i==cNumber) {
						for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}else if(i!=6&&i!=7) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}
		}
		
		return bonds;
	}
	
}
