package com.dave.church;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import org.jsoup.parser.Tag;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import java.lang.StringBuffer;
import org.apache.commons.lang3.StringEscapeUtils;

class Feast{

	public static void main(String [] args) throws IOException{

		//journal1832();
		bookOfMormon1830();
		
	}

	public static void journal1832() throws IOException{

		StringBuffer string;
		JsonParser parser = new JsonParser();

			string = new StringBuffer();
			//String url = "http://www.josephsmithpapers.org/transcript/journal-1832-1834?nocache";
			String url = "http://www.josephsmithpapers.org/transcript/journal-1835-1836?nocache";			
		
			String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
	
			//parse html for the prophets writings
			Document doc = Jsoup.parseBodyFragment(json); 
			Elements ele = doc.select("div.indent.wasptag");

			System.out.println(ele.size());
			for(Element e : ele){
				string.append(e.text() + "\n");
			}

			String entry = string.toString();
	
			FileUtils.writeStringToFile(new File("build/resources/main/journal_1835_1836.txt"), entry);

	}

	public static void bookOfMormon1830() throws IOException{
		StringBuffer string;
		JsonParser parser = new JsonParser();

		for(int x = 11; x <= 594; x++){
			System.out.println(x);
			string = new StringBuffer();
			// create the proper api request
			String url = "http://www.josephsmithpapers.org/paper-summary/book-of-mormon-1830/" + x;
			String json = Jsoup.connect(url).ignoreContentType(true).execute().body();

			Document doc = Jsoup.parseBodyFragment(json); 
			Elements ele = doc.body().select("div.transcript__content--clear-text > div.indent.wasptag");
			for(Element e : ele){
				string.append(e.text() + " ");
			}

			String entry = string.toString().replaceAll("\\[p.*\\]","");
			FileUtils.writeStringToFile(new File("build/resources/main/book_of_mormon_1830.txt"), entry, "UTF-8", true);
		}
	}
	
}