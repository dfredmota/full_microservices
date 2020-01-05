package developersd3.api.users.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import developersd3.api.users.dto.UserDto;
import developersd3.api.users.model.CreateUserRequestModel;
import developersd3.api.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UserService userService;

	// http://localhost:8011/users-ws/users/status/check
    @GetMapping(path = "/status/check")
    public String status(){
        return "working on port :" + env.getProperty("local.server.port");
    }
    
    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
    	
		ModelMapper mapper = new ModelMapper();
		
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto dto = mapper.map(userDetails, UserDto.class);
		
		userService.createUser(dto);
    	
    	return " creating user ";
    }
}
