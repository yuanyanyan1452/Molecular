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
			String[] input= {"CH2O"};
			for(String str:input) {
				
				//有机物存在哪些原子及它们的坐标
				LinkedList<Mole> moles=ServiceController.serviceDispatcher(str);
				for(int i=0;i<moles.size();i++) {
					System.out.println(moles.get(i).toString());
				}
				
				//有机物哪些原子之间相连，即化学键
				LinkedList<String> bonds=organicsService.ServiceController.serviceDispatcher(str);
				for(int i=0;i<bonds.size();i++) {
					System.out.println(bonds.get(i));
				}
				
			}
		}
}
