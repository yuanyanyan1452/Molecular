package organicsService;

import java.util.LinkedList;

import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;
import organicsUtil.BondType;

/*
 * 酮基
 */
public class COService implements TransformService{

	@Override
	public LinkedList<String> transformMoleFormula(String moleFormula) {
		LinkedList<String> bonds=new LinkedList<String>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		if(oNumber==1&&cNumber>=3) {
			if(cNumber*2==hNumber) {
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1||i==2)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("C2"+" O1 "+BondType.CO120DoubleBond);
				for(int i=1;i<=cNumber;i++) {
					if(i==1||i==cNumber) {
						for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}else if(i!=2){
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-2)==hNumber&&cNumber>=4) {
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120DoubleBond);
					else if(i==2||i==3) {
						bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					}else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("C3"+" O1 "+BondType.CO120DoubleBond);
				for(int i=1;i<=cNumber;i++) {
					if(i==1) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					}else if(i==2)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					else if(i==cNumber) {
						for(int j=0;j<3;j++) {
							bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
						}
					}else if(i!=3){
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-4)==hNumber&&cNumber>=4) {
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180TripleBond);
					else if(i==2)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180SingleBond);
					else if(i==3)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("C3 O1 "+BondType.CO120DoubleBond);
				for(int i=1;i<=cNumber;i++) {
					if(i==1)bonds.add("C"+i+" H "+BondType.CH180SingleBond);
					else if(i==cNumber) {
						for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}else if(i!=2&&i!=3) {
						for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-8)==hNumber&&cNumber>=8) {
				bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing));
				for(int i=6;i<=cNumber-1;i++) {
					if(i==6)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else if(i==7)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
					else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				bonds.add("C7 O1 "+BondType.CO120DoubleBond);
				for(int i=1;i<=cNumber;i++) {
					if(i==1||i==2||i==3||i==4||i==5)bonds.add("C"+i+" H "+BondType.CH120SingleBond);
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
