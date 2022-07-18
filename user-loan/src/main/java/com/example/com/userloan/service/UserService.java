package com.example.com.userloan.service;

import com.example.com.userloan.datalayer.entities.Users;
import com.example.com.userloan.models.Response;

public interface UserService {
    public Response createUser(Users user);
    public Response searchUser(String nationalId);
}
