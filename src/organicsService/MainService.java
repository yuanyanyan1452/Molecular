package organicsService;
import organicsUtil.BondType;;

public class MainService {
	//test
	public static void main(String[] args) {
		String moleFormula=InputMoleFormulaService.getMoleFormula();
		String[] bonds=transformMoleFormula(moleFormula);
		OutputStrFormulaService.outputStrFormula(bonds);
	}
	public static String[] transformMoleFormula(String moleFormula) {
		//对于CH4
		if(moleFormula.matches("[Cc][Hh](4)")) {
			String[] bonds=new String[4];
			bonds[0]="C1H1"+BondType.CHTeBond;bonds[1]="C1H2"+BondType.CHTeBond;bonds[2]="C1H3"+BondType.CHTeBond;bonds[3]="C1H4"+BondType.CHTeBond;
			return bonds;
		}
		//对于C2H4
		if(moleFormula.matches("[Cc](2)[Hh](4)")) {
			String[] bonds=new String[5];
			bonds[0]="C1C2"+BondType.CCDoubleBond;bonds[1]="C1H1"+BondType.CH120Bond;bonds[2]="C1H2"+BondType.CH120Bond;bonds[3]="C2H3"+BondType.CH120Bond;
			bonds[4]="C2H4"+BondType.CH120Bond;
			return bonds;
		}
		//对于C2H2
		if(moleFormula.matches("[Cc](2)[Hh](2)")){
			String[] bonds=new String[3];
			bonds[0]="C1C2"+BondType.CCTripleBond;bonds[1]="C1H1"+BondType.CH180Bond;bonds[2]="C2H2"+BondType.CH180Bond;
			return bonds;
		}
		//只含c和h的
		if(moleFormula.matches("[Cc][1-9]{1,}[0-9]{0,}[Hh][1-9][0-9]{0,}")) {
			int[] result=InputMoleFormulaService.getNumber(moleFormula);
			int cNumber=result[0];
			int hNumber=result[1];
			//烷类C(n)H(2n+2)
			if(hNumber==(cNumber*2+2)) {
				String[] bonds=new String[3*cNumber+1];
				for(int i=1;i<=cNumber-1;i++) {
					bonds[i-1]="C"+i+"C"+(i+1)+BondType.CCSingleBond;
				}
				for(int i=1;i<=cNumber;i++) {
					bonds[cNumber+2*i-3]="C"+i+"H"+(2*i-1)+BondType.CHTeBond;
					bonds[cNumber+2*i-2]="C"+i+"H"+(2*i)+BondType.CHTeBond;
				}
				bonds[3*cNumber-1]="C1"+"H"+(2*cNumber+1)+BondType.CHTeBond;
				bonds[3*cNumber]="C"+cNumber+"H"+(2*cNumber+2)+BondType.CHTeBond;
				return bonds;
			}else if(hNumber==cNumber*2) {//含有一个不饱和键的烯类C（n)H(2n)-乙烯单独处理
				String[] bonds=new String[3*cNumber-1];
				bonds[0]="C1C2"+BondType.CCDoubleBond;
				int i=1;
				for(;i<cNumber-1;i++) {
					if(i==1) {
						bonds[i]="C"+(i+1)+"C"+(i+2)+BondType.CC120SingleBond;
					}
					bonds[i]="C"+(i+1)+"C"+(i+2)+BondType.CCSingleBond;
				}
				bonds[i]="C1H1"+BondType.CH120Bond;
				bonds[++i]="C1H2"+BondType.CH120Bond;
				bonds[++i]="C2H3"+BondType.CH120Bond;
				int start=3;
				int hStart=4;
				i++;
				for(;i<bonds.length;i++) {
					bonds[i]="C"+start+"H"+hStart+BondType.CHTeBond;
					bonds[++i]="C"+start+"H"+(++hStart)+BondType.CHTeBond;
					if(i==bonds.length-2) {
						bonds[++i]="C"+start+"H"+(++hStart)+BondType.CHTeBond;
						break;
					}else {
						start++;hStart++;
					}
				}
				return bonds;
			}else if(hNumber==cNumber*2-2) {//含一个不饱和键的炔类C(n)H(2n-2)
				String[] bonds=new String[3*cNumber-3];
				bonds[0]="C1C2"+BondType.CCTripleBond;
				bonds[1]="C2C3"+BondType.CC180SingleBond;
				int start=2;
				for(;start<cNumber-1;start++) {
					bonds[start]="C"+(start+1)+"C"+(start+2)+BondType.CCSingleBond;
				}
				bonds[start]="C1H1"+BondType.CH180Bond;
				start++;
				int cStart=3;
				int hStart=2;
				for(;start<bonds.length;start++) {
					bonds[start]="C"+cStart+"H"+hStart+BondType.CHTeBond;
				}
				return bonds;
			}
			
		}
		return null;
	}
	
}
