import java.sql.*;
import java.util.Scanner;

public class MengShaSha_Login
{
	private static final String DRIVER="sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String URL="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./atm.mdb";
	public static Connection getConnection(){
		Connection conn=null;
		try
		{
			Class.forName(DRIVER);
			System.out.println("数据库驱动加载成功");			
		}catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		try
		{
			conn=DriverManager.getConnection(URL);
			System.out.println("数据库连接成功");
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(ResultSet rs,PreparedStatement pst,Connection conn){
		try
		{
			if(rs!=null)
				rs.close();
			if(pst!=null)
				pst.close();
			if(conn!=null)
				conn.close();
		}catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Connection conn=MengShaSha_Login.getConnection();
		System.out.println("用户名：");
		String name=sc.nextLine();
		System.out.println("密码： ");
		String sql="select * from atm";
		PreparedStatement pst=null;
		try
		{
			pst=conn.prepareStatement(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		ResultSet rs=null;
		try
		{
			rs=pst.executeQuery();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		MengShaSha_Login.close(rs, pst, conn);

	}

}
