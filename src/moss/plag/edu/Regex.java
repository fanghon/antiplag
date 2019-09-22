package moss.plag.edu;
import java.util.regex.*;
public class Regex
{
	Pattern pt=null;
	Matcher match=null;
	/**
	 * 
	 * @param RegString 正则表达式
	 * @param SearchString	要匹配的文本
	 */
	Matcher CreateRegex(String RegString,String SearchString)
	{
		pt=Pattern.compile(RegString);
		match=pt.matcher(SearchString);
		return match;
	}
	/**
	 * 
	 * @param index 匹配的文本的位置
	 * @return 返回文本
	 */
	String GetString(int index)
	{		
			int count=0;
			while(match.find())
			{
				if(index==count)
				{
					return match.group();
					}
				count++;
			}
			return "";
	}
	/**
	 * 
	 * @param index 匹配的文本位置
	 * @param subIndex 匹配的子文本位置
	 * @return 返回子文本
	 */
	String GetSubString(int index,int subIndex)
	{
		int count=0;
		while(match.find())
		{
			if(index==count)
			{
				return match.group(subIndex);
				}
			count++;
		}
		return "";
	}
	/**
	 * 
	 * @return 返回匹配的数量
	 */
	int GetCount()
	{
		int count=0;
		while(match.find())//判断是否有匹配
		{
			count++;
		}
		match=match.reset();
		return count;
	}
	public static void main(String[] args)
	{
		//测试子例
		Regex r=new Regex();
		r.CreateRegex("(\\d+)", "ass123456asd1");
		System.out.println(r.GetString(1));
		System.out.println(r.GetCount());
		
		/*
		 * 匹配斯坦福系统检测结果正则
		 * <TR><TD><A HREF=.*?html.*?>.*?src/(.*?)\((\d+%)\)</A>\n.*?<TD><A HREF=.*?html.*?>.*?src/(.*?)\((\d+%)\)</A>\n<TD ALIGN=right>(\d+)
		 */
	}
}
