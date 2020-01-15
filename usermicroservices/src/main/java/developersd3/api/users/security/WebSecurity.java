package developersd3.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import developersd3.api.users.service.UserService;

/**
 * 
 * @author fred
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurity  extends WebSecurityConfigurerAdapter {
	
	private Environment enviroment;
	
	UserService userService;
	
	BCryptPasswordEncoder bCrypt;
	
	@Autowired
	public WebSecurity(Environment enviroment, UserService userService , BCryptPasswordEncoder bCrypt) {
		this.enviroment  = enviroment;
		this.userService = userService;
		this.bCrypt      = bCrypt;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("**").hasIpAddress(enviroment.getProperty("gateway.ip")).
		and().addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}
	

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		
		AuthenticationFilter authenticator = new AuthenticationFilter(userService,enviroment,authenticationManager());
		authenticator.setFilterProcessesUrl(enviroment.getProperty("login.url.path"));
		return authenticator;
		
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userService).passwordEncoder(bCrypt);
		
	}

}
