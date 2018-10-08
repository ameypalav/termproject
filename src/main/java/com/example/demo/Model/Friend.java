package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="friend")
public class Friend {
	
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String friend_id;
	public String getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	private String friend;
//	@Column(name="userid")
	private String user_id;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "userid")
	private User user;
	
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFriend() {
		return friend;
	}
//	public Friend(String friend, User user) {
//		super();
//		this.friend = friend;
//		this.user = user;
//	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	
//	public String getUserid() {
//		return userid;
//	}
//	public void setUserid(String userid) {
//		this.userid = userid;
//	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public Friend() {
		// TODO Auto-generated constructor stub
	}

	
}
