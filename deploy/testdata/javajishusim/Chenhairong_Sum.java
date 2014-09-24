

import java.util.Scanner;


public class Chenhairong_Sum
{
   float sum(int n){
	int j=0;
	int r=0;
	int sum=0;
	for(j=0;j<=n;j++){
		sum=sum+n;
	}
	return r;
   }
	public static void main(String[] args)
	{
        System.out.println("请输入一个整数：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Chenhairong_Sum fa = new Chenhairong_Sum();
        float sum = fa.sum(n);
        System.out.println("整数的和为："+sum);
         
	}

}
