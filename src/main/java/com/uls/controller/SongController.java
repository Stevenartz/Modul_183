package com.uls.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uls.dao.songDAO.ISongDAO;
import com.uls.dao.songDAO.SongDAO;
import com.uls.model.Song;
import com.uls.security.JWTHelper;
import com.uls.security.RequestHandler;

import io.jsonwebtoken.Claims;

/**
 * All song requests are handled via this class.
 * Created on 2019-09-14
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
@RestController
public class SongController {

	private ISongDAO songDAO;
	private RequestHandler reqHandler;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/**
	 * Default constructor.
	 */
	private SongController() {
		LOGGER.debug("SongController initialized!");
		songDAO = new SongDAO();
		reqHandler = new RequestHandler();
	}
	
	/**
	 * If the user wants to save a song, this method is called.
	 * 
	 * @param headers, the headers from the request.
	 * @return true, if it was successful, false if not.
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/saveSong", method = RequestMethod.POST)
	public boolean saveSong(@RequestHeader Map<String, String> headers) {
		LOGGER.debug("--- New Request ---");
		LOGGER.info("User trying to save a Song!");
		boolean status = false;
		
		Object usernameClaim;
		String username;
		Song song;
		Claims claims = reqHandler.checkAuthorization(headers);
		
		if (claims != null) {
			usernameClaim = claims.get(JWTHelper.USERNAME);
			if (usernameClaim != null) {
				username = usernameClaim.toString().trim();
				song = reqHandler.getSongFromHeaders(headers);
				if (song != null) {
					System.out.println("Song: " + song);
					LOGGER.info("Values for a new Song successfully validated!");
					if (songDAO.insertSongByUsername(username, song)) {
						status = true;
					}
				} else {
					LOGGER.debug("Validation for a new song failed!");
				}
			} else {
				LOGGER.debug("No Claim found with id 'username'!");
			}
		} else {
			LOGGER.debug("No Claims found in Token!");
		}
		LOGGER.debug("--- End of Request ---");
		return status;
	}
	
	/**
	 * If the user wants to have all songs by his username, this method is called. 
	 * 
	 * @param headers, the headers from the request.
	 * @return Either null or the songs found.
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getSongsByUsername", method = RequestMethod.GET)
	public List<Song> getSongsByUsername(@RequestHeader Map<String, String> headers) {
		LOGGER.debug("--- New Request ---");
		LOGGER.info("User trying to get a List with all his songs!");
		List<Song> songList = null;
		Object usernameClaim;
		String username;
		Claims claims = reqHandler.checkAuthorization(headers);
		
		if (claims != null) {
			usernameClaim = claims.get(JWTHelper.USERNAME);
			if (usernameClaim != null) {
				username = usernameClaim.toString().trim();
				songList = songDAO.lookupAllSongsByUsername(username);
				if (songList != null) {
					LOGGER.debug("Successfully found songs with username: '{}'!", username);
					LOGGER.info("Songs found and set!");
				} else {
					LOGGER.debug("No songs found with username: '{}'!", username);
					LOGGER.info("No songs found!");
				}
			} else {
				LOGGER.debug("No Claim found with id 'username'!");
			}
		} else {
			LOGGER.debug("No Claims found in Token!");
		}
		LOGGER.debug("--- End of Request ---");
		return songList;
	}
	
	/**
	 * If the user wants to delete a song by id, this method will be called.
	 * 
	 * @param headers, the headers from the request.
	 * @return true, if it was successful, false if not.
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/deleteSongById")
	public boolean deleteSongById(@RequestHeader Map<String, String> headers) {
		LOGGER.debug("--- New Request ---");
		LOGGER.info("User trying to delete a song!");
		boolean status = false;
		Object usernameClaim;
		Long songId;
		Claims claims = reqHandler.checkAuthorization(headers);
		
		if (claims != null) {
			usernameClaim = claims.get(JWTHelper.USERNAME);
			if (usernameClaim != null) {
				songId = reqHandler.getSongIdFromHeaders(headers);
				if (songId != null) {
					if (songDAO.deleteSongById(songId)) {
						LOGGER.debug("Song with id: '{}'!", songId);
					} else {
						LOGGER.debug("Couldn't delete song with id: '{}'!", songId);
					}
				}
				System.out.println(">>> song id: " + songId);
			} else {
				LOGGER.debug("No Claim found with id 'username'!");
			}
		} else {
			LOGGER.debug("No Claims found in Token!");
		}
		
		return status;
	}
}
