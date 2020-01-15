package developersd3.api.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import developersd3.api.users.dto.UserDto;
import developersd3.api.users.model.LoginRequestModel;
import developersd3.api.users.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
	
	private UserService userService;
	
	private Environment enviroment;
	
	@Autowired
	public AuthenticationFilter(UserService userService,Environment enviroment,AuthenticationManager authenticationManager) {
		
		this.userService = userService;
		
		this.enviroment  = enviroment;
		
		super.setAuthenticationManager(authenticationManager);
		
		
		
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
		
		LoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
		
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
				creds.getEmail(), creds.getPassword(),new ArrayList<>()));
		
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String userName = ((User)authResult.getPrincipal()).getUsername();
		UserDto dto = userService.getUserDetailsByEmail(userName);
		
		String token = Jwts.builder()
				.setSubject(dto.getUserId())
				.setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(enviroment.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, enviroment.getProperty("token.secret"))
				.compact();
		
		response.addHeader("token",token);
		response.addHeader("userId", dto.getUserId());
		
	}

}
