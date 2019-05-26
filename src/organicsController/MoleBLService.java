package organicsController;

import java.util.LinkedList;

import organicsUtil.Mole;

//对外显示的原子相关的业务逻辑的接口
public class MoleBLService {
	
	//获取所有原子的坐标（单种有机物），inputType分为按分子式和按中文名
	 public LinkedList<Mole> getMoleCoordinates(String moleName,String inputType){
		 return CoordinateSingleServiceController.serviceDispatcher(moleName, inputType);
	 }
	 //获取有机物的所有化学键
	 public LinkedList<String> getBonds(String moleName,String inputType){
		 return BondSingleServiceController.serviceDispatcher(moleName, inputType);
	 }
	 //是否在中学化学范围内
	 public boolean checkRange(String moleName) {
		 return BondSingleServiceController.checkRange(moleName);
	 }
}
