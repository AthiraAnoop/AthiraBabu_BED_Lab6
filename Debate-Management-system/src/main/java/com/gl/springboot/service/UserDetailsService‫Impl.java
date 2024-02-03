package com.gl.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gl.springboot.config.UserInfoUserDetails;
import com.gl.springboot.entity.User;
import com.gl.springboot.repository.UserRepository;


@Component
public class UserDetailsService‫Impl implements UserDetailsService{

	 @Autowired
	    private UserRepository userRepository;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user= userRepository.getUserByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return user.map(UserInfoUserDetails::new)
        .orElseThrow(()->new UsernameNotFoundException("user not found" + username));
        
	}

}
