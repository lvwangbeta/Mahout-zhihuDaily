package com.lvwang.mahout;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerTest {
	
	public static void main(String[] args) throws IOException {

		String text = "";
		Analyzer analyzer = new IKAnalyzer(false);
		StringReader reader = new StringReader(text);
		TokenStream ts = analyzer.tokenStream("", reader);  
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class); 
        while(ts.incrementToken()){  
            System.out.print(term.toString()+"|");  
        }
        analyzer.close();
        reader.close();  

 		
	}
}
