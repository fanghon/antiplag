import java.util.Scanner;



public class fangjian_Sum
{
   
	float sum(int n){
		float res = 0;
		int s = 0;
		for(int i=0;i<=n;i++){
			s = s+i;
		}
		res = s/(n*1.0f);
		
		return res;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("请输入累加的n：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        fangjian_Sum to= new fangjian_Sum();
        float sum =to.sum(n);
        System.out.println("平均数为："+sum);

	}

}
