import java.sql.*;
import java.util.Scanner;
public class daichengzeng_Login
{  private  static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcdriver";
   private   static final String URL = "jdbc:odbc:driver={Microsoft Access Driver  *.mdb)};DBQ=./atm.mdb";
	Connection conn = null;
	 Scanner sc=new Scanner(System.in);
	public static Connection getConnection(){
	 Connection conn = null;
try
	{  Class.forName(DRIVER);  
	  System.out.println("数据库驱动加载成功");
	} catch (ClassNotFoundException e)
{ e.printStackTrace();return null;}
  try
{ conn = DriverManager.getConnection(URL);
  System.out.println("数据库连接成功");
	} catch (SQLException e)
  {  e.printStackTrace();	
	} return conn;  }
	}
