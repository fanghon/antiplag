import java.util.*;
public class zhumaolei_Game
{
	static void playgame(){
	 System.out.println("1 开始游戏");
	 System.out.println("2 退出");
	}
		 static int getInput(){ 
	    Scanner cs = new Scanner(System.in);
	    int n=-1;
	    try{
	      n = cs.nextInt();
	    }catch(Exception e){
	    }
	    return n;  
	 }
	 static void Error(){
	  System.out.println("输入错误，请重新输入");
	 }
	 static boolean handle(int sel){
	   switch (sel){
	     case 1: System.out.println("游戏开始");
	             break;
	     case 2: return true;
	     default:Error();
	   }
	   return false;
	 }
	public static void main(String[] args)
	{
		  boolean exit = false;
		  while(!exit){
		    playgame();
		    int sel =  getInput();
		    exit = handle(sel);
		 }
	}
}
