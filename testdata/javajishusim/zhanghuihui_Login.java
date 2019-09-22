import java.sql.*;
import java.util.Scanner;
public class zhanghuihui_Login
{
	  private static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	  private static final String URL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./atm.mdb";
	/**
	 * @param args
	 */public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("输入用户名：");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();  
		System.out.println("输入密码：");
		String password = sc.nextLine();
		 try
			{
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("数据库驱动加载失败，程序退出");
				return ;
			}
			   try
{Connection conn =  DriverManager.getConnection(URL);
String sql = "select * from atm where name=?";  
PreparedStatement pst = conn.prepareStatement(sql);
pst.setString(1, name); 
ResultSet  rs = pst.executeQuery();
String dbpassword = null;
 String dbname = null;
while(rs.next()){
dbpassword = rs.getString("password");  
dbname = rs.getString("name");
 break;
					   }
 if(name.equals(dbname) && password.equals(dbpassword)){
 System.out.println(" 登录成功");
 }else{
  System.out.println(" 登录失败");
					   }  
 //rs.close();
//pst.close();
					  // conn.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("数据库存取错误，系统退出");
					return;
				}
			}
	    }


