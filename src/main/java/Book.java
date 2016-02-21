package com.dave.church;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.Integer;

final public class Book<E extends UrimAndThummim> implements UrimAndThummim{

	private List<E> chapters;
	private String title;

	public Book(List<E> chapters, String title){
		this.chapters = chapters;
		this.title = title;
	}

	public Integer size(){
		return chapters.size();
	}

	public String name(){
	 	return title;
	}
	
}