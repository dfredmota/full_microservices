package developersd3.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import developersd3.api.users.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto dto);

	UserDto getUserDetailsByEmail(String email);
}
