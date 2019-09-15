package com.uls.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uls.dao.songDAO.ISongDAO;
import com.uls.dao.songDAO.SongDAO;
import com.uls.model.Song;
import com.uls.security.RequestHandler;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author sulri
 *
 */
@RestController
public class SongController {

	private ISongDAO songDAO;
	private RequestHandler reqHandler;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private SongController() {
		LOGGER.debug("SongController initialized!");
		songDAO = new SongDAO();
		reqHandler = new RequestHandler();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getSongsByUsername", method = RequestMethod.GET)
	public List<Song> getSongsByUsername(@RequestHeader Map<String, String> headers) {
		LOGGER.info("User trying to get a List with all his songs!");
		List<Song> songList = null;
		Object usernameClaim;
		String username;
		Claims claims = reqHandler.checkAuthorization(headers);
		
		if (claims != null) {
			usernameClaim = claims.get("username");
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
		
		return songList;
	}
}
