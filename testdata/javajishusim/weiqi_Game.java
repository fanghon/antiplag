

import java.util.Scanner;

public class weiqi_Game
{
	static void zhujiemian(){
		
	 System.out.println("1 开始游戏");
	 System.out.println("2 退出");
	 
	}
	
	 static int huoqu()
	 {
	    Scanner sc = new Scanner(System.in);
	    
	    int i = -1;
	     try{
	        i = sc.nextInt();
	     }catch(Exception e){
	     } 
	       return i;  
	 }
	 
	 static void cw(){
	  System.out.println("输入错误，请重新输入");
	 }
	 
	 static boolean qita(int qt){
	   switch (qt)
	   {
	      case 1: System.out.println("游戏开始");break;    
	      case 2: return true; 
	     default:cw();
	   }
	      return false;
	 
	 }
	/**
	 * @param args
	 */
	  public static void main(String[] args)
	  {
		// TODO Auto-generated method stub
		  boolean tuichu = false;
		  
		  while(!tuichu)
		   {
		    zhujiemian();  
		    int qt =  huoqu(); 
		    tuichu = qita(qt); 
		    
		   }
	 }

}
