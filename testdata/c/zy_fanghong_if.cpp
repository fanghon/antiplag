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
  //计算一个数的绝对值
  float abs = -2.8;
  if(abs<0){
     abs = -abs;    
  }
  printf("abs is %f \n",abs);
 
  value = 0;
  if(value = 2){  //丢失=问题
    printf("value is %d\n",value);
  }
  //避免上述错误的方法
  if(2==value){

  }



  printf("hello world\n");

  return 0;
}