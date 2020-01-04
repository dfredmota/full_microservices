package com.developersd3.microservices.controllers;

import com.developersd3.microservices.model.request.UpdateUserDetailsRequest;
import com.developersd3.microservices.model.request.UserDetailsRequest;
import com.developersd3.microservices.model.response.UserRest;
import com.developersd3.microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping()
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit,
                           @RequestParam(value = "sort", required = false) String sort){

    return " getting all users with page : "+ page + " and limit :" + limit;
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){

        if(users.containsKey(userId)){
            return new ResponseEntity<UserRest>(users.get(userId) , HttpStatus.OK);
        }else{
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }


    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRest> createUser(@Valid  @RequestBody UserDetailsRequest userDetailsRequest){

        UserRest userReturn = userService.create(userDetailsRequest);

        return new ResponseEntity<UserRest>(userReturn,HttpStatus.OK);

    }

    @PutMapping(path = "/{userId}" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId , @Valid  @RequestBody UpdateUserDetailsRequest updateUserDetailsRequest){

        if(users.containsKey(userId)){

            UserRest user = users.get(userId);

            user.setFirstName(updateUserDetailsRequest.getFirstName());
            user.setLastName(updateUserDetailsRequest.getLastName());

            users.put(userId,user);

            return new ResponseEntity<UserRest>(user,HttpStatus.ACCEPTED);
        }else{

            return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){

        if(users.containsKey(id)){
            users.remove(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
