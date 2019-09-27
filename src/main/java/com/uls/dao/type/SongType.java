package com.uls.dao.type;

/**
 * This enum provides all the attributes of a song to avoid potential typos.
 * Created on 2019-09-15
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public enum SongType {

	ID("songId"),
	GENRE("genre"),
	TITLE("title"),
	ARTIST("artist"),
	LENGTH("length"),
	PERSON_FOREGIN_KEY("persons_username");
	
	private String type;
	
	/**
	 * Default constructor.
	 * 
	 * @param type, the type to set.
	 */
	SongType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
