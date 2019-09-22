package moss.plag.edu;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.io.*;

import data.plag.edu.SimData;
public class Moss {
	String url; //上传文件后，返回结果中提取的url
	public static void main(String[] args) {
		System.out.println("请选择输出的排序方式:\n1、按相似度\n2、按匹配代码的行数\n3、综合指标");
		Moss m=new Moss();
		m.menu("mossout.txt");
	}
	void menu(String filename){
		String[] SortStyle=new String[]{"one","two","three"};
		ArrayList ResultList=new ArrayList();
		Scanner sc=new Scanner(System.in);
		int input=0;
		try {
			input=sc.nextInt();
		} catch (Exception e) {
			System.out.println("输入错误，请重新输入！");
			menu(filename);
		}
		if(input>=1 && input<=3){
			ResultList=analy(filename,SortStyle[input-1]);
			echoArrList(ResultList);
			}
			else{
			System.out.println("输入错误，请重新输入！");
		menu(filename);
		}
	}
	void echoArrList(ArrayList ArrList)
	{
		//System.out.println("作业文件名1               作业文件名2          相似度   匹配代码行  综合");
		for(int i=0;i<ArrList.size();i++)
			System.out.println(ArrList.get(i));
	}
	
	
	//分析上传结果文件，提取url，下载网页，提取信息，出问题返回-1，有结果返回0，无结果返回1
	 public int analyMoss(String filename,float threshold, List<SimData> lists ){
			int res = -1;
		    Text t = new Text();
			File f = new File(filename);
			if (!f.exists()){//判断文件是否存在
				System.out.println("请在程序运行目录下放入"+filename+"文件！");
				return -1;
			}else {
				
				String[] ArrStr = t.SplitString(filename, "\n");
				url = ArrStr[ArrStr.length - 1]; //最后一行是url
				if (url.matches("http://moss.stanford.edu/results/\\d+"))// 检查读出的URL是否匹配斯坦福的结果网页地址
				{
					Http http = new Http();
					String Result = http.Get(url);
					if (Result != "") //判断网络是否连通
					{
						Regex r = new Regex();
						int i,count;
					/*	Matcher m=r.CreateRegex(
								"<TR><TD><A HREF=.*?html.*?>.*?src/(.*?)\\((\\d+)%\\)</A>\\n.*?<TD><A HREF=.*?html.*?>.*?src/(.*?)\\((\\d+%)\\)</A>\\n<TD ALIGN=right>(\\d+)",
								Result);  */
						Matcher m=r.CreateRegex(
								"<TR><TD><A HREF=.*?html.*?>.*?[/|\\\\](.*?\\..*?)\\((\\d+)%\\)</A>\\n.*?<TD><A HREF=.*?html.*?>.*?[/|\\\\](.*?\\..*?)\\((\\d+)%\\)</A>\\n<TD ALIGN=right>(\\d+)",
								Result);

					
						while(m.find())
						{
						//	db.exeSql("insert into homework values('"+m.group(1)+"','"+m.group(3)+"',"+m.group(2)+","+m.group(5)+","+((Integer.parseInt(m.group(2))*Integer.parseInt(m.group(5)))/100.0)+")");	
							SimData sd = new SimData();
							sd.setFile1(m.group(1));
							sd.setFile2(m.group(3));
							float simf1 = 0.0f;
							float simf2 = 0.0f;
							float maxsim = 0.0f;
							try {
								simf1 = Float.valueOf(m.group(2)); //取第一个相似百分数
								String strtemp = m.group(4);
								strtemp = m.group(5);
								simf2 = Float.valueOf(m.group(4));
								maxsim = simf1>simf2?simf1:simf2;
								sd.setSimilar(maxsim); //设置两个相似度中最大的
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(maxsim>=threshold) //大于门限，才保留
							   lists.add(sd);
						}
						
					} else{
						System.out.println("请检查网络是否连通！");
						return -1;
					}
				}
			}		 
		 
		if(lists.size()>0){  //有至少1个结果
			Collections.sort(lists);  //排序
			res = 0;
		}else if(lists.size()==0) { //没有符合门限值条件的结果
			res = 1;  
		}
	    return res;
	 }
	public ArrayList analy(String filename,String sortStyle)
	{
		ArrayList ResultList=new ArrayList();
		Text t = new Text();
		File f = new File(filename);
		if (!f.exists())//判断文件是否存在
			System.out.println("请在程序运行目录下放入"+filename+"文件！");
		else {
			String[] ArrStr = t.SplitString(filename, "\n");
			String Url = ArrStr[ArrStr.length - 1];
			if (Url.matches("http://moss.stanford.edu/results/\\d+"))// 检查读出的URL是否匹配斯坦福的结果网页地址
			{
				Http http = new Http();
				String Result = http.Get(Url);
				if (Result != "") //判断网络是否连通
				{
					Regex r = new Regex();
					int i,count;
					Matcher m=r.CreateRegex(
							"<TR><TD><A HREF=.*?html.*?>.*?src/(.*?)\\((\\d+)%\\)</A>\\n.*?<TD><A HREF=.*?html.*?>.*?src/(.*?)\\((\\d+%)\\)</A>\\n<TD ALIGN=right>(\\d+)",
							Result);
					DataBase db=new DataBase();
					if(db.Connection()){
					db.exeSql("delete from homework");
					while(m.find())
					{
						db.exeSql("insert into homework values('"+m.group(1)+"','"+m.group(3)+"',"+m.group(2)+","+m.group(5)+","+((Integer.parseInt(m.group(2))*Integer.parseInt(m.group(5)))/100.0)+")");	
					}
					ResultList=db.QuerySql("select * from "+sortStyle);
					db.close();
					}
					else
					{
						System.out.println("数据库文件缺失，程序无法运行！");
					}
				} else
					System.out.println("请检查网络是否连通！");
			}
		}
		return ResultList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}