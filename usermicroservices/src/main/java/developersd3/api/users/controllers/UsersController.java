package developersd3.api.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;

	// http://localhost:8011/users-ws/users/status/check
    @GetMapping(path = "/status/check")
    public String status(){
        return "working on port :" + env.getProperty("local.server.port");
    }
}
