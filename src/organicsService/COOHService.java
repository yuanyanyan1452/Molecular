package organicsService;

import java.util.LinkedList;

import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;
import organicsUtil.BondType;

public class COOHService implements TransformService{

	@Override
	public LinkedList<String> transformMoleFormula(String moleFormula) {
		LinkedList<String> bonds=new LinkedList<String>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		if(oNumber==2&&cNumber>=3) {
			bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.COOH));
			if(cNumber*2==hNumber) {
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("c"+i+"C"+(i+1)+BondType.CC120SingleBond);
					else bonds.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
				}
				for(int i=1;i<=cNumber;i++) {
					if(i==cNumber) {
						for(int j=0;j<3;j++)bonds.add("C"+i+"H"+BondType.CHTeSingleBond);
					}else if(i!=1) {
						for(int j=0;j<2;j++)bonds.add("C"+i+"H"+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-2)==hNumber) {
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("c"+i+"C"+(i+1)+BondType.CC120SingleBond);
					else if(i==cNumber-2)bonds.add("C"+i+"C"+(i+1)+BondType.CC120SingleBond);
					else if(i==cNumber-1)bonds.add("C"+i+"C"+(i+1)+BondType.CC120DoubleBond);
					else bonds.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
				}
				for(int i=1;i<=cNumber;i++) {
					if(i==cNumber) {
						for(int j=0;j<2;j++)bonds.add("C"+i+"H"+BondType.CH120SingleBond);
					}else if(i==cNumber-1)bonds.add("C"+i+"H"+BondType.CH120SingleBond);
					else if(i!=1) {
						for(int j=0;j<2;j++)bonds.add("C"+i+"H"+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-4)==hNumber) {
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1)bonds.add("c"+i+"C"+(i+1)+BondType.CC120SingleBond);
					else if(i==cNumber-1)bonds.add("C"+i+"C"+(i+1)+BondType.CC180TripleBond);
					else if(i==cNumber-2)bonds.add("C"+i+"C"+(i+1)+BondType.CC180SingleBond);
					else bonds.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
				}
				for(int i=1;i<=cNumber;i++) {
					if(i==cNumber)bonds.add("C"+i+"H"+BondType.CH180SingleBond);
					else if(i!=1&&i!=cNumber-1) {
						for(int j=0;j<2;j++)bonds.add("C"+i+"H"+BondType.CHTeSingleBond);
					}
				}
			}else if((cNumber*2-8)==hNumber&&cNumber>=7) {
				bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing));
				for(int i=1;i<=cNumber-6;i++) {
					if(i==1)bonds.add("c"+i+"C"+(i+1)+BondType.CC120SingleBond);
					else if(i==cNumber-6)bonds.add("C"+i+"c2"+BondType.CC120SingleBond);
					else bonds.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
				}
				int count=3;
				for(int i=1;i<=cNumber;i++) {
					if(i>=2&&i<=cNumber-6) {
						for(int j=0;j<2;j++)bonds.add("C"+i+"H"+BondType.CHTeSingleBond);
					}else if(i>=cNumber-4){
						bonds.add("c"+count+"H"+BondType.CH120SingleBond);
						count++;
					}
				}
			}
		}
		return bonds;
	}

}
