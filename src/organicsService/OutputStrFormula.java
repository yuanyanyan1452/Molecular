package organicsService;
import java.util.LinkedList;
public class OutputStrFormula {
	/*
	 * 输出有机物所有的键
	 * @param:String[] bonds
	 */
	public static void outputStrFormula(LinkedList<String> bonds) {
		for(int i=0;i<bonds.size();i++) {
			System.out.println(bonds.get(i));
		}
	}
}
