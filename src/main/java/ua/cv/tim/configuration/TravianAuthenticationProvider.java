package ua.cv.tim.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by vyach on 13.02.2017.
 */

public class TravianAuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(TravianAuthenticationProvider.class);

	private PasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;
	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

	public TravianAuthenticationProvider() {
		setPasswordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (userDetailsService == null) {
			throw new IllegalArgumentException("A UserDetailsService must be set");
		}
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		String userName = (authentication.getPrincipal() == null) ? null : authentication.getName();

		UserDetails user = retrieveUser(userName);
		additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);

		return createSuccessAuthentication(authentication, user);
	}

	private UserDetails retrieveUser(String username)
			throws AuthenticationException {
		UserDetails user;

		try {
			user = userDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException ex) {
			throw ex;
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(
					repositoryProblem.getMessage(), repositoryProblem);
		}

		if (user == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
		return user;
	}

	/**
	 * Main method that respond login page password validation
	 *
	 * @param userDetails
	 * @param authentication
	 * @throws BadCredentialsException if invalid password was entered
	 */
	private void additionalAuthenticationChecks(UserDetails userDetails,
												UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		if (authentication.getCredentials() != null) {
			String password = authentication.getCredentials().toString();

			if (password.length() > 32 || !passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("Bad credentials");
			}
		} else {
			throw new BadCredentialsException("Bad credentials");
		}
	}

	private Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, authentication.getCredentials(),
				authoritiesMapper.mapAuthorities(user.getAuthorities()));
		authenticationToken.setDetails(authentication.getDetails());

		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		if (passwordEncoder == null) {
			throw new IllegalArgumentException("passwordEncoder cannot be null");
		}
		this.passwordEncoder = passwordEncoder;
	}

	protected PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}
}

