package com.example.eventapprentice;

import java.util.Date;


public class Event {
	String theme;
	Date date;
	
	public Event(){}
	
	public Event(String theme, Date date) {
		this.theme = theme;
		this.date = date;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
