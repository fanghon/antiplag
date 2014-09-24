import java.util.Scanner;


public class jichuanming_Sum
{
	float sum(int x){
		int sum=0;
		for(int z=0;z<=x;z++)
		{
			sum=sum+z;
		}
		return sum;
		}
	public static void main(String[] args){
		System.out.println("输入整数：");
		Scanner sc= new Scanner(System.in);
		int x=sc.nextInt();
		jichuanming_Sum da=new jichuanming_Sum();
		float sum=da.sum(x);
		System.out.println("和为"+sum);
	}
	}
