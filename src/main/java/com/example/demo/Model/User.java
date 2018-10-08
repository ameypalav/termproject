package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="userid")
	private String userid;
	private String username;
//	private String desc;
	private String details;
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
//	public Set<Friend> getFriendslist() {
//		return friendslist;
//	}
//	public void setFriendslist(Set<Friend> friendslist) {
//		this.friendslist = friendslist;
//	}
	private String profileLink;
	private String friend;
	//@OneToMany(targetEntity=Friend.class,fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "user")
//	@JoinColumn(name="userid")
	//private Set <Friend> friendslist ;
	private List<Friend> frind;
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "user")	
	private List<Post> post;
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	public List<Friend> getFrind() {
		return frind;
	}
	public void setFrind(List<Friend> frind) {
		this.frind = frind;
	}
	private String email;
	public String getProfileLink() {
		return profileLink;
	}
	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String string) {
		this.userid = string;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	
}
