import java.util.*;
public class zhuyun_Game
{     
	  static void Game(){
      System.out.println("1 开始游戏");
      System.out.println("2 退出");
	  }
	  static void kaishi(int sel){
		   switch (sel){
		     case 1: System.out.println("游戏开始");
		             break;
		     } 
		   }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
      boolean exit =false;
      while(!exit){
    	   Game();break;
    	// kaishi();
    	  
      }
	}

}
