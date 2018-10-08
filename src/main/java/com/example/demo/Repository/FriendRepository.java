package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.Friend;
//import com.example.demo.Model.User;
import com.example.demo.Model.User;

public interface FriendRepository extends CrudRepository<Friend, Integer>{
	
	List<Friend> findByUser(User user);

}
