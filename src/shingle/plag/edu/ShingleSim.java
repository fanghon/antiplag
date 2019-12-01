package shingle.plag.edu;
/**
 *  基于ShingleCloud 的中文文档相似比较程序 V1.0
 *  author:fanghong
 *  2013.9.20
 * 
 */
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import preprocess.plag.edu.TextExtractor;
import preprocess.plag.edu.Tokenizer;
import utils.edu.FileIO;

import data.plag.edu.SimData;
import de.tud.kom.stringmatching.shinglecloud.ShingleCloud;

public class ShingleSim {
    String dic = null;  //作业路径名
    float  threshold = 0.3f; //相似门限默认是0.3
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

	// 实现文件过滤接口，内部类方式,只允许doc、txt、docx、pdf类文件及子目录
	class Fileter implements FileFilter {
		@Override
		public boolean accept(File arg0) {
			// TODO Auto-generated method stub
			String fn = arg0.getName().toLowerCase();
			if (fn.endsWith(".doc")  //
					|| fn.endsWith(".txt")
					|| fn.endsWith(".docx") 
					|| fn.endsWith(".pdf") 
					|| arg0.isDirectory())
				return true;
			return false;
		}
	}

	public String  processZHText(File file){
		String resstr=null;
		try {
			String str = TextExtractor.getTxt(file); 
			//resstr = IKAnalyzer.segment(str,true," "); //智能分词、停用词过滤，空格分开
			resstr = Tokenizer.segment(str," ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resstr;
	}
	
	//比较指定目录下的文件相似度，返回超过指定门限值的相似文件对数
	void ShingleSimFiles(File file,float threshold){
		  List<String> liststrs = 	new ArrayList<String>();
		  //先读取文件、预处理
		  for(int i=0;i<filels.size();i++){
			  File file1 = filels.get(i);
			  String str1 = processZHText(file1);
			  liststrs.add(str1);
			
		  }
		
		
		  for(int i=0;i<filels.size();i++){
				if(liststrs.get(i)==null)
					continue;
				String str1 = liststrs.get(i);
				ShingleCloud sc = new ShingleCloud(str1);
				sc.setNGramSize(2); //能检查的最小相似token数
				sc.setMinimumNumberOfOnesInMatch(1);	
				sc.setSortMatchesByRating(false);
			   
			    sc.compile();
				for(int j=i+1;j<filels.size();j++){
				  String str2 = liststrs.get(j);
				  if(str2==null)
					  continue;
			       
			       sc.match(str2);
				   double score =(sc.getJaccardMeasureForShingles());
				   if(score>=threshold/100){
					 //  System.out.println(score+" "+filels.get(i).getName()+" "+
				       //                      filels.get(j).getName());	
					   SimData sim = new SimData();
					   sim.setSimilar((float)score*100);
					   sim.setFile1(filels.get(i).getName());
					   sim.setFile2(filels.get(j).getName());
					   listsd.add(sim);
				   }
				  
			    }//for
				    
				  
			  }
		
		
	}
	
	void report(){
		
		Collections.sort(listsd); //将列表元素按相似值由小到大排序
		//Arrays.sort(listsd.toArray());
		
		for(int i=listsd.size()-1;i>=0;i--){
			System.out.println(listsd.get(i).getSimilar()+" "+listsd.get(i).getFile1()
					+" "+listsd.get(i).getFile2());
		}
	}
	

		

	//设置命令行参数，成功0，失败-1
	int setParams(String[] args){
		int res = 0;
		if(args.length<2){
			System.out.println("usage:"+"java -jar ShingleSim.jar dic threshold");
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
				System.out.println("threshold is 0-100");
				return -1;
			}
				
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("threshold is 0.0-1.0");
			return -1;
		}
         return res;		
			
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			ShingleSim testsc = new ShingleSim();
			 
			int res = testsc.setParams(args);
			if(res>=0){
			 long st = System.currentTimeMillis();
			 File file = new File(testsc.dic);		
			 testsc.explore(file);
			
			 testsc.ShingleSimFiles(file,testsc.threshold);
			
			 testsc.report();
			 
			 File outfile = new File("out.txt");
			 FileIO.saveFile(outfile, testsc.listsd,2,"from fh"); //结果是由小到大排的
			 
			 System.out.println("handle documents:"+testsc.listsd.size());
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
