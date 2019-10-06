package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.Comments;
import com.example.demo.Model.Post;



public interface CommentRepository extends CrudRepository<Comments, Integer>{

	
	List<Comments> findByPost(Post p);
}
