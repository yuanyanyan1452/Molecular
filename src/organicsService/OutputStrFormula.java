package organicsService;

public class OutputStrFormula {
	/*
	 * 输出有机物所有的键
	 * @param:String[] bonds
	 */
	public static void outputStrFormula(String[] bonds) {
		for(int i=0;bonds!=null&&i<bonds.length;i++) {
			System.out.println(bonds[i]);
		}
	}
}
