import java.util.*;
public class zhuyun_Sum
{
	
    int sum(int n ){
    	int sum=0;
    	for(int i=1;i<=n;i++)
    		{sum=sum+i;}
    return sum;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
       System.out.println("输入一个整数");
       Scanner sc =new Scanner(System.in);
       int n = sc.nextInt();
       zhuyun_Sum a = new zhuyun_Sum();
      int sum=a.sum(n);
      System.out.println("结果："+sum);
	}

}
