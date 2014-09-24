import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class bianlinliang_Login
{

	/**
	 * @param args
	 */
	private static final String  DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";//驱动名称
	 private static final String URL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./atm.mdb";
	 static String name=null;
		static String password=null;
	public static void main(String[] args)
	{
		ResultSet  rs = null ;
		PreparedStatement pst=null;
		Connection conn = null;
		
		 try
		{
			Class.forName(DRIVER);  
		
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		
			
		}	 
		  try
		{
			conn = DriverManager.getConnection(URL);
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		
		Scanner sc=new Scanner(System.in);
		
		
		System.out.println("请输入用户名：");
		String uname=sc.nextLine();
		System.out.println("请输入密码：");
		String upassword=sc.nextLine();
		
		String sql="select * from atm where name=? ";
		try
		{
			pst=conn.prepareStatement(sql);
			pst.setString(1, uname);
			
		} catch (SQLException e)
		{
			System.out.println("shibai");
			
		}
		try
		{
			rs=pst.executeQuery();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			while(rs.next()){  
			   	 name = rs.getString("name");     	
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		String sql1="select * from atm where password=? ";
		try
		{
			pst=conn.prepareStatement(sql1);
			pst.setString(1, upassword);
			
		} catch (SQLException e)
		{
			System.out.println("shibai");
			
		}
		try
		{
			rs=pst.executeQuery();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
