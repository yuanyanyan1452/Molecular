package funcGroupUtil;

import organicsUtil.BondType;

/*
 * 得到某个官能团的结构式
 * 
 */
public class GetFuncGroupStrFormula {
	public static int sequenceC=1;
	public static int sequenceH=1;
	public static String[] getFuncGroupStrFormula(FuncGroupType funcGroup) {
		//甲基的结构式
		if(funcGroup.equals(FuncGroupType.Methyl)) {
			String[] res=new String[3];
			res[0]="c"+sequenceC+"h"+sequenceH+BondType.CHTeBond;
			res[1]="c"+sequenceC+"h"+(++sequenceH)+BondType.CHTeBond;
			res[2]="c"+sequenceC+"h"+(++sequenceH)+BondType.CHTeBond;
			sequenceH++;
			sequenceC++;
			return res;
		}
		return null;
	}
}
