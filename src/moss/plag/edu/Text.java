package moss.plag.edu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Text
{
	/**
	 * 
	 * @param FilePath 文件路径
	 * @param SplitStr 分割符
	 * @return 返回分割后的字符串数组
	 */
	String[] SplitString(String FilePath,String SplitStr){
		File f=new File(FilePath);
		String[] arrurl=null;
		if(!f.exists())//判断文件是否存在
			return arrurl;
		try
		{
			FileReader fr=new FileReader(f);
			char[] cbuf=new char[(int)f.length()];
			fr.read(cbuf);
			String t=new String(cbuf);
			arrurl=t.split(SplitStr);
			fr.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return arrurl;
	}
	public static void main(String[] args) {
		//测试子例
		Text txt=new Text();
		String[] StrArr;
		StrArr=txt.SplitString("C:\\Documents and Settings\\Administrator\\桌面\\1.txt", "\n");
		System.out.println(StrArr[4]);
	}
}
