import java.util.Scanner;
	public class zhangtianyu_Game
{
		static void disMain(){
			System.out.println("1 开始游戏");
			System.out.println("2 退出");

}
		static int getInput(){
			Scanner sc = new Scanner(System.in);
			int u = -1;
	    try{
	        u = sc.nextInt();
}		catch(Exception a){
}
	    return u;  
}
		static void dispError(){
			System.out.println("输入错误，请重新输入");
}
		static boolean handle(int sell){
			switch (sell){
	     case 1: System.out.println("游戏开始");
	             break;
	     case 2: return true;  
	  default:dispError();
}
	  return false;
}
	public static void main(String[] args)
{
		boolean exit = false;
		while(!exit){
		disMain();  
		int sell =  getInput(); 
		exit = handle(sell);  
}
}
}
