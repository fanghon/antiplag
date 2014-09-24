import java.util.Scanner;


public class xiayu_Sum
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("输入一个整数");
		Scanner sc=new Scanner(System.in);
		int num=0,sum=0;
		try
		{
			if(sc.hasNext())
			{
				num=sc.nextInt();
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			System.out.println("输入错误");
			return;
		}
		sum=Sum(num);
		System.out.println(sum);
	}
	static int Sum(int n)
	{
		if(n==1)
			return 1;
		else
			return n+Sum(n-1);
	}

}
