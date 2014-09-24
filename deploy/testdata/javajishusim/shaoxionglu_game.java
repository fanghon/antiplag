import java.util.Scanner;
public class shaoxionglu_game
{

	static void displayMainMenu(){
		System.out.println("1 开始游戏");
		System.out.println("2 退出");
	}
	static int getInput(){
		Scanner sc = new Scanner(System.in);
		int i = -1;
		try{
			i = sc.nextInt();
		}catch(Exception e){
			
		}
		return i;
	}
	
	
	static boolean handle(int sel){
		switch (sel){
		case 1:System.out.println("游戏开始");
		       break;
		case 2:return true;
		
		}
		return false;
	}
	public static void main(String[] args)
	{
		boolean exit = false;
		while(!exit){
			displayMainMenu();
			int sel = getInput();
			exit = handle(sel);
		}
	}

}
