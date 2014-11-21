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

public class ServerTest {
	private final String TEST_URL = "http://nearby.elasticbeanstalk.com";
//	private final String TEST_URL = "http://localhost:8080/";
	
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
		
		int ret = userService.addUser(user1);
		assertTrue(ret==1);
		ret = userService.addUser(user2);
		assertTrue(ret==1);
		ret = userService.addUser(new User("zdg","test"));
		assertTrue(ret==-1);
		
		Collection<User> usersLists = userService.getUserList();
		assertTrue(usersLists.contains(user1));
		assertTrue(usersLists.contains(user2));
		user1 = new ArrayList<>(usersLists).get(0);
		user2 = new ArrayList<>(usersLists).get(1);
		
		User tmp = userService.validate(new User("zdg","pw"));
		assertTrue(tmp!=null);
		tmp = userService.validate(new User("zdg","aha"));
		assertTrue(tmp==null);
//		for(User u:usersLists){
//			System.out.println(u);
//		}
		String[] forum_name = {"ohio union","oia","Thompson lib","wilce center",
				"rpec","18th lib","Dreese lab","Hitchcock & Bolz","Ohio stadium"};
		for(int i = 0; i < 9; i++){
			Forum forum = new Forum(forum_name[i]);
			forumService.addForum(forum);
		}
		
		Collection<Forum> forumLists = forumService.searchByName("ohio");
		for(Forum f:forumLists){
			System.out.println("hahahah" + f.getName());
		}
		
		for(int i = 0; i<5;i++){
			questionService.addQuestion("this is question i" + i,"zdg",1);
		}
		
		ok = questionService.addQuestion("who am I?","zdg",1);
		assertTrue(ok);
		ok = questionService.addQuestion("what is true?","tim",1);
		assertTrue(ok);
		
		Collection<Question> questionList = questionService.findByUserName("zdg");
		for(Question q:questionList){
			System.out.println(q);
		}
		
		questionList = questionService.findByForumId(1);
		for(Question q:questionList){
			System.out.println(q);
		}
		questionList = questionService.searchByQuestionInForum("who", 1);
		System.out.println("This is for search");
		for(Question q:questionList){
			System.out.println(q);
		}
		questionList = questionService.getQuestionList();
				
		
		answerService.addAnswer("haha, I know.","zdg",1);
		answerService.addAnswer("Sorry, I don't know.","tim",2);
		
		Collection<Answer> answerList = answerService.findByUserName("zdg");
		for(Answer a : answerList){
			System.out.println(a);
		}
		
		answerList = answerService.findByQuestionId(11);
		for(Answer a : answerList){
			System.out.println(a);
		}
		
		ok = answerService.rateAnswerById(1);
		assertTrue(ok);
		
		ok = questionService.rateQuestionById(1);
		assertTrue(ok);
		
		questionList = questionService.getSortedQuestionList(1);
		for(Question q:questionList){
			System.out.println(q);
		}
		
		Collection<Integer> notiCollection = userService.getNotificationSet("zdg");
		for(Integer i:notiCollection){
			System.out.println(i);
		}
		
		notiCollection = userService.getNotificationSet("zdg");
		for(Integer i:notiCollection){
			System.out.println(i);
		}
		
		ok = questionService.rateQuestionById(1);
		assertTrue(ok);
		
		notiCollection = userService.getNotificationSet("zdg");
		for(Integer i:notiCollection){
			System.out.println(i);
		}
		
	}
}
