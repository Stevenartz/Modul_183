package com.uls.dao.songDAO;

import java.util.List;

import com.uls.model.Song;

public interface ISongDAO {

	List<Song> lookupAllSongsByUsername(String username);
	boolean insertSongByUsername(String username, Song song);
	
}
