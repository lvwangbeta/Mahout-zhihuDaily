package com.lvwang.mahout;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Word2Vec {
	
	public static final String RAW_FILE_DIR = "datas/raw/";	//分词后的文件
	
	
	public static void main(String[] args) {
		QueryHelper query = new QueryHelper();
		List<SingleNews> news_list = query.getAll();
		for(SingleNews news: news_list) {
			//System.out.println(news.getBody());
			if(news.getBody() == null) continue;
			Document doc = Jsoup.parse(news.getBody());
			Elements contents = doc.getElementsByClass("content");
			StringBuffer text = new StringBuffer();
			if(contents != null) {
				for(Element content : contents) {
					//System.out.println(content.text());
					text.append(content.text());
				}
			}
			wordSplit(news.getId(), text.toString());
		}
		query.close();
	}
	
	public static void wordSplit(int id, String text) {
		System.out.println("spliting id:" +id);
		Analyzer analyzer = new IKAnalyzer(true);
		StringReader reader = new StringReader(text);
		TokenStream ts = analyzer.tokenStream("", reader);  
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class); 
        
        try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter(RAW_FILE_DIR + id));
			while(ts.incrementToken()){  
			    //System.out.print(term.toString()+"|");  
			    writer.write(term.toString() + " ");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
	        analyzer.close();
	        reader.close();  
		}

	}
}
