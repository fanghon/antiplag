package jplag.doc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import jplag.InputState;
import jplag.ParserToken;
import jplag.Structure;
import preprocess.plag.edu.TextExtractor;
import utils.edu.FileIO;

/**
 * @Changed by Emeric Kwemou 29.01.2005
 *  
 */
public class Parser extends jplag.Parser implements jplag.TokenConstants {

	protected TokenStructure tokenStructure = new TokenStructure();

	private Structure struct;

	private String currentFile;

	public jplag.Structure parse(File dir, String files[]) {
		struct = new Structure();
		errors = 0;
		for (int i = 0; i < files.length; i++) {
			//getProgram().print("", "Parsing file " + files[i] + "\n");
			if (!parseFile(dir, files[i]))
				errors++;
			struct.addToken(new DocToken(FILE_END, files[i], this));
		}

		Structure tmp = struct;
		struct = null;
		this.parseEnd();
		return tmp;
	}

	public boolean parseFile(File dir, String file) {
		
		try {
		 currentFile = file;
         String[] strs = FileIO.readFile(new File(dir, file),"utf-8");
		 for(int line=0;line<strs.length;line++) {			
			if(strs[line].trim().length()<1) {   //¹ýÂËµô¿ÕÐÐ
				continue ;
			}
			List<Term> tokens = HanLP.segment(strs[line]);  
			int col = 1;
			for(int j=0;j<tokens.size();j++) {
				Term token = tokens.get(j);
				struct.addToken(new DocToken(token.word, currentFile, 
						line+1, col, token.length(), this));
				
				col = col + tokens.get(j).word.length()+1;
			}
			
		}
			
		} catch (Exception e) {
			getProgram().addError("Parsing Error in '" + file + e.getMessage());
			return false;
		}
		return true;
	}

	private boolean runOut = false;

	public void outOfSerials() {
		if (runOut)
			return;
		runOut = true;
		errors++;
		program.print("ERROR: Out of serials!", null);
        System.out.println("jplag.doc.Parser: ERROR: Out of serials!");
	}

	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println("Only one parameter allowed.");
			System.exit(-1);
		}
		Parser parser = new Parser();
		System.out.println(new File(".").getAbsolutePath());
		jplag.Structure struct = parser.parse(new File("."), args);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
			int lineNr = 1;
			int token = 0;
			String line;
			while ((line = reader.readLine()) != null) {
				if (token < struct.size()) {
					boolean first = true;
					while (struct.tokens[token] != null && struct.tokens[token].getLine() == lineNr) {
						if (!first)
							System.out.println();
						DocToken tok = (DocToken)struct.tokens[token];
						System.out.print(DocToken.type2string(tok.type) + " (" + tok.getLine() + "," + tok.getColumn() + ","
								+ tok.getLength() + ")"+tok.getText()+"\t");
						first = false;
						token++;
					}
					if (first)
						System.out.print(" \t");
				} else
					System.out.print(" \t");
				System.out.println(line);
				lineNr++;
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
