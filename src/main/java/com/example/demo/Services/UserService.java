package com.example.demo.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;



@Service
@Transactional
public class UserService {

	@Autowired
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	public User findByUserid(String userid) {
		return userRepository.findByUserid(userid);
	}
	
	
	public void saveMyUser(User user)
	{
		userRepository.save(user);
	}
	
	
}
