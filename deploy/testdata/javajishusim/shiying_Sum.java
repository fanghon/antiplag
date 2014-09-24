import java.util.Scanner;
public class shiying_Sum
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
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		System.out.println("输入一个整数：");
        
		Scanner sc = new Scanner(System.in);
        
		int n = sc.nextInt();
         
        
		shiying_Sum fa = new shiying_Sum();
        
		float sum = fa.sum(n);
        
		System.out.println("输出："+sum);
         
	}

}
