package organicsModel;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
//抽象数据库处理
public class DBUtil{
	private static final String url="jdbc:mysql://localhost:3306/Organics3D";
	private static final String user="root";
	private static final String password="123456";
	//增加一条记录(用String[])
	public static boolean add(String record,String tableName){
		try {//使用try-catch就不会往上传递异常
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user, password);
			Statement s=con.createStatement();
			String sql="insert into "+tableName+" values("+record+");";
			if(s.executeUpdate(sql)!=0) {
				s.close();con.close();
				return true;
			}
			else {
				s.close();con.close();
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//查询记录是否存在
	public static boolean search(String sql) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user, password);
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(sql);
			if(!rs.next()) {
				rs.close();s.close();con.close();
				return false;
			}else {
				rs.close();s.close();con.close();
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//获得查询结果（一条记录）的某个字段
	public static String searchField(String sql) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user, password);
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(sql);
			String res="";
			if(rs.next()) {
				res=rs.getString(1);
;				rs.close();s.close();con.close();
				return res;
			}else {
				rs.close();s.close();con.close();
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//更新某条记录的某个字段
	public static boolean updateField(String sql) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user, password);
			Statement s=con.createStatement();
			if(s.executeUpdate(sql)!=0) {
				return true;
			}else return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}