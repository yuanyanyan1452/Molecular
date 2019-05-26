package organicsController;
import java.util.LinkedList;
import organicsUtil.Mole;
public class BaseTest {
		public static void main(String[] args) {
			String[] input= {"C3H4O"};
			String inputType="按分子式";
			MoleBLService ms=new MoleBLService();
			for(String str:input) {
				System.out.println(ms.checkRange(str));
				//有机物存在哪些原子及它们的坐标
				LinkedList<Mole> moles=ms.getMoleCoordinates(str, inputType);
				for(int i=0;i<moles.size();i++) {
					System.out.println(moles.get(i).toString());
				}
				
				//有机物哪些原子之间相连，即化学键
				LinkedList<String> bonds=ms.getBonds(str, inputType);
				for(int i=0;i<bonds.size();i++) {
					System.out.println(bonds.get(i));
				}
				
			}
		}
}
