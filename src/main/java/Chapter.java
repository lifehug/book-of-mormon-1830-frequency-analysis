package com.dave.church;

import java.util.List;
import java.lang.Integer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

final public class Chapter<E> implements UrimAndThummim<String>{
	
	private List<E> verses;
	private Integer chapter;
	private String title;

	public Chapter(List<E> verses, Integer chapter, String title){
		this.verses = verses;
		this.chapter = chapter;
		this.title = title;
	}

	public String serialize(){
 		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(verses);
	}

	public Integer size(){
		return verses.size();
	}

}