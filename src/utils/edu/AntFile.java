package utils.edu;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Mkdir;
import org.apache.tools.ant.taskdefs.Move;
import org.apache.tools.ant.types.FileSet;



/**
 * 2013.12.19
 * ant 文件目录的建立、移动、压缩、删除等的操作
 * @author zq
 *
 */
public class AntFile {
	//解压缩文件,src是压缩文件，dest是解压的目标目录，成功返回1，失败返回-1
	public static int unzip(File src,File dest){
		int res = -1;
		try {
			Project prj=new Project(); 
			Expand expand=new Expand(); 
			expand.setProject(prj); 
			expand.setSrc(src); 
			expand.setOverwrite(true); 
			expand.setDest(dest); 
			expand.execute();
			res = 1;
		} catch (BuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		} 
		return res;
	}
	
	//删除目录及其下所有文件含子目录,成功返回1，失败返回-1
	public static int deleteDir(File f){
		int res = -1;
    	try {
			Project prj=new Project(); 
			Delete delete=new Delete(); 
			delete.setProject(prj); 
			delete.setDir(f); //可同时将子目录及所有文件删除 
			delete.execute();
			res = 1;
		} catch (BuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return res;
	}
	
	//删除文件,成功返回1，失败返回-1
	public static int deleteFile(File f){
		int res = -1;
    	try {
			Project prj=new Project(); 
			Delete delete=new Delete(); 
			delete.setProject(prj); 
			delete.setFile(f); //可同时将子目录及所有文件删除 
			delete.execute();
			res = 1;
		} catch (BuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return res;
	}
	// 文件扫描.f是扫描的路径，filter指定过滤的条件，返回符合条件的文件名（相对路径）数组, 失败为空则返回null
	// filter={"**/*.java"} ，
	public static String[] scanFiles(File f,String[] filter){
		String[] includeFiles = null;
		try {
			DirectoryScanner ds=new DirectoryScanner(); 
			
			ds.setBasedir(f); 
			ds.setIncludes(filter);  //**/*.java
			ds.scan(); 
			if(ds.getIncludedFilesCount()>0) { 	 
			 includeFiles=ds.getIncludedFiles(); 
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return includeFiles;
		 
	}
	//建立目录，成功返回1，失败返回-1
	public static int makeDir(File f){
		int res = -1;
		try {
			Project prj=new Project(); 
			Mkdir mkdir=new Mkdir(); 
			mkdir.setProject(prj); 
			mkdir.setDir(f); 
			mkdir.execute(); 
			res = 1;
		} catch (BuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	//复制文件，src被复制文件，dest目标路径，成功返回1，失败返回-1
	public static int copy(File src ,File dest){
		int res = -1;
		try {
			Project prj=new Project(); 
			Copy copy=new Copy(); 
			copy.setProject(prj); 
			copy.setFile(src); 
			copy.setTodir(dest); 
			copy.execute(); //将f1.txt文件copy到dir1中 
			res = 1;
		} catch (BuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
		
	}
	//文件改名
	public static int rename(File srcf,File destf){
		int res = -1;
		
		try {
			Project prj=new Project(); 
			Move copy=new Move(); 
			copy.setProject(prj); 
			copy.setFile(srcf); 
			copy.setTofile(destf); 
			copy.execute(); //将f1.txt文件更名为f2.txt中 
			res = 1;
		} catch (BuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	//将f下的zip文件解压到f下，原zip文件删除。
	public static void unzip(File f,boolean bdel){
		String[] filter={"*.zip"};  
		String[] files = AntFile.scanFiles(f, filter);
		if(files!=null){ //有zip文件
		  for(String file:files){	
			int res = AntFile.unzip(new File(f.getAbsoluteFile()+"\\"+file), f);
			if(res>0 && bdel){ //解压成功, 且允许删除，则删除原zip文件
				AntFile.deleteFile(new File(f.getAbsoluteFile()+"\\"+file));  //删除压缩文件
			}
		  }
		}
	}
		
	//将 指定目录下的文件复制到目标目录下
	public static void copy(File  srcdir,File desdir,String match){
		
		Project prj=new Project(); 
		Copy copy=new Copy(); 
    	copy.setProject(prj);
    	FileSet fileset=new FileSet(); 
		fileset.setProject(prj);
		fileset.setDir(srcdir);
	    if(match==null)
		  fileset.setIncludes("**/*.*");  //目录下的所有文件,含子目录的
	    else
	      fileset.setIncludes(match);	
	    
	    copy.addFileset(fileset);
		copy.setTodir(desdir);
		copy.execute();		
	}
	
	
	public static void main(String[] args){
		File src =new File("./demo/7/Selenium.zip"); //不支持rar文件的解压
		File dest=new File("./testdata/doccn/");
	//	AntFile.unzip(src, dest);
		
		 //AntFile.deleteFile(src); //pass test
		//AntFile.deleteDir(new File(dest.getAbsoluteFile()+"\\zhengchaota_atm"));
		//提取指定目录下的所有java文件，含子目录下的
		String[] filter={"**/*.doc"};  //"*.zip"
		String[] files = AntFile.scanFiles(dest, filter);
		if(files!=null){
			for(String str:files){
				System.out.println(str);
			}
		}
		
		//在当前路径下创建一个目录
	//	AntFile.makeDir(new File("./temp"));
		
	}
 
}
