import java.util.Scanner;
public class LiuLinLin_Sum{
	void sum(){
		int sum=0;
		System.out.println("输入一个整数");
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
		LiuLinLin_Sum LLL=new LiuLinLin_Sum();
		LLL.sum();


	}

}
