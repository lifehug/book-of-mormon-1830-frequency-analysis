package com.dave.church;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import org.jsoup.parser.Tag;
import java.util.ArrayList;


class Feast{

	public static void main(String [] args) throws IOException{

		File f = new File("build/resources/main/BOM-1830.html");
		Document doc = Jsoup.parse(f, "UTF-8");
		Elements ele = doc.select("h3");
		Element l = ele.first().parents().first();
		

		ArrayList<Chapter> chapters = new ArrayList<Chapter>();
		ArrayList<Book> books = new ArrayList<Book>();
		ArrayList<Verse> verses = new ArrayList<Verse>();
		int verse = 0; 
		int chapter = 0;
		BookOfMormon bom = null;
		String bookTitle = null;
		String chapterTitle = null;

		for(Element e : l.children()){
			// book
			if(e.tag() == Tag.valueOf("h2")){

				if(chapter != 0) books.add(new Book(chapters, bookTitle));
				chapter = 0;
				bookTitle = e.text();

			// chapter
			} else if(e.tag() == Tag.valueOf("h3")){
				
				if(verse != 0) chapters.add(new Chapter(verses, ++chapter, chapterTitle));
				verse = 0;
				chapterTitle = e.text();

				verses = new ArrayList<Verse>();
			// verse
			} else if(e.tag() == Tag.valueOf("p")){
				// put in paragraph
				verses.add(new Verse(e.text(), ++verse));
			
			}

		}

		// final addition
		chapters.add(new Chapter(verses, ++chapter, chapterTitle));
		books.add(new Book(chapters, bookTitle));
		bom = new BookOfMormon(books);

		
	}
	
}