package organicsService;
import java.util.LinkedList;

import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;

import java.util.HashMap;
import organicsUtil.BondType;

public class OHService implements TransformService {
	public String[] transformMoleFormula(String moleFormula) {
		int[] res=InputMoleFormula.getNumber(moleFormula);
		int cNumber=res[0];
		int hNumber=res[1];
		int oNumber=res[2];
		LinkedList<String>list=new LinkedList<String>();
		if((cNumber*2+2)==hNumber&&oNumber==1) {
			//先分配碳碳键
			for(int i=1;i<=cNumber-1;i++) {
				list.add("C"+i+"C"+(i+1)+BondType.CCSingleBond);
			}
			HashMap<Integer,Integer>map=new HashMap<Integer,Integer>();
			//再要分配碳上剩下的键
			for(int i=1;i<=cNumber;i++) {
				if(i==1||i==cNumber)map.put(i, 3);
				else {
					map.put(i,2);
				}
			}
			//再分配氢和羟基
			for(int i=1;i<=cNumber;i++) {
				while(map.get(i)>0) {
					if(hNumber>1) {
						list.add("C"+i+"H"+BondType.CHTeBond);
						hNumber--;
					}else{
						list.add("C"+i+"o1"+BondType.COSingleBond);
						String[] temp=GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.OH);
						for(String str:temp) {
							list.add(str);
						}
						break;
					}
					map.put(i, map.get(i)-1);
				}
			}
			String[] array=new String[list.size()];
			return list.toArray(array);
		}
		return null;
	}
}
