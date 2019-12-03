package utils.edu;

/**
 * 在一个外部进程执行完之前你不能得到他的退出状态
 * 在你的外部程序开始执行的时候你必须马上控制输入、输出、出错这些流。
 * 你必须用Runtime.exec()去执行程序
 */
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

import data.plag.edu.SimData;
import jplag.ExitException;
import jplag.JPlag;
import jplag.Program;
import jplag.options.CommandLineOptions;
import jplag.options.CommandLineOptionsExt;
import moss.plag.edu.*;
import preprocess.plag.edu.TextExtractor;

public class WinCMD {
	String outfile = "out.txt"; 
	String mossoutfile = "mossout.txt";
	Moss moss = null;
			
	public static void main(String args[]) {
		/*
		 * if (args.length < 1) {
		 * System.out.println("USAGE java GoodWinRedirect <outputfile>");
		 * System.exit(1); }
		 */
/*		String cmd = "sim_java  -s -p -t 20  -o simout.txt  D:\\fh\\教学\\20141\\java\\学生作业\\test\\*.java";
		//String cmd = "cmd /c  perl mossnet -l java -m 10 -n 1000   ./test/*.java ";
		//cmd /c 表示执行完命令后关闭
		String str = "out.txt";
		try {
			FileOutputStream fos = new FileOutputStream(str);
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			// any error message?
			StreamGobbler errorGobbler = new StreamGobbler(
					proc.getErrorStream(), "ERROR");

			// any output?
			StreamGobbler outputGobbler = new StreamGobbler(
					proc.getInputStream(), "OUTPUT", fos);

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			// any error???
			int exitVal = proc.waitFor();
			System.out.println("ExitValue: " + exitVal);
			fos.flush();
			fos.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}*/
		
		WinCMD wc = new WinCMD();
		List<SimData> lists = new ArrayList<SimData>();
		String files = "./test/*.java";
		//int res = wc.execSim("java", 90, files, lists);
		int res = wc.execMoss("java", 0, files, lists);
		if(res==0){
		  for(int i=0;i<lists.size();i++){
			  System.out.println(lists.get(i).getSimilar()+" "+lists.get(i).getFile1()
					          +" "+ lists.get(i).getFile2());
		  }
			
		}
		
		
	}
	public Moss getMoss() {
		return moss;
	}
	public void setMoss(Moss moss) {
		this.moss = moss;
	}
	//清空out文件
	public void clearOut(File f){
   	if(f!=null && f.exists()){
		FileWriter fw = null;
		try {
			fw = new FileWriter(f,false);
			fw.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
   	}
	}
	//根据算法类型执行sim moss，成功返回0，失败返回-1输入参数错或>0执行命令错
	public int exec(String methodtype,String lang,int threshold,String files){
		int res = -1;
		List<SimData> lists = new ArrayList<SimData>();
		if("moss".equals(methodtype)){
			clearOut(new File(outfile));
			res = execMossJava(lang,threshold, files, lists);
		}else if("sim".equals(methodtype)){
			res = this.execSim(lang, threshold, files, lists);
		}else if("jplag".equals(methodtype)) {
			res = this.execJplag(lang, threshold, files, lists);
		}
		return res;
	}
	
   //将绝对路径转成linux风格的相对路径，相对于user.dir
	String pathconvert(String path){
		String res = null;
		String xdpath = System.getProperty("user.dir");//获取程序当前路径
		if(path.contains(xdpath)){
			try {
				path = path.substring(xdpath.length(),path.length());
				res = "."+path.replace("\\", "/");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return res;
	}
	//用tika读取给定目录下文档，以utf-8编码同名 txt文件形式写入一个临时文件夹下，返回该临时文件夹
	File preJplag(String dir) {
		File tempdir = new File("./tmp"+System.currentTimeMillis());
		int res = AntFile.makeDir(tempdir);
		String[] filter={"**/*.*"};  // 获取所有文件
		String[] filestrs = AntFile.scanFiles(new File(dir), filter);
		String name = null;
		for(String file:filestrs) {
			name = file.substring(0, file.indexOf('.'))+".txt";
			File outfile = new File(tempdir, name);
			String str = TextExtractor.getTxt(new File(dir, file));
			FileIO.saveFile(outfile, str, "utf-8");
		}
		
		return tempdir ;
	}
	
	void postJplag(File tempdir) {
		if(tempdir!=null) {
		  AntFile.deleteDir(tempdir);
		}
	}
	//调用Jplag的方法，对代码进行比较,成功返回0，失败返回-1
	public int execJplag(String lang,float threshold,String files,List<SimData> lists){
		  int res = -1;
		  File tmpf = null;
		  long t = System.currentTimeMillis();
		  try {		    
			String INPUT_FILE_FOLDER_NAME=files ;  //输入文件目录
		    
			if("doc".equals(lang)) {
		    	tmpf = preJplag(files);
		    	INPUT_FILE_FOLDER_NAME=tmpf.getAbsolutePath() ;  //输入文件目录
		    }
		    
			String jplagResultsFolderName="./jplagresult/";   //检查结果放在项目的子目录下
			// AntFile.deleteDir(new File(jplagResultsFolderName )); //先删除结果目录
			
			float MINIMUM_FILE_SIMILARITY = threshold ;
			String EXCLUDE_FILES = null ;  
			ArrayList<String> args = new ArrayList<String>();
			
			args.add("-l");
			if(!"java".equals(lang)) {
			   args.add(lang);    //设置语言类型参数，不加此参数，就使用默认值，为java19 
			}else {
			   args.add("java19");
			}
			args.add("-s"); //递归查询输入文件目录下的子目录
			args.add("-r"); //指定结果存放的路径
			args.add(jplagResultsFolderName);
			args.add("-m");  //设置相似度检查门限参数值
			args.add((int) (MINIMUM_FILE_SIMILARITY) + "%");
			if (EXCLUDE_FILES!=null) { // 设置被排除的文件
				args.add("-x");
				args.add(EXCLUDE_FILES);
			}
		//	args.add("-clustertype");   //对结果聚类，发现成组抄袭
		//	args.add("avr");
			
			args.add(INPUT_FILE_FOLDER_NAME);
			String[] toPass = new String[args.size()];
			toPass = args.toArray(toPass);
	    
            CommandLineOptionsExt options = new CommandLineOptionsExt(toPass, null);
                
            Program program = new Program(options);
                 
            System.out.println("jplag initialize ok "+program.get_commandLine());
            program.run();
            res = 0; //执行成功
          
		  } catch(Exception e) {
	    	  e.printStackTrace();
	      }finally {
	   		  postJplag(tmpf);
	      }
		  System.out.println("time:"+(System.currentTimeMillis()-t)+"ms");
	      return res ; 
	}
	

	
	
	
	//java客户端执行moss,参数lang语言，threshold相似度限值，files比较文件所在的目录， lists比较结果，成功返回0，失败返回-1，
	//无符合条件结果返回1
	public int execMossJava(String lang,float threshold,String files,List<SimData> lists){
		  int res = -1;
		  MossClient mc = new MossClient(new File(mossoutfile));  //仍将结果保存到mossout文件，
	      files =  pathconvert(files);
	      if(files==null)
	    	  return res;
		  File dir = new File(files);
	      res = mc.sendMoss(dir,lang);
	      if(res==0){  //上传成功
	 		 moss = new Moss();
			 res = moss.analyMoss(mossoutfile,threshold, lists);
			 if(res==0 && lists.size()>0){ //分析到有效数据
			    FileIO.saveFile(new File(outfile), lists,2,"from stanford:"+moss.getUrl()); //保存结果到out.txt文件
			 }
	      }
	      return res ; 
	}
	
	//执行moss,参数lang语言，threshold相似度限值，files比较的文件 lists比较结果，成功返回0，失败返回-1,无符合条件结果返回1
	public int execMoss(String lang,float threshold,String files,List<SimData> lists){
		int res = -1;
		
		 files = files+"\\*.*";
		
		 String cmd = "cmd /c perl  mossnet -l "+lang;
		 cmd = cmd + "  -m 10 -n 2000 ";
		 
		 String path = pathconvert(files);
		 if(path==null) //路径不对
			 return res;
		 cmd = cmd +"  "+path;
		 
		 
		 cmd = cmd +" "+" > mossout.txt";
		 
		
		 try {
				Runtime rt = Runtime.getRuntime();
				 Process proc = rt.exec(cmd);
				 res = proc.waitFor();  // 可能会长时间等待上传文件，执行失败会返回各种>0的值
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
			 
		// File file = new File("mossout.txt");
		// analySim(file,lang,lists);	
		if(res==0){ //上传执行成功
		 moss = new Moss();
		 res = moss.analyMoss(mossoutfile,threshold, lists);
		 if(res==0 && lists.size()>0){ //分析到有效数据,注意：如果没有超过门限的值，size也为0
		    FileIO.saveFile(new File(outfile), lists,2,"from stanford:"+moss.getUrl()); //保存结果到out.txt文件
		  }
		}
		return res;
	}

 //返回0：正常，-1输入参数错，>0，执行命令错
   public int  execSim(String lang,int threshold,String files,List<SimData> lists){
	 int res = -1;
	 String cmd = "";
	 files = files+"\\*.*";
	 if("java".equals(lang)){
		 cmd = "sim_java -s -p ";
	 }else if("c".equals(lang)){
		 cmd = "sim_c -s -p ";
	 }else
		 return res;
	 
	 if(threshold>0 && threshold<101){
	   cmd = cmd + " -t "+ threshold;
	 }else
		 return res;
	 
	 cmd = cmd + "  -o simout.txt ";
	 if(files!=null && files.length()>1)
	   cmd = cmd +" "+files;
	 
	 try {
		Runtime rt = Runtime.getRuntime();
		
		 Process proc = rt.exec(cmd);
		 res = proc.waitFor();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	 
	 
	 File file = new File("simout.txt");
	 analySim(file,lang,lists);
	 
	 
	 FileIO.saveFile(new File(outfile), lists,1,"from sim");
	 
	 return res;
   }

   public List<SimData> analySim(File file,String lang, List<SimData> lists ){
	 
	  try {
		if(file.exists()){
			FileReader isr = new FileReader(file);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] strs = Regex.extract("\\d{1,3}\\s+[%]",line);
				if(strs!=null && strs.length>0){
					try {
						String tmp = strs[0];
						tmp  = tmp.substring(0,tmp.indexOf("%")-1).trim();
						int baifen = Integer.parseInt(tmp);
						SimData sim = new SimData();
						sim.setSimilar(baifen);
						String[] temps = line.split(" ");
						for(int i=0;i<temps.length;i++){
							if(temps[i].indexOf(lang)>0){
							  if(sim.getFile1()==null)	
								sim.setFile1(temps[i]);
							  else
								sim.setFile2(temps[i]);
							}
								
						}
						lists.add(sim);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					}
					
					
				}	
				
			}
			  
			  
		  }
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
	  return lists;
	   
	   
   }
   

 
   
}
class StreamGobbler extends Thread {
	InputStream is;
	String type;
	OutputStream os;

	StreamGobbler(InputStream is, String type) {
		this(is, type, null);
	}

	StreamGobbler(InputStream is, String type, OutputStream redirect) {
		this.is = is;
		this.type = type;
		this.os = redirect;
	}

	public void run() {
		try {
			PrintWriter pw = null;
			if (os != null)
				pw = new PrintWriter(os);

			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (pw != null)
					pw.println(line);
				System.out.println(type + ">" + line);
			}
			if (pw != null)
				pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
