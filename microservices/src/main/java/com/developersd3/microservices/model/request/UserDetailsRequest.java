package com.developersd3.microservices.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequest {

    @NotNull(message = "First Name is required.")
    private String firstName;

    @NotNull(message = "Last Name is required.")
    private String lastName;

    @NotNull(message = "Email is required.")
    @Email
    private String email;

    @NotNull(message = "PassWord is required.")
    @Size(min = 8 , max =  16 , message = "Password must be equal or greater than 8 caracteres and less than 16")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
