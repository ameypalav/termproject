package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comments {
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
	private Post post;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int commentid;
	
//	public int getCommentuserid() {
//		return commentuserid;
//	}
//
//	public void setCommentuserid(int commentuserid) {
//		this.commentuserid = commentuserid;
//	}

	private String commentuserid;
	
	public String getCommentuserid() {
		return commentuserid;
	}

	public void setCommentuserid(String commentuserid) {
		this.commentuserid = commentuserid;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	private String commentuser;
	
	public String getCommentuser() {
		return commentuser;
	}

	public void setCommentuser(String commentuser) {
		this.commentuser = commentuser;
	}

	private String comment;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Comments()
	{
		
	}
	

}
