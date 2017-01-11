package ua.cv.tim.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by vyach on 03.01.2017.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/some").access("hasRole('USER')")
				.antMatchers("/logout").authenticated()
				.antMatchers("/admin**").access("hasRole('ADMIN')")
				.antMatchers("/allianceDTO*").permitAll()
				.antMatchers("/leader**").access("hasRole('LEADER')")
				.antMatchers("/user**").access("hasRole('LEADER') or hasRole('USER')")
				.antMatchers("/", "/login").permitAll()
				.and()
				.formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/login")
				.and()
				.exceptionHandling().accessDeniedPage("/access_denied");

//        http.csrf().disable();


 /*       CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
*/
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
}
