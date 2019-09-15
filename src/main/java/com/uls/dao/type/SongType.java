package com.uls.dao.type;

public enum SongType {

	ID("songId"),
	GENRE("genre"),
	TITLE("title"),
	ARTIST("artist"),
	LENGTH("length"),
	PERSON_FOREGIN_KEY("persons_username");
	
	private String type;
	
	SongType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
