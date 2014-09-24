
import java.util.*;

public class zhumaolei_Sum
{
	float sum(int y){
		int sum = 0;
		for(int k=0;k<=y;k++){
			sum = sum+k;
		}
		return sum;
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
         System.out.println("输入一个整数:");
         Scanner sc = new Scanner(System.in);
         int y = sc.nextInt();
         zhumaolei_Sum da=new zhumaolei_Sum();
         float sum =da.sum(y);
         System.out.println("累加和为："+sum);
         }
}
