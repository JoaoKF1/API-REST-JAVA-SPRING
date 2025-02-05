package com.example.APIrest_JavaSpring.repositories;

import com.example.APIrest_JavaSpring.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByLogin(String login);

    String login(String login);
}
