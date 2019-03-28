package organicsUtil;

public enum BondType {
	CHTeBond,//C和H单键，键角为109度28分，空间构型为正四面体
	CH120Bond,//C和H单键，键角为120度，平面型
	CH180Bond,//C和H单键，键角为180度，直线型
	CCSingleBond,//C和C单键,键角不定可以随意旋转
	CC180SingleBond,//C和C单键，键角为180度，直线型
	CC120SingleBond,//C和C120度单键
	CCDoubleBond,//C和C双键
	CCTripleBond,//C和C三键
	CCDoubleBen//C和C在苯环上特殊的双键
}
