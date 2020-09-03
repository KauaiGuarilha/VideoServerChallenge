package com.videoserverchallenge.model.security;

import com.videoserverchallenge.model.entity.User;
import com.videoserverchallenge.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUser(username);

        if (user == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return user;
    }
}
