import java.util.Scanner;


public class zhaodexiong_Game
{
	static void displayMainMenu(){
		System.out.println("1 游戏开始");
		System.out.println("1 游戏结束");
	}
	static int getInput(){
		Scanner sc=new Scanner(System.in);
		int j=-1;
		try{
			j=sc.nextInt();
		}catch(Exception e){}
	}
	
	
static void dispError(){
	System.out.println("输入错误，重新输入");
}
static boolean handle(int sel){
	switch(sel){
	case 1:System.out.println("游戏开始");
	break;
	case 2:return true;
	default:dispError();
	
	}return false;
	}

