#include  <stdio.h>

int main(){
  //判断一个数的奇、偶性
  int value = 8;
  if((value % 2) == 0){
     printf("value is oushu \n");
  }else{
     printf("value is jishu \n");
  }
  // 判断一个数是正、0、负
  value = 10 ;
  if(value == 0){  
     printf("value is 0\n"); 
  }else if(value < 0){
     printf("value is fushu\n");
  }else {
     printf("value is zhengshu \n");
  }
 


  

  return 0;
}