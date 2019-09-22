import java.util.Scanner;
public class lizezhong_Sum
{
  float sum(int n){				
  int sum = 0;
  for(int i=0;i<=n;i++){
  sum = sum+i;
    }
    return sum;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
{
		
  System.out.println("请输入一个整数：");
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt( );
  lizezhong_Sum fa = new lizezhong_Sum();
  float sum = fa.sum(n);
  System.out.println("和为："+sum);
  }
  }
