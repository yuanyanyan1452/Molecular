package organicsUtil;
/*
 * 通过BondType解析出键角
 */
public class GetBondAngle {
	//test
	public static void main(String[] args) {
		System.out.println(getBondAngle("CCDoubleBen"));
	}
	public static double getBondAngle(String bondType) {
		switch(bondType) {
		case("CHTeBond"):return 109.5;
		case("CH120Bond"):return 120;
		case("CH180Bond"):return 180;
		case("CCSingleBond"):return 180;//C和C随意旋转的单键暂时设置为180度
		case("CC180SingleBond"):return 180;
		case("CC120SingleBond"):return 120;
		case("CCDoubleBond"):return 120;
		case("CCTripleBond"):return 180;
		case("CCDoubleBen"):return 120;
		default:return 0;
		}
	}
}
