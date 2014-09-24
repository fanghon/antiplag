import java.util.Scanner;
   public class daichengzeng_sum
     {    void sum()
		{   int sum=2;
			System.out.println("输入一个整数");
			Scanner sc=new Scanner(System.in);
			int b= sc.nextInt();
			   for(int i=1;i<=b;i++)
	 {   sum=sum+i;
	   } System.out.println(sum);
   }
		public static void main(String[] args){
			  daichengzeng_sum dcz=new daichengzeng_sum();
			   dcz.sum();
		  }	
	 }

