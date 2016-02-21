package com.dave.church;

import java.util.List;
import java.lang.Integer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

final public class BookOfMormon<E extends UrimAndThummim> implements UrimAndThummim{

	private List<E> books;
	private Integer published = 1830;
	private String title = "The Book of Mormon";

	public BookOfMormon(List<E> books){
		this.books = books;
	}

	public Integer size(){
		return books.size();
	}

	public String name(){
	 	return title;
	}

}