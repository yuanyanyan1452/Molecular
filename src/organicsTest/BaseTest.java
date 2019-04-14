package organicsTest;
import java.util.LinkedList;
import organicsService.ServiceController;
public class BaseTest {
	/*
	 * 测试
	 * 获得输入的分子式
	 * 根据分子式进行业务逻辑分析获得所有键的集合,具体哪一个业务逻辑由serviceController负责调派
	 * 输出键的集合
	 */
		public static LinkedList<String> test(String moleFormula) {
			LinkedList<String> bonds=ServiceController.serviceDispatcher(moleFormula);
			for(int i=0;i<bonds.size();i++)System.out.println(bonds.get(i));
			return bonds;
		}
		//debug entrance
		public static void main(String[] args) {
			LinkedList<String> bonds=ServiceController.serviceDispatcher("c10h12o");
			for(int i=0;i<bonds.size();i++)System.out.println(bonds.get(i));
		}
}
