package com.uls.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uls.dao.type.PersonType;
import com.uls.dao.type.SongType;
import com.uls.model.Person;
import com.uls.model.Song;

/**
 * 
 * @author sulri
 *
 */
public class Mapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 * @param rs
	 * @return
	 */
	public List<Person> mapResultSetToPersonList(ResultSet rs) {
		List<Person> personList = new ArrayList<>();
		LOGGER.debug("Mapping ResultSet to person list!");
		try {
			if (rs != null) {
				while (rs.next()) {
					Person person = new Person();
					person.setUsername(rs.getString(PersonType.USERNAME.toString()));
					person.setFirstname(rs.getString(PersonType.FIRSTNAME.toString()));
					person.setLastname(rs.getString(PersonType.LASTNAME.toString()));
					person.setPassword(rs.getString(PersonType.PASSWORD.toString()));
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.setTime(rs.getDate(PersonType.BIRTHDAY.toString()));
					person.setBirthday(calendar);
					personList.add(person);
				}
			} else {
				LOGGER.debug("Tried to map ResultSet to person list, but ResultSet is null!");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			LOGGER.error("Failed to map ResultSet to person list, Msg: '{}'!", sqle.getMessage());
		}
		return personList;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 */
	public List<Song> mapResultSetToSongList(ResultSet rs) {
		List<Song> songList = new ArrayList<>();
		LOGGER.debug("Mapping ResultSet to song list!");
		try {
			if (rs != null) {
				while (rs.next()) {
					Song song = new Song();
					song.setId(rs.getLong(SongType.ID.toString()));
					song.setGenre(rs.getString(SongType.GENRE.toString()));
					song.setTitle(rs.getString(SongType.TITLE.toString()));
					song.setArtist(rs.getString(SongType.ARTIST.toString()));
					song.setLength(rs.getInt(SongType.LENGTH.toString()));
					songList.add(song);
				}
			} else {
				LOGGER.debug("Tried to map ResultSet to song list, but ResultSet is null!");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			LOGGER.error("Failed to map ResultSet to song list, Msg: '{}'!", sqle.getMessage());
		}
		return songList;
	}
}
