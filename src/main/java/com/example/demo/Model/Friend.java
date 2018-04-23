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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String friend_id;
	private String friend;
//	@Column(name="userid")
//	//private String userid;
//	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
//    @JoinColumn(name = "userid")
	//private User user;
	
	

//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
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
