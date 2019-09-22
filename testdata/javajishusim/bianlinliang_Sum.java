import java.util.*;
public class bianlinliang_Sum
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
System.out.println("输入一个整数");
int n;
Scanner sc=new Scanner(System.in);
n=sc.nextInt();
int sum=0;
for(int i=0;i<=n;i++)
{
	sum=sum+i;
	}
System.out.println(sum);
	}

}
