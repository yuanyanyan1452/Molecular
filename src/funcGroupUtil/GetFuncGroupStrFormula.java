package funcGroupUtil;

import organicsUtil.BondType;
import java.util.LinkedList;
/*
 * 得到某个官能团的结构式
 * 
 */
public class GetFuncGroupStrFormula {
	public static int sequenceC=1;
	public static int sequenceH=1;
	public static int sequenceO=1;
	public static LinkedList<String> getFuncGroupStrFormula(FuncGroupType funcGroup) {
		LinkedList<String> list=new LinkedList<String>();
		//甲基的结构式，碳原子上面还剩一个可以安装其他原子的单键
		if(funcGroup.equals(FuncGroupType.Methyl)) {
			list.add("c"+sequenceC+"h"+sequenceH+BondType.CHTeSingleBond);
			list.add("c"+sequenceC+"h"+(++sequenceH)+BondType.CHTeSingleBond);
			list.add("c"+sequenceC+"h"+(++sequenceH)+BondType.CHTeSingleBond);
			sequenceH++;
			sequenceC++;
			return list;
		}
		//苯环的结构式，六个碳原子上面都还剩一个可以安装其他原子的单键
		if(funcGroup.equals(FuncGroupType.BenzeneRing)) {
			for(int i=sequenceC;i<=5+sequenceC;i++) {
				if(i==5+sequenceC) {
					list.add("c"+i+"c1"+BondType.CC120SingleBond);
					continue;
				}
				if(i%2==1) {
					list.add("c"+i+"c"+(i+1)+BondType.CC120DoubleBen);
				}else {
					list.add("c"+i+"c"+(i+1)+BondType.CC120SingleBond);
				}
			}
			sequenceC+=6;
			return list;
		}
		//羟基
		if(funcGroup.equals(FuncGroupType.OH)) {
			list.add("o"+(sequenceO)+"h"+(sequenceH)+BondType.OH90Bond);
			sequenceO++;sequenceH++;
			return list;
		}
		//醛基
		if(funcGroup.equals(FuncGroupType.CHO)) {
			list.add("c"+(sequenceC)+"o"+(sequenceO)+BondType.CO120DoubleBond);
			list.add("c"+(sequenceC)+"h"+(sequenceH)+BondType.CH120SingleBond);
			sequenceC++;
			sequenceO++;
			sequenceH++;
			return list;
		}
		//羧基
		if(funcGroup.equals(FuncGroupType.COOH)) {
			list.add("c"+(sequenceC)+"o"+(sequenceO)+BondType.CO120DoubleBond);
			list.add("c"+(sequenceC)+"o"+(++sequenceO)+BondType.COTeSingleBond);
			list.add("o"+(sequenceO)+"h"+(sequenceH)+BondType.OH90Bond);
			sequenceC++;
			sequenceO++;
			sequenceH++;
			return list;
		}
		return null;
	}
	
}
