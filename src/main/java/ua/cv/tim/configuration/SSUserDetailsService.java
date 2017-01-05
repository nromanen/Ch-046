package ua.cv.tim.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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

	@Autowired
	private UserService userService;

	public SSUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = userService.getUserByUsername(username);
			return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
					getAuthorities(user));
		} catch (NoResultException ex) {
			throw new UsernameNotFoundException("User not found");
		}
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
