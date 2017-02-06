package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vyach on 03.01.2017.
 */

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String loginPage() {
		return "login.jsp";
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest req, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, resp, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public String handleAccessDenied() {
		logger.warn("Access denied");
		if (isCurrentAuthenticationAnonymous()) {
			return "redirect:/login";
		} else {
			return getRedirectPath();
		}
	}

	private String getRedirectPath() {
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<String> authorities = new HashSet<>();
		for (GrantedAuthority authority : principal.getAuthorities()) {
			authorities.add(authority.getAuthority());
		}
		if (authorities.contains("ROLE_ADMIN")) {
			return "redirect:/admin";
		} else if (authorities.contains("ROLE_LEADER")) {
			return "redirect:/leader";
		} else {
			return "redirect:/user/init";
		}
	}

	@RequestMapping(value = {"/admin", "/leader", "/user/init"}, method = RequestMethod.GET)
	public String showMainPage() {
		return "index.html";
	}
}
