package utils.edu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import data.plag.edu.SimData;

public class FileIO {

	//按相似度由大到小保存结果到文件，type 2 原结果是由小到大，1原结果是由大到小
	public static void saveFile(File outfile,List<SimData> listsd,int type,String laiyuan){
		if(outfile.exists()){
			FileWriter fr = null;
			try {
				fr = new FileWriter(outfile);
				if(type==1){ //
				  for(int i=0;i<listsd.size()-1;i++){
					fr.write((i+1)+"  "+listsd.get(i).getSimilar()+"%  "+listsd.get(i).getFile1()
							+"  "+listsd.get(i).getFile2()+"\r\n");
				  }
				}else if(type==2){
					for(int i=listsd.size()-1;i>=0;i--){
						fr.write((listsd.size()-i)+"  "+listsd.get(i).getSimilar()+"%  "+listsd.get(i).getFile1()
								+"  "+listsd.get(i).getFile2()+"\r\n");
					  }  
			    }
				fr.write(laiyuan+"  "+new Date().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{		
				try {
					if(fr!=null)
						fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
