import java.util.Scanner;
public class zhuzhiwei_Sum
{
   float Sum(int n)
   {
		float res = 0;
		int sum = 0;
		for(int i=0;i<=n;i++)
	{
		sum = sum+i;
	}
		res = sum;
		return res;
	}
	public static void main(String[] args)
	{
		
   System.out.println("请输入一个整数：");
      Scanner sc = new Scanner(System.in);
      
         zhuzhiwei_Sum fa = new zhuzhiwei_Sum();
       float Sum = fa.Sum();
   System.out.println("和："+Sum);
         
	}

}

