package com.uls.model;

import java.util.GregorianCalendar;

/**
 * 
 * @author sulri
 *
 */
public class Song {

	private long id;
	private String genre, title, artist;
	private GregorianCalendar length;
	
	/**
	 * 
	 * @param id
	 * @param genre
	 * @param title
	 * @param artist
	 * @param length
	 */
	public Song(long id, String genre, String title, String artist, GregorianCalendar length) {
		super();
		this.id = id;
		this.genre = genre;
		this.title = title;
		this.artist = artist;
		this.length = length;
	}
	
	/**
	 * 
	 */
	public Song() {
		
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return the length
	 */
	public GregorianCalendar getLength() {
		return length;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Song [id=" + id + ", genre=" + genre + ", title=" + title + ", artist=" + artist + ", length=" + length.getTime()
				+ "]";
	}
	
}
