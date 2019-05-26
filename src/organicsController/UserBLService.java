package organicsController;
import java.util.LinkedList;

import organicsModel.UserTransaction;
//对外显示的用户相关的业务逻辑接口
public class UserBLService {
	
	//注册
	public boolean register(String name,String password){
		return UserTransaction.registerUser(name, password);
	}
	//登陆
	public boolean login(String name,String password) {
		return UserTransaction.loginUser(name, password);
	}
	
	//获取搜索记录
	public LinkedList<String> getResearchRecord(String name){
		return UserTransaction.getRearchRecord(name);
	}
	
	//存储原子分子式（添加了名字属性）
	public boolean saveMolecule(String moleName,String name) {
		return UserTransaction.saveMolecule(moleName, name);
	}
}
