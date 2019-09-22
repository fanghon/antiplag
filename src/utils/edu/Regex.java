package utils.edu;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static String[] extract(String regex, String str) {
		ArrayList<String> regs = new ArrayList();

		int i = 0;
		Pattern p = Pattern.compile(regex);
		Matcher m = null;
		while (true)

		{
			if (str == null)
				break;
			m = p.matcher(str);

			while (m.find()) {
				regs.add(m.group().toString());
				i++;
			}
			break;
		}
		String[] ress = new String[regs.size()];
		regs.toArray(ress);
		return ress;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
