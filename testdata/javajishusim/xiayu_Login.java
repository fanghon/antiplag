import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class xiayu_Login
{
	  private static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	  private static final String URL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./atm.mdb";
	public static void main(String[] args)
	{
		System.out.println("请输入用户名");
		Scanner sc=new Scanner(System.in);
		String name="",password="",dbpwd="";
		if(sc.hasNext())
		{
			name=sc.next();
		}
		System.out.println("请输入密码");
		if(sc.hasNext())
		{
			password=sc.next();
		}
		Connection Conn=null;
			try
			{
				Class.forName(DRIVER);
				Conn=DriverManager.getConnection(URL);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			String sql="select * from atm where name=?";
			try
			{
				PreparedStatement pst=Conn.prepareStatement(sql);
				pst.setString(1, name);
				ResultSet rs=pst.executeQuery();
				if(rs.next())
				{
					dbpwd=rs.getString("password");
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			if(dbpwd.equals(password))
			{
				System.out.println("登录成功");
			}
			else
			{
				System.out.println("登录失败");
			}
	}

}
