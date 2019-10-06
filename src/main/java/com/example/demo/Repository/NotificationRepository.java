package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.Notification;
//import com.example.demo.Model.Post;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {

	List<Notification> findByUsername(String id);
}
