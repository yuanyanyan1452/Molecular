package organicsService;
import java.util.LinkedList;

import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;

import java.util.HashMap;
import organicsUtil.BondType;

public class OHService implements TransformService {
	public LinkedList<String> transformMoleFormula(String moleFormula) {
		LinkedList<Integer>numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		LinkedList<String>list=new LinkedList<String>();
		HashMap<Integer,Integer>map=new HashMap<Integer,Integer>();
		//饱和醇
		if((cNumber*2+2)==hNumber&&oNumber==1) {
			//先分配碳碳键
			for(int i=1;i<=cNumber-1;i++) {
				list.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
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
						list.add("C"+i+"H"+BondType.CHTeSingleBond);
						hNumber--;
					}else{
						list.add("C"+i+"o1"+BondType.COTeSingleBond);
						list.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.OH));
						break;
					}
					map.put(i, map.get(i)-1);
				}
			}
			return list;
		}else if(cNumber*2==hNumber&&oNumber==1&&cNumber>=2) {//不饱和度为1的醇
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1)list.add("C1C2"+BondType.CC120DoubleBond);
				else if(i==2)list.add("C2C3"+BondType.CC120SingleBond);
				else list.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
			}
			//分配碳上剩下的空闲键
			for(int i=1;i<=cNumber;i++) {
				if(i==1)map.put(i, 2);
				else if(i==2&&i==cNumber)map.put(i,2);
				else if(i==cNumber) map.put(i, 3);
				else map.put(i, 2);
			}
			//分配氢键和羟基
			list.addAll(dispatcherHO(map,cNumber,hNumber));
			return list;
		}else if((cNumber*2-6)==hNumber&&oNumber==1) {//芳香醇
			list.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing));
			list.add("c1o1CO120SingleBond");
			list.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.OH));
			for(int i=2;i<=6;i++) {
				list.add("c"+i+"h"+BondType.CH120SingleBond);
			}
			return list;
		}
		return null;
	}
	//烯类（不饱和度为1）的分配氢键和羟基
	public static LinkedList<String> dispatcherHO(HashMap<Integer,Integer> map,int cNumber,int hNumber){
		LinkedList<String> list=new LinkedList<String>();
		//分配氢键(记得留一个氢给羟基)
		for(int i=1;i<=cNumber;i++) {
			while(hNumber>1&&map.get(i)>0) {
				if(i==1||i==2) {
					list.add("C"+i+"H"+BondType.CH120SingleBond);
				}else {
					list.add("C"+i+"H"+BondType.CHTeSingleBond);
				}
				map.put(i, map.get(i)-1);
				hNumber--;
			}
		}
		//分配羟基
		if(map.get(2)>0)list.add("C"+cNumber+"o1"+BondType.CO120SingleBond);
		else list.add("C"+cNumber+"o1"+BondType.COTeSingleBond);
		list.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.OH));
		return list;
	}
	
}
