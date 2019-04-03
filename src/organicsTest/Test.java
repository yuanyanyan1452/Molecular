package organicsTest;

import organicsService.InputMoleFormula;
import organicsService.OutputStrFormula;
import organicsService.ServiceController;
public class Test {
	/*
	 * 测试
	 * 获得输入的分子式
	 * 根据分子式进行业务逻辑分析获得所有键的集合,具体哪一个业务逻辑由serviceController负责调派
	 * 输出键的集合
	 */
		public static void main(String[] args) {
			String moleFormula=InputMoleFormula.getMoleFormula();
			String[] bonds=ServiceController.serviceDispatcher(moleFormula);
			OutputStrFormula.outputStrFormula(bonds);
		}
}
