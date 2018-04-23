package com.example.demo.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Friend;
import com.example.demo.Model.User;
import com.example.demo.Repository.FriendRepository;
import com.example.demo.Repository.UserRepository;

@Service
@Transactional
public class FriendService {


	@Autowired
	private final FriendRepository friendRepository;
	public FriendService(FriendRepository friendRepository) {
		this.friendRepository=friendRepository;
	}
	
	
	
	public void saveMyFriend(Friend friend)
	{
		friendRepository.save(friend);
	}
	
	
}
