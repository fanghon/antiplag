import java.util.Scanner;
public class Mashuying_Sum
{
	/**
	 * Sum
	 * 
	 * 
	 */
 static int sum(int n){
	 
 int i;
 
 int Sum=0;
 
 for(i = 1;i <= n; i++){
	 
	Sum=Sum+i;
	}
 
return Sum;
  }
public static void main(String[] args)
	{
	System.out.println(" 输入一个整数: ");
	
	Scanner sc=new Scanner
		
	(System.in);
		
	int n=sc.nextInt();
		
	System.out.println(" 和 ："+sum(n));
			 
	 }//main

}//class


