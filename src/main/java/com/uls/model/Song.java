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
	private int length;

	/**
	 * 
	 * @param id
	 * @param genre
	 * @param title
	 * @param artist
	 * @param length
	 */
	public Song(long id, String genre, String title, String artist, int length) {
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
	public int getLength() {
		return length;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Song [id=" + id + ", genre=" + genre + ", title=" + title + ", artist=" + artist + ", length=" + length
				+ "]";
	}

}
