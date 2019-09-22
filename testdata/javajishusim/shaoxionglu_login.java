import java.sql.*;
import java.util.Scanner;
public class shaoxionglu_login
{

	private static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String URL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./atm.mdb";
	
	public static Object main(String[] args)
	{
		Connection conn = null;
		try
		{
			Class.forName(DRIVER);
			System.out.println("数据库驱动加载成功");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		try
		{
			conn = DriverManager.getConnection(URL);
			System.out.println("数据库连接成功");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	

}
