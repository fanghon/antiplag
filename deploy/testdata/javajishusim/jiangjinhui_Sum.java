import java.util.Scanner;
public class jiangjinhui_Sum
{
  float sum ( int n) {     
	  float res = 0;
	  int sum = 0;
	  for(int i = 0;i<=n;i++)
	   {
		   sum = sum+i;
	   }
	  res = sum+(n+1.0f);
	   return res;
   }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
        System.out.println("请输入一个整数：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        jiangjinhui_Sum ji = new jiangjinhui_Sum();
        float sum = ji.sum(n);
        System.out.println("res："+sum);
	}

}
