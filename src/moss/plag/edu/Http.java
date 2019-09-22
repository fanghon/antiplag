package moss.plag.edu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
public class Http {
	/**
	 * 
	 * @param url 要读取的URL
	 * @return 返回网页源码
	 */
	public String Get(String url) {
		String str,alls = "";
		try {
			URL s_url = new URL(url);
			InputStreamReader isr = new InputStreamReader(s_url.openStream());
			BufferedReader bfr = new BufferedReader(isr);
			while ((str = bfr.readLine()) != null) {
				alls+=str+"\n";
			}
			bfr.close();		
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException" + e);
		} catch (IOException ice) {
			System.out.println("IOException" + ice);
		}
		return alls;
	}
}
