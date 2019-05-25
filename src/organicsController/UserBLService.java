package organicsController;
import java.util.LinkedList;

import organicsModel.UserTransaction;
public class UserBLService {
	
	//register
	public boolean register(String name,String password){
		return UserTransaction.registerUser(name, password);
	}
	//login
	public boolean login(String name,String password) {
		return UserTransaction.loginUser(name, password);
	}
	
	//获取搜索记录
	public LinkedList<String> getResearchRecord(String name){
		return UserTransaction.getRearchRecord(name);
	}
	
	//存储原子分子式
	public boolean saveMolecule(String molename) {
		return true;
	}
	
	//test
	public static void main(String[] args){
		UserBLService userBLService=new UserBLService();
		LinkedList<String> res=userBLService.getResearchRecord("suwan");
		for(String s:res)System.out.println(s);
	}
	
}
