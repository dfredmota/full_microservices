package com.developersd3.microservices.services;

import com.developersd3.microservices.model.request.UserDetailsRequest;
import com.developersd3.microservices.model.response.UserRest;

public interface UserService {

    UserRest create(UserDetailsRequest userDetailsRequest);
}
