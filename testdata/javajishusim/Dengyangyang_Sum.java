import java.util.Scanner;
public class Dengyangyang_Sum
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
		System.out.println("输入一个整数");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        Dengyangyang_Sum de = new Dengyangyang_Sum();
        float sum = de.sum(n);
        System.out.println("求和为："+sum);
	}
}

	

