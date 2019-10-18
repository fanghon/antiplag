

public class wangdongyue{

   public static void main(String[] args){
     //求3个数中的最大值
     int a = 19 ;
     int b = 18 ;
     int c = 20;
     //1 找出其中两个数中的最大值
      int max = a>b?a:b;
     //2 将1中的最大值和第3个数比较，求取最大值
      max = max > c ? max : c ;
      System.out.println(max);

      if (a>b ){
         max = a ;
     } else {
         max = b ;
     }
      if (max < c) {
         max = c ;
      }
      System.out.println(max);

      //判断成绩等级，输入成绩，90分以上 A  70-90 B  60-70 C  <60  D
     int score =55 ;
     if (score>=90){
         System.out.println('A');
     } else if (score>=70 ){
          System.out.println('B');
     }  else if(score >= 60){
           System.out.println('C');                                       
     }  else {
           System.out.println('D');      

      }
 
      // 输入字符 A B C D，输出分数范围?
      char  degree = 'B';
      if (degree=='A'){
         System.out.println("score>=90");
     } else if (degree=='B'){
          System.out.println("score>=70");
     }  else if(degree=='C'){
           System.out.println("score >= 60");                                       
     }  else{
           System.out.println("score<60"); 
     }
   }  //main


}  //class