package jplag.options;

import jplag.ExitException;

public class CommandLineOptionsExt extends CommandLineOptions {

	public CommandLineOptionsExt(String[] args) throws ExitException {
		super(args);
		initLangs();
	}

	public CommandLineOptionsExt(String[] args, String cmdInString) throws ExitException {
		super(args,cmdInString);
		initLangs();
	}
	//初始化语言集合，在此添加支持的语言
	void initLangs() {
		String[] langs= {"doc","jplag.doc.Language"};
		addLanguages(langs);
	}
	public String[] getLanguages() {
		return this.languages;
	}
	
	public void addLanguages(String[] langs) {
		String[] strs = new String[languages.length+langs.length];
		System.arraycopy(languages, 0, strs, 0, languages.length);
		System.arraycopy(langs, 0, strs, languages.length, langs.length);
		this.languages = strs ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] langs= {"doc","jplag.doc.Language"};
		try {
			CommandLineOptionsExt cmdop = new CommandLineOptionsExt(langs);
			cmdop.addLanguages(langs);
			for(String str:cmdop.getLanguages()) {
			   System.out.print(str+",");
			}
		} catch (ExitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
