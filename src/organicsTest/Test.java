package organicsTest;

import organicsService.InputMoleFormulaService;
import organicsService.OutputStrFormulaService;
import organicsService.IsomersService;
public class Test {
	//test
		public static void main(String[] args) {
			String moleFormula=InputMoleFormulaService.getMoleFormula();
			String[] bonds=IsomersService.isomersMoleFormula(moleFormula);
			OutputStrFormulaService.outputStrFormula(bonds);
		}
}
