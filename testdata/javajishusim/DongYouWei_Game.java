import java.util.*;
public class DongYouWei_Game
{
     static void displayMainMenu(){
	 System.out.println("1.开始游戏");
	 System.out.println("2.退出");

	}  
	 static int inpout(){
	   Scanner sc = new Scanner(System.in);
	   int i = -1;
	   try{
	     i = sc.nextInt();
	   }catch(Exception e){
	   System.out.println("请输入正确的数字非字符!");   
	   }
	    return i;  
	 } 
	 static boolean handle(int chouse){
	   switch (chouse){
	     case 1: System.out.println("游戏开始");break;
	     case 2: return true;
	     }
	   return false;
	 
	 }
	public static void main(String[] args)
	{
		  boolean out = false;
		  while(!out){
		  displayMainMenu();  
		  int chouse =inpout(); 
		  out= handle(chouse); 
		  }
	}

}