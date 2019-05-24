package organicsController;
import java.util.LinkedList;
import organicsUtil.Mole;
public class BaseTest {
	/*
	 * 测试
	 * 获得输入的分子式
	 * 根据分子式进行业务逻辑分析获得所有键的集合,具体哪一个业务逻辑由serviceController负责调派
	 * 输出原子的集合
	 */
		//debug entrance
		public static void main(String[] args) {
			String[] input= {"C3H6O"};
			for(String str:input) {
				LinkedList<String> bonds=organicsService.ServiceController.serviceDispatcher(str);
				LinkedList<Mole> moles=ServiceController.serviceDispatcher(str);
				for(int i=0;i<moles.size();i++) {
					System.out.println(moles.get(i).toString());
				}
				for(int i=0;i<bonds.size();i++) {
					System.out.println(bonds.get(i));
				}
			}
		}
}
