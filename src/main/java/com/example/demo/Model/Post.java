package com.example.demo.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private String audiourl;
	private String descpt;
	public String getDescpt() {
		return descpt;
	}
	public void setDescpt(String descpt) {
		this.descpt = descpt;
	}

	private String imageurl;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "userid")
	private User user;
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "post")
	private List<Comments> comment;
	
	
	public List<Comments> getComment() {
		return comment;
	}
	public void setComment(List<Comments> comment) {
		this.comment = comment;
	}
	public String getAudiourl() {
		return audiourl;
	}
	public void setAudiourl(String audiourl) {
		this.audiourl = audiourl;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Post() {
		
	}
	
	
	
}
