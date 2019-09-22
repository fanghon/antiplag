import java.util.Scanner;
public class shaoxionglu_sum
{

	void sum(){
		int sum=0;
		System.out.println("请输入一个整数");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=1;i<=n;i++){
			sum=sum+1;
		}
		System.out.println(sum);
	}
	public static void main(String[] args)
	{
		shaoxionglu_sum sxl = new shaoxionglu_sum();
		sxl.sum();

	}

}
