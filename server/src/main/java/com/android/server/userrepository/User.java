package com.android.server.userrepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;
	
	private String username;
	private String password;
	private int score;
	
	public User(){}
	
	public User(String username, String password){
		super();
		this.username = username;
		this.password = password;
		this.score = 0;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(username, password);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(username, other.username)
					&& Objects.equal(password, other.password);
		} else {
			return false;
		}
	}
	
	public String toString(){
		return username + " " + password + " " + score;
	}
}
