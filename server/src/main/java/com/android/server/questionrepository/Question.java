package com.android.server.questionrepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.android.server.forumrepository.Forum;
import com.android.server.userrepository.User;
import com.google.common.base.Objects;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long qid;
	
	private String content;
	private int rate;
	@ManyToOne
	private User user;
	@ManyToOne
	private Forum forum;
	
	public Question(){
	}
	
	public Question(String content){
		super();
		this.content = content;
		this.rate = 0;
	}
	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}
	
	public void addRate(){
		this.rate++;
	}
	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(content, rate);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Question) {
			Question other = (Question) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(content, other.content)
					&& Objects.equal(rate, other.rate);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString(){
		return "Question content: " + content + " rate: " + rate; 
	}
}
