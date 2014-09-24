import java.util.Scanner;
public class qinchen_Game
{
       static int n=0;
       static void ini(){
    	   System.out.println("1.开始游戏");
           System.out.println("2.退出");
       Scanner  sc=new  Scanner( System.in);
       try{
    	   
    	   
    	   n=sc.nextInt();
       }catch  (Exception e){   
    	   
               }
       suiji(n);}
       static void suiji(int f){
    	   switch(f)
    { case 1:System.out.println("开始游戏");   	   
          ini();
          break;
    case 2 :Runtime.getRuntime().exit(0);
    break;
    default:System.out.println("输入错误，请重新输入");
      ini();	   
    }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
       ini();
	}

}
