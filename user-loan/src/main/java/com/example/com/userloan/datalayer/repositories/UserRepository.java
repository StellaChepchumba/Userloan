package com.example.com.userloan.datalayer.repositories;


import com.example.com.userloan.datalayer.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users>findAllByEmail(String email);
    Users findAllByNationalId(String nationalId);
}
