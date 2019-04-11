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
		HashMap<Integer,Integer>map=new HashMap<Integer,Integer>();
		//饱和醇
		if((cNumber*2+2)==hNumber&&oNumber==1) {
			//先分配碳碳键
			for(int i=1;i<=cNumber-1;i++) {
				list.add("C"+i+"C"+(i+1)+BondType.CCSingleBond);
			}
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
		}else if(cNumber*2==hNumber&&oNumber==1) {//不饱和度为1的醇
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1)list.add("C1C2"+BondType.CCDoubleBond);
				else if(i==2)list.add("C2C3"+BondType.CC120SingleBond);
				else list.add("C"+i+"C"+(i+1)+BondType.CCSingleBond);
			}
			//分配碳上剩下的空闲键
			for(int i=1;i<=cNumber;i++) {
				if(i==1)map.put(i, 2);
				else if(i==2&&i==cNumber)map.put(i,2);
				else if(i==cNumber) map.put(i, 3);
				else map.put(i, 2);
			}
			//分配氢键(记得留一个氢给羟基)
			for(int i=1;i<=cNumber;i++) {
				while(hNumber>1&&map.get(i)>0) {
					if(i==1||i==2) {
						list.add("C"+i+"H"+BondType.CH120Bond);
					}else {
						list.add("C"+i+"H"+BondType.CHTeBond);
					}
					map.put(i, map.get(i)-1);
					hNumber--;
				}
			}
			//分配羟基
			if(map.get(2)>0)list.add("C"+cNumber+"o1"+BondType.CO120SingleBond);
			else list.add("C"+cNumber+"o1"+BondType.COSingleBond);
			String[] OH=GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.OH);
			for(String str:OH)list.add(str);
			String[] bonds=new String[list.size()];
			return list.toArray(bonds);
		}
		return null;
	}
}
