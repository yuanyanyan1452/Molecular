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
	public static String[] getFuncGroupStrFormula(FuncGroupType funcGroup) {
		LinkedList<String> list=new LinkedList<String>();
		//甲基的结构式，碳原子上面还剩一个可以安装其他原子的单键
		if(funcGroup.equals(FuncGroupType.Methyl)) {
			list.add("c"+sequenceC+"h"+sequenceH+BondType.CHTeBond);
			list.add("c"+sequenceC+"h"+(++sequenceH)+BondType.CHTeBond);
			list.add("c"+sequenceC+"h"+(++sequenceH)+BondType.CHTeBond);
			sequenceH++;
			sequenceC++;
			String[] res=new String[list.size()];
			return list.toArray(res);
		}
		//苯环的结构式，六个碳原子上面都还剩一个可以安装其他原子的单键
		if(funcGroup.equals(FuncGroupType.BenzeneRing)) {
			for(int i=1+sequenceC;i<=6+sequenceC;i++) {
				if(i==6+sequenceC) {
					list.add("c"+i+"c1"+BondType.CC120SingleBond);
					continue;
				}
				if(i%2==1) {
					list.add("c"+i+"c"+(i+1)+BondType.CCDoubleBen);
				}else {
					list.add("c"+i+"c"+(i+1)+BondType.CC120SingleBond);
				}
			}
			sequenceC+=6;
			String[] res=new String[list.size()];
			return list.toArray(res);
		}
		//羟基
		if(funcGroup.equals(FuncGroupType.OH)) {
			list.add("o"+(sequenceO)+"h"+(sequenceH)+BondType.OHBond);
			sequenceO++;sequenceH++;
			String[] res=new String[list.size()];
			return list.toArray(res);
		}
		//醛基
		if(funcGroup.equals(FuncGroupType.CHO)) {
			list.add("c"+(sequenceC)+"o"+(sequenceO)+BondType.CODouble120Bond);
			list.add("c"+(sequenceC)+"h"+(sequenceH)+BondType.CH120Bond);
			sequenceC++;
			sequenceO++;
			sequenceH++;
			String[] res=new String[list.size()];
			return list.toArray(res);
		}
		//羧基
		if(funcGroup.equals(FuncGroupType.COOH)) {
			list.add("c"+(sequenceC)+"o"+(sequenceO)+BondType.CODouble120Bond);
			list.add("c"+(sequenceC)+"o"+(sequenceO)+BondType.COSingleBond);
			list.add("o"+(sequenceO)+"h"+(sequenceH)+BondType.OHBond);
			sequenceC++;
			sequenceO++;
			sequenceH++;
			String[] res=new String[list.size()];
			return list.toArray(res);
		}
		return null;
	}
	
}
