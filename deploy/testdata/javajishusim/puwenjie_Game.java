import java.util.Scanner;
public class puwenjie_Game
{
	//生成一个主菜单的程序
	static void zhucaidan(){
		 System.out.println("1 开始游戏");
		 System.out.println("2 退出");
	}
	
	
	//从键盘输入并且读取相应内容并且返回m
	
	 static int read(){
	    Scanner sc = new Scanner(System.in);
	    int m = -1;
	    try{
	      m = sc.nextInt();
	      
	    }catch(Exception e){
	      
	    	
	    }
	    return m;  
	 }
	 
	 
	 
	 //出错提示
	 static void chucuotishi(){
	  System.out.println("输入错误，请重新输入");
	 }
	 static boolean panduan(int sel){
		   switch (sel){
		     case 1: System.out.println("游戏开始");
		             break;
		     case 2: return true; 
		     default:chucuotishi();
		  
		   }
		   return false;
		                }
	 
	 
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		 boolean exit = false;
		  while(!exit){
		    zhucaidan(); 
		    int n =  read(); 
		    exit = panduan(n);  
		  }
	}//main

}//class
