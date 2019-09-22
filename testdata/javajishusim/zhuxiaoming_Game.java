
import java.util.*;
public class zhuxiaoming_Game
{
   static void xianshi(){
	System.out.println("1 开始游戏");
	System.out.println("2 退出");
   }
	static int hq()
	{
		Scanner sc=new  Scanner(System.in);
		int i=-1;
		try
		{
			i=sc.nextInt();
		} catch (RuntimeException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return i;
	}
	static void xianerror()
	{
		System.out.println("输入错误，请重新输入");
	}
	static boolean bd(int s)
	{
		switch (s){
		case 1:System.out.println("开始游戏");break;
		case 2: return true;
		default:xianerror();
		}
  return false;
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
    boolean e=false;
    while(!e)
    {
    	xianshi();
    	int s=hq();
    	e=bd(s);
    	
    }
	}

}
