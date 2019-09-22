import java.util.Scanner;
public class zhaodexiong_Sum
{
	public static void main(String args[]){
	int i=1,sum=0;
	Scanner scanner=new Scanner(System.in);
	int n=scanner.nextInt();
	do{
		sum+=i;
		i++;
	}while(i<=n);
	System.out.println("1+2+...+"+n+"="+sum);
}
}
