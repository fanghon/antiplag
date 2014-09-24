import java.util.Scanner;

public class MengShaSha_Sum
{
	void sum(){
		int sum=0;
		System.out.println("输入一个整数：");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for(int i=1;i<=n;i++){
			sum=sum+i;
		}
		System.out.println(sum);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MengShaSha_Sum mss=new MengShaSha_Sum();
		mss.sum();

	}

}
