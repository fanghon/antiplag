import java.util.Scanner;

public class xujinxia_Sum
{
 int Sum=0;
 int n=0;
   int Sum(int n){
    for(int i=1;i<=n;i=i+1)
    {
       Sum=Sum + i;
    }  
    return Sum;
   }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	 System.out.println("请输入一个整数：");
	 Scanner sc=new Scanner(System.in);
     int n=sc.nextInt(); 
         xujinxia_Sum  xu = new xujinxia_Sum();
       int Sum=xu.Sum(n);
        System.out.println("累加和为："+Sum);
         
	}

}
