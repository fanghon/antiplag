import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public class sunliyan_Login
{

	private static final String DRIVER ="sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String URL ="jdbc:odbc:driver={Microsoft Access Driver  *.mdb)};DBQ=./atm.mdb";

	public static void main(String[] args)
	{
		
		System.out.println("输入用户名：");
		Scanner ss = new Scanner(System.in);
		String name =ss.nextLine();
		System.out.println("输入密码：");
		String password =ss.nextLine();
		{
		
			
		}	
		
	
	}
}
