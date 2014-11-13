package com.android.server.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

import com.android.server.answerrepository.Answer;
import com.android.server.api.AnswerSvcApi;
import com.android.server.api.ForumSvcApi;
import com.android.server.api.QuestionSvcApi;
import com.android.server.api.UserSvcApi;
import com.android.server.forumrepository.Forum;
import com.android.server.questionrepository.Question;
import com.android.server.userrepository.User;
import com.google.common.collect.Lists;

public class ServerTest {
	private final String TEST_URL = "http://nearby.elasticbeanstalk.com";
	
	private QuestionSvcApi questionService = new RestAdapter.Builder()
		.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
		.create(QuestionSvcApi.class);
	
	private UserSvcApi userService = new RestAdapter.Builder().setEndpoint(TEST_URL)
			.setLogLevel(LogLevel.FULL).build().create(UserSvcApi.class);
	
	private ForumSvcApi forumService = new RestAdapter.Builder().setEndpoint(TEST_URL)
			.setLogLevel(LogLevel.FULL).build().create(ForumSvcApi.class);
	
	private AnswerSvcApi answerService = new RestAdapter.Builder().setEndpoint(TEST_URL)
			.setLogLevel(LogLevel.FULL).build().create(AnswerSvcApi.class);
	
//	private 
	
	@Test
	public void testAll() throws Exception {
		boolean ok = false;
		//add user1
		User user1 = new User("zdg","pw");
		//add user2
		User user2 = new User("tim","pw");
		
		ok = userService.addUser(user1);
		System.out.println(ok);
		assertTrue(ok);
		ok = userService.addUser(user2);
		assertTrue(ok);
		
		Collection<User> usersLists = userService.getUserList();
		assertTrue(usersLists.contains(user1));
		assertTrue(usersLists.contains(user2));
		user1 = new ArrayList<>(usersLists).get(0);
		user2 = new ArrayList<>(usersLists).get(1);
		
//		for(User u:usersLists){
//			System.out.println(u);
//		}
		String[] forum_name = {"ohio union","oia","Thompson lib","wilce center",
				"rpec","18th lib","Dreese lab","Hitchcock & Bolz","Ohio stadium"};
		for(int i = 0; i < 9; i++){
			Forum forum = new Forum(forum_name[i]);
			forumService.addForum(forum);
		}
		
		Collection<Forum> forumLists = forumService.getForumList();
		Forum f1 = Lists.newArrayList(forumLists).get(0);
		Forum f2 = Lists.newArrayList(forumLists).get(1);
		
		Question q1 = new Question("who am I?");
		q1.setForum(f1);
		q1.setUser(user1);
		
		Question q2 = new Question("what is true?");
		q2.setForum(f2);
		q2.setUser(user2);
		
		ok = questionService.addQuestion(q1);
		assertTrue(ok);
		ok = questionService.addQuestion(q2);
		assertTrue(ok);
		
		Collection<Question> questionList = questionService.findByUserName("zdg");
		for(Question q:questionList){
			System.out.println(q);
		}
		
		questionList = questionService.findByForumId(1);
		for(Question q:questionList){
			System.out.println(q);
		}
		
		questionList = questionService.getQuestionList();
		q1 = Lists.newArrayList(questionList).get(0);
		q2 = Lists.newArrayList(questionList).get(1);
		Answer a1 = new Answer("haha, I know.");
		a1.setQuestion(q1);
		a1.setUser(user2);
		
		Answer a2 = new Answer("Sorry, I don't know.");
		a2.setQuestion(q2);
		a2.setUser(user1);
		
		answerService.addAnswer(a1);
		answerService.addAnswer(a2);
		
		Collection<Answer> answerList = answerService.findByUserName("zdg");
		for(Answer a : answerList){
			System.out.println(a);
		}
	}
}
