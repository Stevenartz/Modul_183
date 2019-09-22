package com.uls.security;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uls.dao.personDAO.IPersonDAO;
import com.uls.dao.personDAO.PersonDAO;
import com.uls.dao.type.SongType;
import com.uls.model.Person;
import com.uls.model.Song;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author Stefan
 *
 */
public class RequestHandler {

	private JWTHelper jwtHelper;
	private IPersonDAO personDAO;
	private SHA512Hasher sha512Hasher;

	private final int TOKEN = 1;
	private final int USERNAME = 0;
	private final int PASSWORD = 1;

	final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	public RequestHandler() {
		jwtHelper = new JWTHelper();
		personDAO = new PersonDAO();
		sha512Hasher = new SHA512Hasher();
	}

	/**
	 * 
	 * @param headers
	 * @return
	 */
	public Claims checkAuthorization(Map<String, String> headers) {
		String authorization = headers.get("authorization");
		String token[];
		Claims claims = null;
		
		if (authorization != null) {
			LOGGER.debug("Authorization found: '{}'!", authorization);
			token = authorization.split(" ");
			if (token.length == 2) {
				claims = jwtHelper.verifyToken(token[TOKEN]);
			} else {
				LOGGER.debug("Token length invalid!");
			}
		} else {
			LOGGER.debug("No authorization found in header!");
		}
		return claims;
	}

	/**
	 * 
	 * @param headers
	 * @return
	 */
	public String checkLogin(Map<String, String> headers) {
		String username = null;

		String authorization = headers.get("authorization");
		String usernameAndPassword[];
		Person person;

		if (authorization != null) {
			LOGGER.debug("Basic authorization found: '{}'!", authorization);
			String base64Credentials = authorization.substring("Basic".length()).trim();
			LOGGER.debug("Decoding basic authorization!");
			byte[] decodedBytes = Base64.decodeBase64(base64Credentials);
			usernameAndPassword = new String(decodedBytes).split(":");
			if (usernameAndPassword.length == 2) {
				person = personDAO.lookupPersonByUsername(usernameAndPassword[USERNAME]);
				if (person != null) {
					if (person.getPassword().equals(sha512Hasher.stringToHash(usernameAndPassword[PASSWORD]))) {
						LOGGER.debug("User: '{}' successfully authenticated!", usernameAndPassword[USERNAME]);
						username = usernameAndPassword[USERNAME];
					} else {
						LOGGER.debug("User: '{}' tried to login with wrong password!", usernameAndPassword[USERNAME]);
					}
				} else {
					LOGGER.debug("No person found with username: '{}'!", usernameAndPassword[USERNAME]);
				}
			} else {
				LOGGER.debug("Authorization header not long enough, expected: '2', but was : '{}'!",
						usernameAndPassword.length);
			}
		} else {
			LOGGER.debug("No authorization found in header!");
		}
		return username;
	}

	/**
	 * 
	 * @param headers
	 * @return
	 */
	public Song getSongFromHeaders(Map<String, String> headers) {
		Song song = null;
		int status = 0;

		String genre = headers.get(SongType.GENRE.toString()).trim();
		String title = headers.get(SongType.TITLE.toString()).trim();
		String artist = headers.get(SongType.ARTIST.toString()).trim();
		String lengthString = headers.get(SongType.LENGTH.toString()).trim();
		int length = 0;
		
		LOGGER.debug("Validating values: genre: '{}', title: '{}', artist: '{}', length: '{}'!", genre, title, artist,
				lengthString);
		if (genre != null && title != null && title != null && lengthString != null) {
			
			// Validating song genre
			LOGGER.debug("Validating genre input...!");
			if (genre.matches(Regex.SONG_GENRE.toString())) {
				if (genre.length() > 0 && genre.length() <= 30) {
					status++;
					LOGGER.debug("Validtion for genre successful!");
				} else {
					LOGGER.debug("Genre input didn't match with length. Expected between: '{}' and '{}', but was: '{}'!", 0, 30, genre.length());
				}
			} else {
				LOGGER.debug("Genre input didn't match with RegEx pattern: '{}'!", Regex.SONG_GENRE.toString());
			}
			
			// Validating song title
			LOGGER.debug("Validating title input...!");
			if (title.matches(Regex.SONG_TITLE.toString())) {
				if (title.length() > 0 && title.length() <= 75) {
					status++;
					LOGGER.debug("Validtion for title successful!");
				} else {
					LOGGER.debug("Title input title didn't match with length. Expected between: '{}' and '{}', but was: '{}'!", 0, 75, title.length());
				}
			} else {
				LOGGER.debug("Title input didn't match with RegEx pattern: '{}'!", Regex.SONG_TITLE.toString());
			}
			
			// Validating song artist
			LOGGER.debug("Validating artist input...!");
			if (artist.matches(Regex.SONG_ARTIST.toString())) {
				if (artist.length() > 0 && artist.length() <= 50) {
					status++;
					LOGGER.debug("Validtion for artist successful!");
				} else {
					LOGGER.debug("Artist input didn't match with length. Expected between: '{}' and '{}', but was: '{}'!", 0, 50, artist.length());
				}
			} else {
				LOGGER.debug("Artist input didn't match with RegEx pattern: '{}'!", Regex.SONG_ARTIST.toString());
			}
			
			// Validating song length
			LOGGER.debug("Validating length input...!");
			if (lengthString.matches(Regex.SONG_LENGTH.toString())) {
				try {
					length = Integer.parseInt(lengthString);
					status++;
					LOGGER.debug("Validtion for song length successful!");
				} catch (NumberFormatException nfe) {
					LOGGER.debug("Exception thrown while validating song length, exception message: '{}'!",
							nfe.getMessage());
					LOGGER.debug("Length attribute is not a Integer but: '{}'!", lengthString);
				}
			} else {
				LOGGER.debug("Song length didn't match with RegEx pattern: '{}'!", Regex.SONG_LENGTH.toString());
			}
			
			// Validtion was successful on all input fields.
			if (status == 4) {
				LOGGER.debug("Creating a new Song!");
				song = new Song(genre, title, artist, length);
			} else {
				LOGGER.debug("Validation failed! Amount of passsed validations: '{}'!", status);
			}

		} else {
			LOGGER.debug("Validation failed: a Song attribute contains null!");
		}
		if (song != null) {
			LOGGER.debug("Successfully created a new song!");
		}

		return song;
	}

}
