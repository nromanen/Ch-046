package ua.cv.tim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.cv.tim.model.AuthorizedUser;
import ua.cv.tim.model.Role;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vyach on 03.01.2017.
 */

@Service(value = "userDetailsService")
public class SSUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(SSUserDetailsService.class);

	@Autowired
	private UserService userService;

	public SSUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = userService.getFullUserByUsername(username);
			if (user.getPlayer() != null) {
				AuthorizedUser authorizedUser = getAuthorizedUserInstance(user);
				log.info(authorizedUser.toString());
				return authorizedUser;
			} else {
				return new AuthorizedUser(user.getLogin(), user.getPassword(), getAuthorities(user));
			}
		} catch (NoResultException ex) {
			throw new UsernameNotFoundException("User not found");
		}
	}

	private AuthorizedUser getAuthorizedUserInstance(User user) {
		AuthorizedUser authorizedUser = new AuthorizedUser(user.getLogin(), user.getPassword(),
				getAuthorities(user));
		authorizedUser.setUuid(user.getUuid());
		authorizedUser.setEmail(user.getEmail());
		authorizedUser.setRoles(user.getRoles());
		authorizedUser.setRace(user.getPlayer().getRace());
		authorizedUser.setVillages(user.getPlayer().getVillages());
		authorizedUser.setAlliance(user.getPlayer().getAlliance());
		return authorizedUser;
	}

	private Set<GrantedAuthority> getAuthorities(User user) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.toString());
			authorities.add(grantedAuthority);
		}
		return authorities;
	}

}
