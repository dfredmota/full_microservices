package developersd3.api.users.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import developersd3.api.users.model.LoginRequestModel;

public class AuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
	
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
	}

}
