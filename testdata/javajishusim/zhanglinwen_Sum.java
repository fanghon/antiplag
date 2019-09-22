import java.util.Scanner;


public class zhanglinwen_Sum
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	// TODO Auto-generated method stub
		System.out.println("请输入一个整数：");
		int i=1,sum=0;
		
		Scanner scanner =new Scanner(System.in);
		int n=scanner.nextInt();
		
		do{
			sum+=i;
			i++;
			
		}
		while(i<=n);
		System.out.println("1+2+……+"+n+"="+sum);
	}
}
