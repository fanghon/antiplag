import java.util.Scanner;

public class wanglingli_Sum
{
    void sum(){
    	int sum=0;
    	System.out.println("输入一个整数");
    	Scanner sc=new Scanner(System.in);
    	int n=sc.nextInt();
    	for(int i=1;i<=n;i++){
    		sum=sum+i;
    	}
    	System.out.println(sum);
    } 
    /**
	 * @param args
	 */
      public static void main(String[] args)
      {
          //TODO Auto-generated method stub
    	 wanglingli_Sum wll=new wanglingli_Sum();
    	 wll.sum();
      }
}
