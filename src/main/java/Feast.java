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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import java.lang.StringBuffer;

class Feast{

	public static void main(String [] args) throws IOException{

		journal1832();
		bookOfMormon1830();
		
	}

	public static void journal1832() throws IOException{
		// 2 - 106
		StringBuffer string;
		JsonParser parser = new JsonParser();
		for(int page = 2; page <= 94; page++){
			string = new StringBuffer();
			// create the proper api request
			String url = "http://josephsmithpapers.org/old/api/media-viewer/page?uri=journal-1832-1834&page=" + page + "&pageonly=true&highlight=";
			String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
			// get the html
			JsonObject o = parser.parse(json).getAsJsonObject();
			JsonElement html = o.getAsJsonObject("page").getAsJsonObject("paperSummaryModel").getAsJsonObject("transcriptionModel").get("clear");
			
			//parse html for the prophets writings
			Document doc = Jsoup.parseBodyFragment(html.getAsString()); 
			Elements ele = doc.select("div.indent.wasptag");
			for(Element e : ele){
				string.append(e.text() + " ");
			}

			String entry = string.toString();
			FileUtils.writeStringToFile(new File("build/resources/main/journal_1832_1834/" + page + ".txt"), entry);
		}
	}

	public static void bookOfMormon1830() throws IOException{
		StringBuffer string;
		JsonParser parser = new JsonParser();
		// 11 - 594
		for(int page = 11; page <= 594; page++){
			string = new StringBuffer();
			// create the proper api request
			String url = "http://josephsmithpapers.org/old/api/media-viewer/page?uri=book-of-mormon-1830&page=" + page + "&pageonly=true&highlight=";
			String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
			// get the html
			JsonObject o = parser.parse(json).getAsJsonObject();
			JsonElement html = o.getAsJsonObject("page").getAsJsonObject("paperSummaryModel").getAsJsonObject("transcriptionModel").get("clear");
			
			//parse html for the book of mormon
			Document doc = Jsoup.parseBodyFragment(html.getAsString()); 
			Elements ele = doc.select("div.indent.wasptag");
			for(Element e : ele){
				string.append(e.text() + " ");
			}
			//get rid of page numbers
			String entry = string.toString().replaceAll("\\[p.*\\]","");
			FileUtils.writeStringToFile(new File("build/resources/main/book_of_mormon_1830/" + page + ".txt"), entry);
		}
	}
	
}