package organicsService;

import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;
import organicsUtil.BondType;

//同分异构体
public class IsomersService {
	
	public static  String[]  isomersMoleFormula(String moleFormula) {
		
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
