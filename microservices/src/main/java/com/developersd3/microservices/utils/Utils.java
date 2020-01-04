package com.developersd3.microservices.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utils {

    public String generateUUID(){

        return UUID.randomUUID().toString();
    }
}
