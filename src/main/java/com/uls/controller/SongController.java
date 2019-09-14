package com.uls.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uls.model.Song;
import com.uls.security.RequestHandler;

/**
 * 
 * @author sulri
 *
 */
@RestController
public class SongController {

//	private ISongDAO songDAO;
	private RequestHandler reqHandler;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private SongController() {
		LOGGER.debug("SongController initialized!");
//		songDAO = new SongDAO();
		reqHandler = new RequestHandler();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getSongsByUsername", method = RequestMethod.GET)
	public List<Song> getSongsByUsername(@RequestHeader Map<String, String> headers) {
		List<Song> songList = new ArrayList<>();
		songList.add(new Song(1, "pop", "titel", "artist", new GregorianCalendar()));
		songList.add(new Song(2, "rock", "titel2", "artist2", new GregorianCalendar()));
		songList.add(new Song(3, "EDM", "titel3", "artist3", new GregorianCalendar()));
		return songList;
	}
	
}
