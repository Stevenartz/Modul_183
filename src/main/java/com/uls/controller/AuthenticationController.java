package com.uls.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uls.security.JWTHelper;
import com.uls.security.RequestHandler;

/**
 * 
 * @author sulri
 *
 */
@RestController
public class AuthenticationController {

	private JWTHelper jwtHelper;
	private RequestHandler reqHandler;

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	private AuthenticationController() {
		LOGGER.debug("AuthenticationController initialized!");
		jwtHelper = new JWTHelper();
		reqHandler = new RequestHandler();
	}

	/**
	 * 
	 * @param headers
	 * @param response
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public String authenticate(@RequestHeader Map<String, String> headers, HttpServletResponse response) {
		LOGGER.info("User trying to login!");
		String username = reqHandler.checkLogin(headers);
		LOGGER.debug("Variable 'username' set to : '{}'!", username);
		String token = null;

		if (username != null) {
			Map<String, Object> myClaims = new HashMap<>();
			myClaims.put("username", username);
			token = jwtHelper.createJWT(myClaims, "subjeect", new Date(System.currentTimeMillis()),
					new Date(System.currentTimeMillis() + 60 * 60 * 1000));
			
			LOGGER.debug("Token created: '{}'!", token);
			LOGGER.info("A new JWT has been generated!");
			LOGGER.info("User successfully logged in!");
		} else {
			try {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
				LOGGER.info("Displayed unauthorized page for user!");
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error("Failed to display Unauthorized page, Msg: '{}'!", e.getMessage());
			}
		}
		return token;
	}

}
