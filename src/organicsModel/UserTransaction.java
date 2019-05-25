package organicsModel;

import java.util.LinkedList;

public class UserTransaction {
	
	//用户注册
	public static boolean registerUser(String name,String password) {
		String record="\""+name+"\""+","+"\""+password+"\""+","+"\"\","+"\"\"";
		return DBUtil.add(record, "User");
	}
	//用户登陆
	public static boolean loginUser(String name,String password) {
		String sql="select * from User where name=\""+name+"\" and password=\""+password+"\";";
		return DBUtil.search(sql);
	}
	//获取搜索记录
	public static LinkedList<String> getRearchRecord(String name){
		LinkedList<String> res=new LinkedList<String>();
		String sql="select searchRecord from User where name=\""+name+"\";";
		String str=DBUtil.searchField(sql);
		if(str==null)return res;
		else{
			String[] strs=str.split(",");
			for(String s:strs) {
			res.add(s);
			}
		}	
		return res;
	}
}
