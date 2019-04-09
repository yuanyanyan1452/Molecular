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
	public static int times=0;//苯环出现的次数
	public static String[] getFuncGroupStrFormula(FuncGroupType funcGroup) {
		//甲基的结构式，碳原子上面还剩一个可以安装其他原子的单键
		if(funcGroup.equals(FuncGroupType.Methyl)) {
			String[] res=new String[3];
			res[0]="c"+sequenceC+"h"+sequenceH+BondType.CHTeBond;
			res[1]="c"+sequenceC+"h"+(++sequenceH)+BondType.CHTeBond;
			res[2]="c"+sequenceC+"h"+(++sequenceH)+BondType.CHTeBond;
			sequenceH++;
			sequenceC++;
			return res;
		}
		//苯环的结构式，六个碳原子上面都还剩一个可以安装其他原子的单键
		if(funcGroup.equals(FuncGroupType.BenzeneRing)) {
			
			LinkedList<String> list=new LinkedList<String>();
			for(int i=1+times;i<=6+times;i++) {
				if(i==6) {
					list.add("c"+i+"c1"+BondType.CC120SingleBond);
					continue;
				}
				if(i%2==1) {
					list.add("c"+i+"c"+(i+1)+BondType.CCDoubleBen);
				}else {
					list.add("c"+i+"c"+(i+1)+BondType.CC120SingleBond);
				}
			}
			times+=6;
			String[] res=new String[list.size()];
			return list.toArray(res);
		}
		return null;
	}
	
}
