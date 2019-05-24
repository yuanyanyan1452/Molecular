package organicsUtil;
/*
 * 官能团类型
 * 通过官能团获取的原子以小写形式表示
 * 在同一个分子中的相同的官能团会以不同的原子序号来区分
 */
public enum FuncGroupType {
	Methyl,//甲基
	BenzeneRing,//苯环（不是官能团）但可以重用
	OH,//羟基-醇
	CHO,//醛基-醛
	COOH,//羧基-酸
	CHO2,//双醛基
	
}
