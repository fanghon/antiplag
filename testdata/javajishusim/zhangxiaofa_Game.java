   import java.util.Scanner;
   public class zhangxiaofa_Game
{
	
	static void game()
	
	{
	        System.out.println("1 开始游戏");
	 
	        System.out.println("2 退出");

	} 
static int shurupanduan()
         {
	    
	    Scanner sc = new Scanner(System.in);
	      int i = -1;
	    try
	       {
	        i = sc.nextInt();
	       }
	    catch(Exception e)
	    { 
	    	System.out.println("错误！");
	    }
	    return i;  
	 }

static void Error()
{
	    System.out.println("输入错误，请重新输入");
}
static boolean youxiqiehuan(int _se){
	   switch (_se)
	  {
	    case 1: System.out.println("游戏开始");break;case 2: return true;  
	    
	    default:Error();
	  }
	   return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		   boolean _ex = false;
		        while(!_ex){
		      game();  
		       int sel =  shurupanduan(); 
		     _ex = youxiqiehuan(sel);  
		         }
	}

}


