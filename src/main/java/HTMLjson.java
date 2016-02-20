package com.dave.church;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import org.jsoup.parser.Tag;


final class HTMLjson{

	public static void main(String [] args) throws IOException{

		File f = new File("build/resources/main/BOM-1830.html");
		Document doc = Jsoup.parse(f, "UTF-8");
		Elements ele = doc.select("h3");
		Element l = ele.first().parents().first();
		boolean chapter = false;
		int verse = 0; 
		for(Element e : l.children()){
			if(e.tag() == Tag.valueOf("h3")){
				chapter = true;
				verse = 0;
				// put in chapter header	
				System.out.println(e.text());
			} else if(e.tag() == Tag.valueOf("p") && chapter == true){
				// put in paragraph
				System.out.println(++verse);
			} else{
				chapter = false;
				verse = 0;
			}
		}
	}
	
}