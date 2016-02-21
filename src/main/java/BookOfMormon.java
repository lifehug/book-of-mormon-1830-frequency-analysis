package com.dave.church;

import java.util.List;
import java.lang.Integer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

final public class BookOfMormon<E> implements UrimAndThummim<String>{

	private List<E> books;
	private Integer published = 1830;
	private String title = "The Book of Mormon";
	private Integer bookCount = 0;
	private Integer documentCount = 0;
	private Integer verseCount = 0;

	public BookOfMormon(List<E> books){
		this.books = books;
	}

	public String serialize(){
 		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(books);
	}

	public Integer size(){
		return books.size();
	}

	// public Integer chapters(){
	// 	if(documentCount == 0){
	// 		for(E b : books) documentCount += b.size();
	// 	}
	// 	return documentCount;

	// }

	// public Integer verses(){
	// 	if(verseCount == 0){
	// 		for(E b : books){
	// 			for(Chapter c : b){
	// 				verseCount += c.docs();
	// 			}
	// 		}
	// 	}

	// 	return verseCount;
	// }

}