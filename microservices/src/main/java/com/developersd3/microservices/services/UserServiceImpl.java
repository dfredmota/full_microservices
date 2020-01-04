package com.developersd3.microservices.services;

import com.developersd3.microservices.model.request.UserDetailsRequest;
import com.developersd3.microservices.model.response.UserRest;
import com.developersd3.microservices.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;

    private Utils utils;

    public UserServiceImpl(){}

    @Autowired
    public UserServiceImpl(Utils utils){
        this.utils = utils;
    }

    @Override
    public UserRest create(UserDetailsRequest userDetailsRequest) {
        UserRest userReturn = new UserRest();

        userReturn.setFirstName(userDetailsRequest.getFirstName());
        userReturn.setLastName(userDetailsRequest.getLastName());
        userReturn.setEmail(userDetailsRequest.getEmail());

        String userId = utils.generateUUID();

        userReturn.setUserId(userId);

        if(users == null) users = new HashMap<String,UserRest>();

        users.put(userId , userReturn);

        return userReturn;
    }
}
