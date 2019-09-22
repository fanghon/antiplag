import java.util.Scanner;

public class DongYouWei_Sum{
	public static void main(String[] args)
	{
		System.out.println("请输入一个整数：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
		for(int i=0;i<=n;i++){
			sum = sum+i;}
        System.out.println("累加和为："+sum);
	}

}
