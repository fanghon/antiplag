//输入成绩等级 A B C D，输出分数范围。  90分以上 A  70-90 B  60-70 C  <60  D
public class liuchenyi {

   public static void main(String[] args){
   char  degree = 'A';
   if (degree=='A'){
         System.out.println("你的分数在90分以上");
     } else if (degree=='B'){
          System.out.println("你的分数在70-90之间");
     }  else if(degree=='C'){
           System.out.println("你的分数在60-70之间");                                       
     }  else {
           System.out.println("你的分数在60分以下");      

      }
 
   }

 }