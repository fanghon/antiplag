
import java.util.Scanner;



public class niuyangyang_Sum
{
   
	float sum(int n){
		float res = 0;
		int sum = 0;
		for(int i=0;i<=n;i++){
			sum = sum+i;
		}
		res = sum;
		
		return res;
	}

	
	
	public static void main(String[] args)
	{

System.out.println("请输入一个整数：");
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         
         niuyangyang_Sum fa = new niuyangyang_Sum();
         float sum = fa.sum(n);
System.out.println("求和为："+sum);
         
	}

}