package com.dave.church;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.Integer;

final public class Book<E> implements UrimAndThummim<String>{

	private List<E> chapters;
	private String title;
	private Integer documentCount = 0;

	public Book(List<E> chapters, String title){
		this.chapters = chapters;
		this.title = title;
	}

	public String serialize(){
 		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(chapters);
	}

	public Integer size(){
		return chapters.size();
	}

	// public Integer docs(){
	// 	if(documentCount == 0){
	// 		for(E c : chapters){
	// 			documentCount += c.size();
	// 		}
	// 	}
	// 	return documentCount;
	// }
	
}