package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.PostRepository;
//import com.example.demo.Repository.UserRepository;
//import com.example.demo.model.Event;

@Service
public class PostService {

@Autowired
PostRepository postrepository;


public PostService(PostRepository postrepository) {
	this.postrepository=postrepository;
}


public void saveMyPost(Post post)
{
	postrepository.save(post);
}

public List<Post> showAllPosts(){
	List<Post> posts = new ArrayList<Post>();
for(Post post :postrepository.findAll()) {
	posts.add(post);
}
	
	return posts;
}



	
}
