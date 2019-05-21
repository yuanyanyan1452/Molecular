package funcGroupUtil;

import organicsUtil.BondType;
import java.util.LinkedList;
/*
 * 得到某个官能团的结构式
 * 
 */
public class GetFuncGroupStrFormula {
	public static LinkedList<String> getFuncGroupStrFormula(FuncGroupType funcGroup) {
		LinkedList<String> list=new LinkedList<String>();
		//甲基的结构式，碳原子上面还剩一个可以安装其他原子的单键
		if(funcGroup.equals(FuncGroupType.Methyl)) {
			int sequenceC=1;
			list.add("c"+sequenceC+" H "+BondType.CHTeSingleBond);
			list.add("c"+sequenceC+" H "+BondType.CHTeSingleBond);
			list.add("c"+sequenceC+" H "+BondType.CHTeSingleBond);
		}
		//苯环的结构式，六个碳原子上面都还剩一个可以安装其他原子的单键
		if(funcGroup.equals(FuncGroupType.BenzeneRing)) {
			int sequenceC=1;
			for(int i=sequenceC;i<=5+sequenceC;i++) {
				if(i==5+sequenceC) {
					list.add("C"+i+" C1 "+BondType.CC120SingleBond);
					continue;
				}
				if(i%2==1) {
					list.add("C"+i+" C"+(i+1)+" "+BondType.CC120DoubleBen);
				}else {
					list.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
				}
			}
		}
		//羟基
		if(funcGroup.equals(FuncGroupType.OH)) {
			int sequenceO=1;
			list.add("o"+(sequenceO)+" H "+BondType.OH90Bond);
		}
		//醛基
		if(funcGroup.equals(FuncGroupType.CHO)) {
			int sequenceC=1;
			int sequenceO=1;
			list.add("c"+(sequenceC)+" o"+(sequenceO)+" "+BondType.CO120DoubleBond);
			list.add("c"+(sequenceC)+" H "+BondType.CH120SingleBond);
		}
		//羧基
		if(funcGroup.equals(FuncGroupType.COOH)) {
			int sequenceC=1;
			int sequenceO=1;
			list.add("c"+(sequenceC)+" o"+(sequenceO)+" "+BondType.CO120DoubleBond);
			list.add("c"+(sequenceC)+" o"+(++sequenceO)+" "+BondType.COTeSingleBond);
			list.add("o"+(sequenceO)+" H "+BondType.OH90Bond);
		}
		return list;
	}
	
}
