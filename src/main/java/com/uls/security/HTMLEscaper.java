package com.uls.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class escapes HTML Tags into Strings.
 * Created on 2019-10-05
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public class HTMLEscaper {

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/**
	 * Removes the HTML tags from the input.
	 * 
	 * @param input, the Input with potentially HTML tags.
	 * @return input without the HTML tags.
	 */
	public String escapeHTML(String input){
	       StringBuffer sb = new StringBuffer();
	       LOGGER.debug("Removing all HTML tags of input: '{}'!", input);
	       int stringLength = input.length();
	       for (int i = 0; i < stringLength; i++) {
	          char c = input.charAt(i);
	          switch (c) {
	             case '<': sb.append("&lt;"); break;
	             case '>': sb.append("&gt;"); break;
	             case '&': sb.append("&amp;"); break;
	             case '"': sb.append("&quot;"); break;
	             default:  sb.append(c); break;
	          }
	       }
	       return sb.toString();
	    }
}
