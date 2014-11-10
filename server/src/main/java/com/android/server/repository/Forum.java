package com.android.server.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

@Entity
public class Forum {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fid;
	
	private String name;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name= "fid")
//	private Set<Question> forumQuestions;
	
	public Forum(){}
	
	public Forum(String name){
		super();
		this.name = name;
	}
	
	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	public Set<Question> getForumQuestions() {
//		return forumQuestions;
//	}
//
//	public void setForumQuestions(Set<Question> forumQuestions) {
//		this.forumQuestions = forumQuestions;
//	}

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Forum) {
			Forum other = (Forum) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(name, other.name);
		} else {
			return false;
		}
	}
}
