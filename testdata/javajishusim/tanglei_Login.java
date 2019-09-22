import java.util.Scanner;
import java.sql.*;
public class tanglei_Login {
private static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
private static final String URL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./atm.mdb";


public static void main(String[] args) {
System.out.println("请输入用户名:");
Scanner t = new Scanner(System.in);
		String name = t.nextLine();
System.out.println("请输入密码：");
	String password = t.nextLine();
	
		 	   try
			{
		Connection conn =  DriverManager.getConnection(URL);
		   String sql = "select * from atm where name=?";
		   PreparedStatement pst = conn.prepareStatement(sql);
				   ResultSet  rs = pst.executeQuery();
			   String dbpassword = null;
		   String dbname = null;
					   while(rs.next()){
					   dbname = rs.getString("name");
				   break;
					   }
		   if(name.equals(dbname) && password.equals(dbpassword)){
				   System.out.println("登录成功");
					   }else{
			   System.out.println("登录失败");
					  }
					   
				  
				} catch (SQLException e)
		{
					e.printStackTrace();
					System.out.println("数据库存取错误，系统退出");
					return;
	}
	}
}
