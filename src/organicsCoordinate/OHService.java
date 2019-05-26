package organicsCoordinate;
import java.util.LinkedList;
import java.util.HashMap;
import organicsUtil.BondType;
import organicsUtil.FuncGroupType;
import organicsUtil.GetFuncGroupStrFormula;
import organicsUtil.InputMoleFormula;
import organicsUtil.Mole;

public class OHService implements TransformService {
	public LinkedList<Mole> transformMoleFormula(String moleFormula) {
		LinkedList<Integer>numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		LinkedList<Mole>moles=new LinkedList<Mole>();
		//饱和醇
		if((cNumber*2+2)==hNumber&&oNumber==1) {
		}else if(cNumber*2==hNumber&&oNumber==1&&cNumber>=2) {//不饱和度为1的醇
		}else if((cNumber*2-2)==hNumber) {//炔
		}else if((cNumber*2-6)==hNumber&&oNumber==1) {//芳香醇
		}
		return moles;
	}
	//烯类（不饱和度为1）的分配氢键和羟基
	public static LinkedList<String> dispatcherHO(HashMap<Integer,Integer> map,int cNumber,int hNumber){
		LinkedList<String> list=new LinkedList<String>();
		//分配氢键(记得留一个氢给羟基)
		for(int i=1;i<=cNumber;i++) {
			while(hNumber>1&&map.get(i)>0) {
				if(i==1||i==2) {
					list.add("C"+i+" H "+BondType.CH120SingleBond);
				}else {
					list.add("C"+i+" H "+BondType.CHTeSingleBond);
				}
				map.put(i, map.get(i)-1);
				hNumber--;
			}
		}
		//分配羟基
		if(map.get(2)>0)list.add("C"+cNumber+" o1 "+BondType.CO120SingleBond);
		else list.add("C"+cNumber+" o1 "+BondType.COTeSingleBond);
		list.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.OH));
		return list;
	}
	
}
