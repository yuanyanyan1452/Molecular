package organicsService;

public class TolueneService {

	//test
	public static void main(String[] args) {
		String moleFormula=InputMoleFormulaService.getMoleFormula();
		String[] bonds=getTolueneService(moleFormula);
		OutputStrFormulaService.outputStrFormula(bonds);
	}
	/*
	 * 甲苯-不饱和度为4
	 */
	public static String[] getTolueneService(String moleFormula) {
		int[] result=InputMoleFormulaService.getNumber(moleFormula);
		int cNumber=result[0];
		int hNumber=result[1];
		//不饱和度为4-一个苯环的不饱和度
		if((cNumber*2+2-hNumber)/2==4) {
			
		}
		return null;
	}
}
