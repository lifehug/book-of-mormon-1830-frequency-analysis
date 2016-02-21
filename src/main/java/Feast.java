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
import org.apache.commons.io.FileUtils;


class Feast{

	public static void main(String [] args) throws IOException{

		File f = new File("build/resources/main/BOM-1830.html");
		Document doc = Jsoup.parse(f, "UTF-8");
		Elements ele = doc.select("h3");
		Element l = ele.first().parents().first();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();;
		ArrayList<Chapter> chapters = new ArrayList<Chapter>();
		ArrayList<Book> books = new ArrayList<Book>();
		ArrayList<Verse> verses = new ArrayList<Verse>();
		int verse = 0; 
		int chapter = 0;
		BookOfMormon<Book> bom = null;
		String bookTitle = null;
		String chapterTitle = null;

		for(Element e : l.children()){
			// book
			if(e.tag() == Tag.valueOf("h2")){

				if(chapter != 0){
					Book<Chapter> b = new Book<Chapter>(chapters, bookTitle);
					String bk = gson.toJson(b);
					FileUtils.writeStringToFile(new File("build/resources/main/book/" + bookTitle +".json"), bk);
					books.add(b);
				} 
				chapter = 0;
				bookTitle = e.text();

			// chapter
			} else if(e.tag() == Tag.valueOf("h3")){
				
				if(verse != 0){
					Chapter<Verse> c = new Chapter<Verse>(verses, ++chapter, chapterTitle);
					String ch = gson.toJson(c);
					FileUtils.writeStringToFile(new File("build/resources/main/chapter/" + chapterTitle +".json"), ch);					
					chapters.add(c);
				} 
				verse = 0;
				chapterTitle = e.text();

				verses = new ArrayList<Verse>();
			// verse
			} else if(e.tag() == Tag.valueOf("p")){
				// put in paragraph
				if(!(e.text().matches("\\[[0-9]*\\]"))) verses.add(new Verse(e.text(), ++verse));
			
			}

		}

		// final addition
		chapters.add(new Chapter<Verse>(verses, ++chapter, chapterTitle));
		Chapter<Verse> c = new Chapter<Verse>(verses, chapter, chapterTitle);
		String ch = gson.toJson(c);
		FileUtils.writeStringToFile(new File("build/resources/main/chapter/" + chapterTitle +".json"), ch);
		chapters.add(c);
		Book<Chapter> b = new Book<Chapter>(chapters, bookTitle);
		String bk = gson.toJson(b);
		FileUtils.writeStringToFile(new File("build/resources/main/book/" + bookTitle +".json"), bk);
		books.add(b);
		bom = new BookOfMormon<Book>(books);
		String mormon = gson.toJson(bom);
		FileUtils.writeStringToFile(new File("build/resources/main/book_of_mormon_1980.json"), mormon);
		
	}
	
}