package imghash.plag.edu;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.kilianB.hash.Hash;
import com.github.kilianB.hashAlgorithms.HashingAlgorithm;
import com.github.kilianB.hashAlgorithms.PerceptiveHash;
import com.github.kilianB.hashAlgorithms.RotPHash;

import data.plag.edu.SimData;
import utils.edu.FileIO;

public class ImageSim {
	 String dic = null;  //作业路径名
     float  threshold = 0.8f; //图片相似门限默认是0.8
     List<File> filels = new ArrayList<File>(); //需要比较的文件
	 List<SimData> listsd = new ArrayList<SimData>(); //文件比较的结果

		 //将给定目录下的符合扩展名要求的文件（含子目录下的），提取出来
		public void explore(File file) {
			if (file != null && file.isDirectory()) {
				File[] files = file.listFiles(new Fileter());
				for (File tempfile : files) {
					if (tempfile.isDirectory()) {
						explore(tempfile);// 如果是子目录，则递归调用
					} else {
						filels.add(tempfile);

					}
				}

			}
		}

		// 实现文件过滤接口，内部类方式,只允许指定扩展名的文件及子目录
		class Fileter implements FileFilter {
			@Override
			public boolean accept(File arg0) {
				// TODO Auto-generated method stub
				String fn = arg0.getName().toLowerCase();
				if (fn.endsWith(".png")  //
						|| fn.endsWith(".jpg")
						|| fn.endsWith(".jpeg") 
						|| fn.endsWith(".gif") 
						|| fn.endsWith(".bmp")
						|| fn.endsWith(".tiff")
						|| arg0.isDirectory())
					return true;
				return false;
			}
		}

		
		
		//比较指定目录下的图片文件相似度，返回超过指定门限值的相似文件对数
		void ImageSimFiles(float threshold){	
			  List<Hash> listhashs = 	new ArrayList<Hash>();
			  HashingAlgorithm hasher = new PerceptiveHash(128);  //128位phash
			  // HashingAlgorithm hasher = new RotPHash(256);  //能判断旋转相似图片
			   //先读取图片文件、生成hash值			
			   for(int i=0;i<filels.size();i++){
				  File file = filels.get(i);
				  try {
				    Hash hash = hasher.hash(file);				  
				    listhashs.add(hash);
				  }catch(Exception e) {
					  e.printStackTrace();
					  continue ;
				  }				
			   }
			
			   for(int i=0;i<filels.size();i++){				
					
					Hash hash1 = listhashs.get(i);
					
					for(int j=i+1;j<filels.size();j++){
					   
					   Hash hash2 = listhashs.get(j);;
					   
					   double score = hash1.normalizedHammingDistance(hash2);
					   if(score<1-threshold/100){   //score越小越相似
						 //  System.out.println(score+" "+filels.get(i).getName()+" "+
					       //                      filels.get(j).getName());	
						   SimData sim = new SimData();
						   sim.setSimilar((float)(1-score)*100);
						   sim.setFile1(filels.get(i).getName());
						   sim.setFile2(filels.get(j).getName());
						   listsd.add(sim);
					   }
					  
				    }//for
				   
				  }			
			
		}
		
		//将比较结果由大到小输出
		void report(){
			
			Collections.sort(listsd); //将列表元素按相似值由小到大排序
			//Arrays.sort(listsd.toArray());
			
			for(int i=listsd.size()-1;i>=0;i--){  //由大到小输出
				System.out.println(listsd.get(i).getSimilar()+" "+listsd.get(i).getFile1()
						+" "+listsd.get(i).getFile2());
			}
		}
		

			

		//设置命令行参数，成功0，失败-1
		int setParams(String[] args){
			int res = 0;
			if(args.length<2){
				System.out.println("usage:"+"java -jar ImageSim.jar dic threshold");
				return -1;
			}
			this.dic = args[0];
			File dic = new File(this.dic);
			if(!dic.isDirectory()){
			  System.out.println("dic is not exsit!");
			  return  -1;
			}
			try {
				this.threshold = Float.valueOf(args[1]);
				if(this.threshold<0 || this.threshold>100){
					System.out.println("threshold is outof 0-100");
					return -1;
				}
					
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("threshold is 0-100 number");
				return -1;
			}
	         return res;		
				
		}
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			
			try {
				ImageSim testsc = new ImageSim();
				 
				int res = testsc.setParams(args);
				if(res>=0){
				 long st = System.currentTimeMillis();
				 File file = new File(testsc.dic);		
				
				 testsc.explore(file);  //读取并计算图片文件hash值
				
				 testsc.ImageSimFiles(testsc.threshold); //根据hash值比较相似值
				
				 testsc.report();  //生成结果
				 
				 File outfile = new File("out.txt");
				 FileIO.saveFile(outfile, testsc.listsd,2,"from fh"); //结果是由大到小排的
				 
				 System.out.println("handle documents:"+testsc.filels.size());
				 System.out.println("time:"+(System.currentTimeMillis()-st));
				}else{
					System.out.println("参数错误");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}


}
