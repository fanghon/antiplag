using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Sort
{
    class Program
    {
        
        private void MaoPao()
        {
            int temp;
            int[] arry = new int[17] { 1, 4, 6, 23, 2, 56, 7, 2, 46, 78, 89, 123, 34, 4, 6, 68, 9 };
            Console.WriteLine(arry.Length);
            for (int j = 1; j < arry.Length - 2; j++)
            {
                for (int i = 0; i < arry.Length - j; i++)
                {
                    if (arry[i] > arry[i + 1])
                    {

                        temp = arry[i];
                        arry[i] = arry[i + 1];
                        arry[i + 1] = temp;

                    }

                }
            }
            foreach (var item in arry)
            {
                Console.Write(item + " ");
            }
        
        }

        /// <summary>
        /// 快速排序法
        /// </summary>
        
        public static void Sort(ref int[] data, int start, int end)
        {
            if (start >= end) return;
            if (start + 1 == end)
            {
                if (data[start] > data[end])
                    Swap(ref data, start, end);

                return;
            }

            int indexL = start + 1, indexR = end;
            while (indexL < indexR)
            {
                // Get from left
                while (indexL <= end && data[start] >= data[indexL])
                    indexL++;

                // Get from right
                while (indexR > start && data[start] < data[indexR])
                    indexR--;

                if (indexL < indexR)
                {
                    Swap(ref data, indexR, indexL);
                }
            }

            if (indexL - 1 != start)
                Swap(ref data, start, indexL - 1);


            Sort(ref data, start, indexL - 2);
            Sort(ref data, indexL, end);
        }

        /// <summary>
        /// 交换
        /// </summary>
        /// <param name="data"></param>
        /// <param name="x"></param>
        /// <param name="y"></param>
        private static void Swap(ref int[] data, int x, int y)
        {
            var temp2 = data[x];
            data[x] = data[y];
            data[y] = temp2;
        }


        private static void QuikSort()
        {
            int[] arry = new int[8]{5,6,4,2,7,3,1,8};

            Sort(ref arry, 0,7);

            foreach (var item in arry)
            {
                Console.Write(item + " ");
            }
        }

        static void Main(string[] args)
        {
            //MaoPao()           //冒泡排序法
            QuikSort();          //快速排序法

        }
    }
}