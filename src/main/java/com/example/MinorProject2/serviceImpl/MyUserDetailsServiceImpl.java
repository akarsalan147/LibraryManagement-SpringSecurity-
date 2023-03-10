package com.example.MinorProject2.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MinorProject2.enums.Authorities;
import com.example.MinorProject2.model.MyUser;
import com.example.MinorProject2.repository.MyUserCacheRepositoryInterf;
import com.example.MinorProject2.repository.MyUserRepositoryInterf;
import com.example.MinorProject2.repositoryImpl.MyUserCacheRepositoryImpl;
import com.example.MinorProject2.requests.UserCreateRequest;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MyUserCacheRepositoryInterf myUserCacheRepositoryInterf;
	
	@Autowired
	MyUserRepositoryInterf myUserRepositoryInterf;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser = myUserCacheRepositoryInterf.get(username);
		if(myUser == null) {
			myUser = myUserRepositoryInterf.findByUsername(username);
			if(myUser != null) {
				myUserCacheRepositoryInterf.set(myUser);
			}
		}
		
		return myUser;
	}
	
	public MyUser createUser(UserCreateRequest userCreateRequest) {
		MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
				.username(userCreateRequest.getUsername())
				.password(passwordEncoder.encode(userCreateRequest.getPassword()));
		
		if(userCreateRequest.getStudent() != null) {
			myUserBuilder.authority(Authorities.std.toString());
		}
		else {
			myUserBuilder.authority(Authorities.Adm.toString());
		}
		return myUserRepositoryInterf.save(myUserBuilder.build());
	}

}
