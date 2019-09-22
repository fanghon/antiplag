package preprocess.plag.edu;

import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.cfg.*;
import org.wltea.analyzer.core.Lexeme;
/**
 * 2013.7.25  依据使用手册
 * 1 加入IKAnalyzer2012_u6.jar ,jar 包中已经自带字典
 * 2 将IKAnalyzer.cfg.xml、stopword.dic拷贝到项目根路径下
 * 3 编码测试
 * 貌似没有去掉停用词 a
 * 性能：cpu负荷及内存占用不大
 * 进一步测试扩展词库、停用词
 * 将IKAnalyzer.cfg.xml、stopword.dic拷到\bin目录下
 * 原理：主要基于字典的字符串匹配切词，智能方式是精确分词，去掉各种标点符号,英文统一小写
 */
public class IKAnalyzer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 Configuration   cfg = DefaultConfig.getInstance();
	 System.out.println("main dic:"+cfg.getMainDictionary());
	 System.out.println("ext dic:"+cfg.getExtDictionarys());
	 System.out.println("stopword dic:"+cfg.getExtStopWordDictionarys());
	 
	 
     IKSegmenter ik = new IKSegmenter(new StringReader("a Hello " +
     		" 中华人民共和国      'world java('&quot2013年（）：,:  19:28 " +
     		"Ansj中文分词是一个真正的ict的实现.并且加入了自己的一些数据结构和算法的分词.实现了高效率和高准确率的完美结合!" ),true);
     Lexeme le = null;
    
     try {
		while((le=ik.next())!=null){
			 System.out.print(le.getLexemeText()+"|" );
		 }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
     System.out.println(ik.toString());
	}
	public static String segment(String str,boolean bsmart){
		
		return segment(str,bsmart,"");
	}
     public static String segment(String str,boolean bsmart,String split){
       if(str!=null){	 
    	 IKSegmenter ik = new IKSegmenter(new StringReader(str),bsmart);
	     Lexeme le = null;
	     StringBuffer sb = new StringBuffer();
	     try {
			while((le=ik.next())!=null){
				sb.append(le.getLexemeText()+split);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return sb.toString(); 
     }
       return null;
     }
}
