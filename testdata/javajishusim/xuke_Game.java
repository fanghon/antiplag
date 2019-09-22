

import java.util.Scanner;


public class xuke_Game
{

	static void display()
	{
		 
		System.out.println("1 开始游戏");
		 System.out.println("2 退出");

		}
		
		 
		 static int Input(){
		    
		    Scanner sc = new Scanner(System.in);
		    
		    int j = -1;
		    try{
		      j = sc.nextInt();
		    }
		    catch(Exception e){
		      
		    }
		    return j;  
		 }
	
		 static void Error(){
		    
			 System.out.println("输入错误，请重新输入");
		 
		 }
		 
		 static boolean handle(int n){
		   
		switch (n)
		{
		   case 1: System.out.println("游戏开始");
		             break;
		   case 2: System.out.println("游戏退出");
		    	     return true;  //
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
			boolean exit = false;
			  
			while(!exit)
		{
			  display();  //
			    
			  int n =  Input(); //
			    
			  exit = handle(n);  //
			  //
		 }
	}//

 }//
