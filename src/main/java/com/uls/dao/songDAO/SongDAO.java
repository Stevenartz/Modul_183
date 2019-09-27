package com.uls.dao.songDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uls.dao.factory.ConnectionFactory;
import com.uls.dao.manager.QueryManager;
import com.uls.dao.mapper.Mapper;
import com.uls.model.Song;

/**
 * This class executes all database queries specific to the songs.
 * Created on 2019-09-15
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public class SongDAO implements ISongDAO {

	private QueryManager queryManager;
	private Mapper mapper;

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * Default constructor.
	 */
	public SongDAO() {
		queryManager = new QueryManager();
		mapper = new Mapper();
	}

	/**
	 * Looks up all songs based on the username.
	 * 
	 * @param username, the username for looking up the songs.
	 * @return null or a list with the songs based on the username.
	 */
	@Override
	public List<Song> lookupAllSongsByUsername(String username) {
		Connection conn = ConnectionFactory.getConnection();
		List<Song> songList = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(queryManager.lookupAllSongsByUsername());
			pstmt.setString(1, username);
			songList = mapper.mapResultSetToSongList(pstmt.executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return songList;
	}

	/**
	 * Inserts a song to a person.
	 * 
	 * @param username to save the song to the right person.
	 * @param song, the song to save
	 * @return true if a row has been affected, false if not.
	 */
	@Override
	public boolean insertSongByUsername(String username, Song song) {
		Connection conn = ConnectionFactory.getConnection();
		boolean status = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(queryManager.insertSongByUsername());
			pstmt.setString(1, username);
			pstmt.setString(2, song.getGenre());
			pstmt.setString(3, song.getTitle());
			pstmt.setString(4, song.getArtist());
			pstmt.setInt(5, song.getLength());
			if (pstmt.executeUpdate() == 1) {
				status = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		System.out.println(queryManager.insertSongByUsername());

		System.out.println("inserting song: " + song + " with username: " + username);

		return status;
	}

}
