import java.util.*;
import java.sql.*;
public class zhuyun_Login
{
   private static final String DRIVER ="sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String URL ="jdbc:odbc:driver={Microsoft Access Driver  *.mdb";
   /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
       System.out.println("输入用户");
       Scanner sc =new Scanner(System.in);
       String name =sc.nextLine();
       System.out.println("输入密码");
       String password =sc.nextLine();
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
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
