import java.util.Scanner;
public class zhuyan_Sum
{
	public static void main(String[] args)
	{
		System.out.println("输入一个整数");
		Scanner sc=new Scanner(System.in);
		int n=0,s=0,i=1;
		n=sc.nextInt();
		while(i<=100)
		{
			s+=i;
			i++;
		}
		System.out.println(s);
	}

}