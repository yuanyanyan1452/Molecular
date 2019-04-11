package organicsService;
import java.util.LinkedList;
import java.util.HashMap;
import organicsUtil.BondType;
/*
 * 只含碳氢的化合物，或者简称为烃
 * 分为烷烃烯烃炔烃芳香烃
 */
public class HydrocarbonService implements TransformService{
	public  LinkedList<String> transformMoleFormula(String moleFormula) {
		LinkedList<String> bonds=new LinkedList<String>();
		HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		//烷烃
		if((cNumber*2+2)==hNumber) {
			//先分配碳碳键
			for(int i=1;i<=cNumber-1;i++) {
				bonds.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
			}
			//再分配各个碳上面剩下的空闲的键
			for(int i=1;i<=cNumber;i++) {
				if(i==1&&i==cNumber)map.put(i, 4);
				else if(i==1&&i<cNumber)map.put(i,3);
				else if(i==cNumber)map.put(i, 3);
				else map.put(i, 2);
			}
			//分配氢键
			for(int i=1;i<=cNumber;i++) {
				while(map.get(i)>0) {
					bonds.add("C"+i+"H"+BondType.CHTeSingleBond);
					map.put(i, map.get(i)-1);
				}
			}
		}else if(cNumber*2==hNumber) {//一烯烃 
			//先分配碳碳键
			for(int i=1;i<=cNumber-1;i++) {
				if(i==1)bonds.add("C"+i+"C"+(i+1)+BondType.CC120DoubleBond);
				else if(i==2) {
					bonds.add("C"+i+"C"+(i+1)+BondType.CC120SingleBond);
				}else {
					bonds.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
				}
			}
			for(int i=1;i<=cNumber;i++) {
				if(i==1)map.put(i, 2);
				else if(i==2&&i==cNumber)map.put(i, 2);
				else if(i==2&&i<cNumber)map.put(i, 1);
				else if(i==cNumber)map.put(i,3);
				else map.put(i, 2);
			}
			for(int i=1;i<=cNumber;i++) {
				while(map.get(i)>0) {
					if(i==1||i==2) {
						bonds.add("C"+i+"H"+BondType.CH120SingleBond);
					}else {
						bonds.add("C"+i+"H"+BondType.CHTeSingleBond);
					}
					map.put(i, map.get(i)-1);
				}
			}
		}else if((cNumber*2-2)==hNumber) {//一炔烃
			
		}
		return bonds;
	}
	
}
