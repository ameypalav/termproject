package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;

public interface PostRepository extends CrudRepository<Post, Integer> {

	List<Post> findAllById(String userid);
	
	public Post findById(int id);
	 List<Post> findByUser(User user);

}
