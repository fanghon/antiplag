import java.util.Scanner;


public class xiayu_Game
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		while(true)
		{
			showMenu();
			Scanner scn=new Scanner(System.in);
			int num=0;
			try
			{
				num=scn.nextInt();
			} catch (Exception e)
			{
				// TODO: handle exception
			}
			switch (num)
			{
			case 1:
			{
				System.out.println("开始游戏");
				break;
			}
			case 2:
			{
				System.exit(0);
				break;
			}
			default:System.out.println("输入错误，请重新输入");
			}
		}
	}
	static void showMenu()
	{
		System.out.println("1 开始游戏   2 退出");
	}
}
