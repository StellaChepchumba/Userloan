package com.example.com.userloan.service.serviceimpl;

import com.example.com.userloan.datalayer.entities.Users;
import com.example.com.userloan.datalayer.repositories.UserRepository;
import com.example.com.userloan.models.Response;
import com.example.com.userloan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Response createUser(Users user) {
        Response apiResponse = new Response();
        Optional<Users> existingEmail = userRepository.findAllByEmail(user.getEmail());
        if (existingEmail.isPresent()) {
            apiResponse.setResponseCode("409");
            apiResponse.setMessage("Email " + user.getEmail() + " already exist");
            return apiResponse;
        }
        user.setCreatedOn(LocalDateTime.now());
        userRepository.save(user);
        apiResponse.setResponseCode("200");
        apiResponse.setMessage("user created successfully");
        apiResponse.setResponseObject(user);
        return apiResponse;
    }

    @Override
    public Response searchUser(String nationalId) {
        Response apiResponse = new Response();
        Users existingUser =userRepository.findAllByNationalId(nationalId);
        if(existingUser==null){
            apiResponse.setResponseCode("404");
            apiResponse.setMessage("User not found");
        }
        else {
            apiResponse.setResponseCode("200");
            apiResponse.setMessage("success");
            apiResponse.setResponseObject(existingUser);
        }
        return apiResponse;
    }
}
