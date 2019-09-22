import java.util.Scanner;
public class puwenjie_Sum
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		int i,sum=0;
		Scanner sc=new Scanner(System.in);//从键盘获取一个整数
		System.out.println("输入一个整数:");
		int n=sc.nextInt();
		for(i=1;i<=n;i++)
		{
			sum=sum+i;
			}
		System.out.println("1…n的算术和为:");
		System.out.println(sum);
	}//main

}//class
