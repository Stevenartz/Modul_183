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
			if (username != null) {
				if (conn != null) {
					PreparedStatement pstmt = conn.prepareStatement(queryManager.lookupAllSongsByUsername());
					pstmt.setString(1, username);
					LOGGER.debug("Setting parameter 1 with value: '{}'!", username);
					LOGGER.debug("Statement: '{}'!", pstmt);
					songList = mapper.mapResultSetToSongList(pstmt.executeQuery());
					if (songList != null) {
						LOGGER.info("Successfully found songs for person with username: '{}'!", username);
					} else {
						LOGGER.debug("songList after SQL Statement still null!");
					}
				} else {
					LOGGER.warn("Connection not active, connection is null!");
				}
			} else {
				LOGGER.debug("username cannot be null!");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			LOGGER.error("Failed to execute lookupAllSongsByUsername({}), Msg: '{}'!", username, sqle.getMessage());
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
			if (username != null && song != null) {
				if (conn != null) {
					PreparedStatement pstmt = conn.prepareStatement(queryManager.insertSongByUsername());
					pstmt.setString(1, username);
					pstmt.setString(2, song.getGenre());
					pstmt.setString(3, song.getTitle());
					pstmt.setString(4, song.getArtist());
					pstmt.setInt(5, song.getLength());
					LOGGER.debug("Setting parameter 1 with value: '{}'!", username);
					LOGGER.debug("Setting parameter 2 with value: '{}'!", song.getGenre());
					LOGGER.debug("Setting parameter 3 with value: '{}'!", song.getTitle());
					LOGGER.debug("Setting parameter 4 with value: '{}'!", song.getArtist());
					LOGGER.debug("Setting parameter 5 with value: '{}'!", song.getLength());
					LOGGER.debug("Statement: '{}'!", pstmt);
					if (pstmt.executeUpdate() == 1) {
						status = true;
						LOGGER.debug("Successfully inserted new song: '{}', for person with username: '{}'!", song, username);
					} else {
						LOGGER.debug("User tried to insert a new song, but no rows have been affected!");
					}
				} else {
					LOGGER.warn("Connection not active, connection is null!");
				}
			} else {
				LOGGER.debug("username or song cannot be null!");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			LOGGER.error("Failed to execute insertSongByUsername({}, {}), Msg: '{}'!", username, song, sqle.getMessage());
		}
		return status;
	}

}
