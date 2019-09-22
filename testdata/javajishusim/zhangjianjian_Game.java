import java.util.Scanner;
public class zhangjianjian_Game
{
    static void dpMM(){
    	System.out.println("1 开始游戏*");
    	System.out.println("2 退出游戏*");}
    static int Input(){
    	Scanner sc=new Scanner(System.in);
    	int i=-1;
    	try{i=sc.nextInt();}
    	catch(Exception e){}
    	return i;}
      static void xw(){
    	  System.out.println("您的输入有误，请重新输入一个数");}    
public static void main(String[] args)
	{ boolean exit=false;
      while(!exit){dpMM();int sel=Input();}
     }

}
