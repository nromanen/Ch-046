package ua.cv.tim.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vyach on 03.01.2017.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(travianAuthenticationProvider());
		auth.eraseCredentials(true);
	}

	@Bean
	public AuthenticationProvider travianAuthenticationProvider() {
		TravianAuthenticationProvider authenticationProvider = new TravianAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/", "/login").permitAll()
					.antMatchers("/logout").authenticated()
					.antMatchers("/admin**/**").access("hasRole('ADMIN')")
					.antMatchers("/leader**/**").access("hasRole('LEADER')")
					.antMatchers("/user**/**").access("hasRole('LEADER') or hasRole('USER')")
					.antMatchers("/askhelp").authenticated()
				.and()

				.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.successHandler(authenticationSuccessHandler)
					.failureUrl("/login.html?error=true")
				.and()

					.logout()
					.invalidateHttpSession(true)
					.logoutSuccessUrl("/logout")
					.deleteCookies("JSESSIONID", "XSRF-TOKEN", "locale-cookie")
				.and()

				.exceptionHandling()
					.accessDeniedPage("/access_denied")
				.and()

				.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
											HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
						.getName());
				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "X-XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null || token != null
							&& !token.equals(cookie.getValue())) {
						cookie = new Cookie("X-XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				filterChain.doFilter(request, response);
			}
		};
	}


	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
}
