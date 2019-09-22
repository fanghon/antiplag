import java.util.Scanner;

public class tanglei_Sum 
{
	static int sum(int t)
	{
	int i;
	int Sum=0;
	for(i=1;i<=t;i++)
	{
			Sum=Sum+i;
		}
			return Sum;
}


	public static void main(String[] args)
	{
		System.out.println("输入一个整数");
		Scanner c=new Scanner
				(System.in);
		int s=c.nextInt();
		System.out.println(+sum(s));

	}

}	
