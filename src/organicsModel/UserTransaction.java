package organicsModel;

import java.util.LinkedList;
//和用户有关的事务处理
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
	//更新收藏列表
	public static boolean saveMolecule(String moleName,String name) {
		String saveList=DBUtil.searchField("select saveList from User where name='"+name+"';");
		//若该有机物已收藏过
		if(saveList!=null) {
			String[] strs=saveList.split(",");
			for(String s:strs) {
				if(s.equals(moleName))return true;
			}
		}
		String sql="update User set saveList=CONCAT(saveList,'"+moleName+",') where name='"+name+"';";
		return DBUtil.updateField(sql);
	}
	//添加搜索记录(每次搜索的时候要调用）-搜索记录默认是最新的十条
	public static boolean addSearchRecord(String moleName,String name) {
		String searchRecord=DBUtil.searchField("select searchRecord from User where name='"+name+"';");
		String[] strs=searchRecord.split(",");
		//如果记录少于十条，则可以直接添加新的记录；若已经十条了，则将第一个替换成新的记录
		if(strs.length<10) {
			String sql="update User set searchRecord=CONCAT(searchRecord,'"+moleName+",') where name='"+name+"';";
			return DBUtil.updateField(sql);
		}else{
			LinkedList<String> temp=new LinkedList<String>();
			for(String s:strs) {
				temp.add(s);
			}
			temp.removeLast();
			temp.offerFirst(moleName);
			searchRecord="";
			for(String s:temp) {
				searchRecord+=(s+",");
			}
			String sql="update User set searchRecord='" +searchRecord+"' where name='"+name+"';";
			return DBUtil.updateField(sql);
		}
	}
}
