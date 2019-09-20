package com.uls.security;

public enum Regex {

	SONG_GENRE("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"),
	SONG_TITLE("^[a-zA-ZäöüÄÖÜ0-9]+(([',. -][a-zA-ZäöüÄÖÜ 0-9])?[a-zA-ZäöüÄÖÜ0-9]*)*$"),
	SONG_ARTIST("^[a-zA-ZäöüÄÖÜ0-9]+(([',. -][a-zA-ZäöüÄÖÜ 0-9])?[a-zA-ZäöüÄÖÜ0-9]*)*$"),
	SONG_LENGTH("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$");
	
	private String type;
	
	Regex(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
