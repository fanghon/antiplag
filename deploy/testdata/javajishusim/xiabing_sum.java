import java.util.Scanner;
public class xiabing_sum
{
	public static void main(String args[])
{
		Scanner scan=new Scanner(System.in);
		int count=0;
		int num=scan.nextInt();
		for(int i=1;i<=num;i++){
			count +=i;
			
		}
		System.out.println(count);
}
}

