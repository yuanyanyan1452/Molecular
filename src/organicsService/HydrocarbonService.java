package organicsService;
import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;
import organicsUtil.BondType;;
/*
 * 只含碳氢的化合物，或者简称为烃
 * 分为烷烃烯烃炔烃芳香烃
 */
public class HydrocarbonService implements TransformService{
	public  String[] transformMoleFormula(String moleFormula) {
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
			int[] result=InputMoleFormula.getNumber(moleFormula);
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
		//芳香烃含苯环的
		if(moleFormula.matches("[Cc](6)[Hh](6)")) {
			//输出苯环
			String[] bonds=new String[12];
			bonds[0]="C1C2"+BondType.CCDoubleBen;bonds[2]="C3C4"+BondType.CCDoubleBen;bonds[4]="C5C6"+BondType.CCDoubleBen;
			bonds[1]="C2C3"+BondType.CC120SingleBond;bonds[3]="C4C5"+BondType.CC120SingleBond;bonds[5]="C6C1"+BondType.CC120SingleBond;
			int cStart=1;
			int hStart=1;
			for(int i=6;i<bonds.length;i++) {
				bonds[i]="C"+cStart+"H"+hStart+BondType.CH120Bond;
				cStart++;hStart++;
			}
			return bonds;
		}
		//甲苯
		if(moleFormula.matches("[Cc](7)[Hh](8)")) {
			String[] bonds=new String[15];
			bonds[0]="C1C2"+BondType.CCDoubleBen;bonds[2]="C3C4"+BondType.CCDoubleBen;bonds[4]="C5C6"+BondType.CCDoubleBen;
			bonds[1]="C2C3"+BondType.CC120SingleBond;bonds[3]="C4C5"+BondType.CC120SingleBond;bonds[5]="C6C1"+BondType.CC120SingleBond;
			int cStart=2;
			int hStart=2;
			for(int i=6;i<=10;i++) {
				bonds[i]="C"+cStart+"H"+hStart+BondType.CH120Bond;
				cStart++;hStart++;
			}
			String[] methyl=GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.Methyl);
			bonds[11]="C1"+"c1"+BondType.CC120SingleBond;
			for(int i=0;i<methyl.length;i++) {
				bonds[12+i]=methyl[i];
			}
			return bonds;
		}
		//2-6甲苯
		if(moleFormula.matches("[Cc](8)[Hh](10)||[Cc](9)[Hh](12)||[Cc](10)[Hh](14)||[Cc](11)[Hh](16)||[Cc](12)[Hh](118)")) {
			
		}

		return null;
	}
	
}
