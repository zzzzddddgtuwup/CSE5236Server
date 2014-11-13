package com.android.server.answerrepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.android.server.questionrepository.Question;
import com.android.server.userrepository.User;
import com.google.common.base.Objects;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long aid;
	
	private String content;
	private int rate;
	@ManyToOne
	private User user;
	@ManyToOne
	private Question question;
	
	public Answer(){}
	
	public Answer(String content){
		super();
		this.content = content;
		this.rate = 0;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(content, rate);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Answer) {
			Answer other = (Answer) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(content, other.content)
					&& Objects.equal(rate, other.rate);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString(){
		return "Answer content is" + content + ", rate is " + rate;
	}
}
