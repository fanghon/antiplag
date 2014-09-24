import java.util.*;

public class tanglei_Game{

static void displayMainMenu(){
 System.out.println("1 开始游戏");
 System.out.println("2 退出");
}
static int getInput(){
    Scanner c = new Scanner(System.in);
    int i = -1;
    try{
      i = c.nextInt();
    }catch(Exception e){
      
    }
    return i;  
 }
 static void SubGuessNumber(){
 
 }

 static void dispError(){
  System.out.println("输入错误，请重新输入");
 }
 

 static boolean handle(int sel){
   switch (sel){
     case 1: SubGuessNumber();
     System.out.println("开始猜数");
     case 2: return true;  
     default:dispError();
  
   }
   return false;
 
 }
 

public static void main(String[] args){
  boolean exit = false;
  while(!exit){
    displayMainMenu();  
    int sel =  getInput(); 
    exit = handle(sel); 
  }

}
  

}