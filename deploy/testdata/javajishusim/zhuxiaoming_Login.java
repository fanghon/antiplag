import java.util.*;
import java.sql.*;
public class zhuxiaoming_Login
{ private static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
 private static final String URL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./atm.mdb";
 
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	System.out.println("请输入你的用户名：");
	Scanner sc = new Scanner(System.in);
	String name = sc.nextLine();  
	System.out.println("请输入你的密码：");
	String password = sc.nextLine();
		try
		{
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   try
	{
  Connection conn =  DriverManager.getConnection(URL);
	  
   String sq = "select * from atm where name=?";  
   PreparedStatement p = conn.prepareStatement(sq);
   p.setString(1, name); 
   ResultSet  r = p.executeQuery();
   String dbpassword = null;
   String dbname = null;
   while(r.next())
		  {
			 dbpassword = r.getString("password");  
			 dbname = r.getString("name");
			 break;
		}
	  if(name.equals(dbname)&&password.equals(dbpassword))
	  {
			 System.out.println(" 登录成功");
		}
	  else
	{
		 System.out.println(" 登录失败");
	 }  
		
	  } catch (SQLException e)
		{
				// TODO Auto-generated catch block
		e.printStackTrace();
				
		}
			   
	}
}


