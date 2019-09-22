import java.util.Scanner;

public class xujinxia_Game
{
	static void displayMainMenu(){
	System.out.println("1 开始游戏");
	System.out.println("2 退出");
}
	static int getInput()
	{
    Scanner sc=new Scanner(System.in);
	int i= -1;
	    try{
	      i=sc.nextInt();
	    }catch(Exception e){
	      
	    }
	   return i;  
	 }
  static void dispError(){
	  System.out.println("输入错误，请重新输入");
	 }
    static boolean handle(int sel){
	    switch (sel){
	     case 1: System.out.println("游戏开始");
	             break;
	     case 2: return true;  
	     default:dispError();
	 }
   return false;
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	boolean exit=false;
while(!exit)
  {
	 displayMainMenu();  
	  int sel=getInput(); 
	 exit=handle(sel);  
		  
  }
}
}
