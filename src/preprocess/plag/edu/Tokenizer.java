package preprocess.plag.edu;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;

public class Tokenizer {
    //将输入的字符串转成指定分隔符隔开的分词过的字符串
	public static String segment(String text,String sep) {
		   StringBuilder sb = new StringBuilder();
	       HanLP.Config.Normalization = true; //（繁体->简体，全角->半角，大写->小写）
	       List<Term> tokens = NotionalTokenizer.segment(text);//分词，去除停用词
	       for(Term token : tokens) {
	    	   sb.append(token.word+sep);
	       }
	       return sb.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	        HanLP.Config.Normalization = true; //（繁体->简体，全角->半角，大写->小写）
	        CustomDictionary.insert("爱听4G", "nz 1000");
	        String text = "i am from china.小区居民有的反对喂养流浪猫，而有的居民却”赞成“喂养这些小宝贝,i will go back Home，我愛聽４Ｇ";
	        System.out.println(text);
	        //精确分词
	        List<Term> tokens = HanLP.segment(text);
	        System.out.println(tokens);    // 停用词典位于data/dictionary/stopwords.txt，可以自行修改
	        for (Term token : tokens) {
	        	System.out.print("("+token.word+","+token.offset+","+token.length()+")");
	        	
	        }
	        System.out.println();
	        // 自动去除停用词,会丢失词在原文件中的位置信息
	        tokens = NotionalTokenizer.segment(text); 
	        System.out.println(tokens);    // 停用词典位于data/dictionary/stopwords.txt，可以自行修改
	        for (Term token : tokens) {
	        	System.out.print("("+token.word+","+token.offset+","+token.length()+")");
	        	
	        }
	        System.out.println();
	        // 自动断句+去除停用词
	        for (List<Term> sentence : NotionalTokenizer.seg2sentence(text))
	        {
	            System.out.println(sentence);
	        }
	        //英语中的停用词也会被去掉
	        String str = Tokenizer.segment(text," ");
	        System.out.println(str);  
	}

}
