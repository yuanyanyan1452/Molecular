package organicsUtil;

public enum BondType {
	
	CCSingleBond,//C和C单键,键角不定可以随意旋转
	CC180SingleBond,//C和C单键，键角为180度，直线型
	CC120SingleBond,//C和C120度单键，平面型
	CCTeSingleBond,//C和C109度28分单键，空间构型为正四面体
	
	CCDoubleBond,//C和C双键
	CCTripleBond,//C和C三键
	CCDoubleBen,//C和C在苯环上特殊的双键,
	
	CHTeBond,//C和H单键，键角为109度28分，空间构型为正四面体
	CH120Bond,//C和H单键，键角为120度，平面型
	CH180Bond,//C和H单键，键角为180度，直线型
	
	CFTeBond,//C和F单键，键角为109度28分，空间构型为正四面体
	CClTeBond,
	CBrTeBond,
	CITeBond,
	CF120Bond,//C和F单键，键角为120分，空间构型为平面型
	CCl120Bond,
	CBr120Bond,
	CI120Bond,
	CF180Bond,//C和F单键，键角为180分，空间构型为直线型
	CCl180Bond,
	CBr180Bond,
	CI180Bond,
	//羟基，结构为直角型
	OHBond,
	//醛基，结构为正三角型
	CODouble120Bond,
	//羧基
	COSingleBond,
	CO120SingleBond,
}
