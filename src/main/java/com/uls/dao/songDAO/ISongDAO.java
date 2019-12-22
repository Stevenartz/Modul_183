package com.uls.dao.songDAO;

import java.util.List;

import com.uls.model.Song;

/**
 * This class serves an interface for the SongDAO class to restrict the methods.
 * Created on 2019-09-15
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public interface ISongDAO {

	List<Song> lookupAllSongsByUsername(String username);
	boolean insertSongByUsername(String username, Song song);
	boolean deleteSongById(Long songId);
	
}
