import java.sql.*;
import java.util.Scanner;
public class Mashuying_Login
{
	private static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String URL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./atm.mdb";

	/**
	 * 驱动：sun.jdbc.odbc.JdbcOdbcDriver 
	 * name，password
	 * @param args
	 * 
	 * 
	 */
	public static void main(String[] args)
	{
	System.out.println(" 输 入 用 户 名：");
	Scanner sc=new Scanner(System.in);
	String name=sc.nextLine();
	System.out.println(" 输 入 密 码：");
	String password=sc.nextLine();
	try
	 {
			Class.forName(DRIVER);
	} catch (ClassNotFoundException e)
	 {
		e.printStackTrace();
		System.out.println(" 数 据 库 驱 动 加 载 失 败 了 ，程 序 自 动 退 出 ");
		return;
	 }
	try
	 {
	Connection conn=DriverManager.getConnection(URL);
	String sql="select *from atm where name=?";
	PreparedStatement pst=conn.prepareStatement(sql);
	pst.setString(1,name);
	ResultSet rs=pst.executeQuery();
	String dbpassword=null;
	String dbname=null;
	while(rs.next()){
	  dbpassword=rs.getString("password");
	  dbname=rs.getString("name");
	    break;
	}
	if(name.equals(dbname)&&password.equals(dbpassword)){
		System.out.println(" 登陆成功 ");
	}else{
		System.out.println(" 登陆失败 ");
	}
	rs.close();
	pst.close();
	conn.close();
} catch (SQLException e){
		
 // TODO Auto-generated catch block
	e.printStackTrace();
	System.out.println("数据库存取错误，系统自动退出");
	return;

	}

	}//main
	
	
}//class


