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
 * 
 * @author sulri
 *
 */
public class SongDAO implements ISongDAO {

	private QueryManager queryManager;
	private Mapper mapper;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 */
	public SongDAO() {
		queryManager = new QueryManager();
		mapper = new Mapper();
	}

	/**a
	 * 
	 */
	@Override
	public List<Song> lookupAllSongsByUsername(String username) {
		Connection conn = ConnectionFactory.getConnection();
		List<Song> songList = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(queryManager.lookupAllSongsById());
			pstmt.setString(1, username);
			songList = mapper.mapResultSetToSongList(pstmt.executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return songList;
	}

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
