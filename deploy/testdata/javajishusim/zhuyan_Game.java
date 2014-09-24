import java.util.Scanner;
public class zhuyan_Game
{
	public static void main(String[] args)
	{
			System.out.println("1 开始游戏   2 退出");
			Scanner sc=new Scanner(System.in);
			int n=0;
			try
			{
				n=sc.nextInt();
			} catch (Exception e)
			{
				
			}
			switch (n)
			{
			case 1:System.out.println("开始游戏");
			       break;
			case 2:System.exit(0);
			       break;
			default:System.out.println("输入错误,请重新输入");
			}
			String[] args1 =null;
			main(args1);
	}
}
