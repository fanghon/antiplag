
package jplag.doc;

import java.io.File;

import jplag.ProgramI;

/**
 * @Changed by fanghong 2019.12.1
 *  
 */
public class Language implements jplag.Language {

	private ProgramI program;

	private jplag.doc.Parser parser = new jplag.doc.Parser();

	public Language(ProgramI program) {
		this.program = program;
		this.parser.setProgram(this.program);
	}

	public int errorsCount() {
		return this.parser.errorsCount();
	}

	public String[] suffixes() {
		String[] res = { ".txt", ".doc", ".docx", ".pdf", ".html" };
		return res;
	}

	public String name() {
		return "Doc Parser";
	}

	public String getShortName() {
		return "doc";
	}

	public int min_token_match() {
		return 12;
	}

	public jplag.Structure parse(File dir, String[] files) {
		return this.parser.parse(dir, files);
	}

	public boolean errors() {
		return this.parser.getErrors();
	}

	public boolean supportsColumns() {
		return true;
	}

	public boolean isPreformated() {
		return false;
	}

	public boolean usesIndex() {
		return false;
	}

	public int noOfTokens() {
        return parser.tokenStructure.serial;
//		return jplag.text.TextToken.numberOfTokens();   // always returns 1 ....
	}

	public String type2string(int type) {
		return jplag.text.TextToken.type2string(type);
	}
}
