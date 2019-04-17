package organicsUtil;
/*
 * a原子-b原子-键角-键的类型
 * 键角：Ran自由旋转-Te是109度28分-120-180
 * 键的类型：单键双键（正常双键DoubleBond和苯环上的双键DoubleBen）三键
 */
public enum BondType {
	
	CCRanSingleBond,//C和C单键,键角不定可以随意旋转
	CC180SingleBond,//C和C单键，键角为180度，直线型
	CC120SingleBond,//C和C120度单键，平面型
	CCTeSingleBond,//C和C109度28分单键，空间构型为正四面体
	
	CC120DoubleBond,//C和C双键
	CC180TripleBond,//C和C三键
	CC120DoubleBen,//C和C在苯环上特殊的双键,
	
	CHTeSingleBond,//C和H单键，键角为109度28分，空间构型为正四面体
	CH120SingleBond,//C和H单键，键角为120度，平面型
	CH180SingleBond,//C和H单键，键角为180度，直线型
	
	CFTeSingleBond,//C和F单键，键角为109度28分，空间构型为正四面体
	CClTeSingleBond,
	CBrTeSingleBond,
	CITeSingleBond,
	CF120SingleBond,//C和F单键，键角为120分，空间构型为平面型
	CCl120SingleBond,
	CBr120SingleBond,
	CI120SingleBond,
	CF180SingleBond,//C和F单键，键角为180分，空间构型为直线型
	CCl180SingleBond,
	CBr180SingleBond,
	CI180SingleBond,
	//羟基，结构为直角型
	OH90Bond,
	//醛基，结构为正三角型
	CO120DoubleBond,
	//羧基
	COTeSingleBond,
	CO120SingleBond,
	
	CO180SingleBond,
}
