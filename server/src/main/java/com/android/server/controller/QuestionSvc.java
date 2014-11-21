package com.android.server.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.android.server.api.QuestionSvcApi;
import com.android.server.forumrepository.ForumRepository;
import com.android.server.questionrepository.Question;
import com.android.server.questionrepository.QuestionRepository;
import com.android.server.userrepository.User;
import com.android.server.userrepository.UserRepository;
import com.google.common.collect.Lists;

@Controller
public class QuestionSvc implements QuestionSvcApi{
	
	@Autowired
	private QuestionRepository questions;
	
	@Autowired
	private UserRepository user;
	
	@Autowired
	private ForumRepository forumRepository;
	
	@Override
	@RequestMapping(value=QUESTION_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Question> getQuestionList() {
		return Lists.newArrayList(questions.findAll());
	}

	@Override
	@RequestMapping(value=QUESTION_USER_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Question> findByUserName(
			@RequestParam(USER_NAME) String username) {
		long uid = Lists.newArrayList(user.findByUsername(username)).get(0).getUid();
		return questions.findByUser_Uid(uid);
	}

	@Override
	@RequestMapping(value=QUESTION_FORUM_GET_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Question> findByForumId(
			@RequestParam(FORUM_ID) long fid) {
		return questions.findByForum_Fid(fid);
	}

	@Override
	@RequestMapping(value=QUESTION_ADD_PATH, method=RequestMethod.GET)
	public @ResponseBody boolean addQuestion(
			@RequestParam(QUESTION_CONTENT)String content, 
			@RequestParam(USER_NAME)String username,
			@RequestParam(FORUM_ID)long fid) {
		Question q = new Question(content);
		q.setForum(forumRepository.findOne(fid));
		q.setUser(Lists.newArrayList(user.findByUsername(username)).get(0));
		q.getUser().addQuestion_count();
		questions.save(q);
		return true;
	}

	@Override
	@RequestMapping(value=QUESTION_FORUM_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Question> searchByQuestionInForum(
			@RequestParam(SEARCH_KEY) String key,@RequestParam(FORUM_ID) long fid) {
		return questions.searchByQuestionAndForum("%"+key+"%", fid);
	}

	@Override
	@RequestMapping(value=QUESTION_RATE_PATH,method=RequestMethod.GET)
	public @ResponseBody boolean rateQuestionById(
			@RequestParam(QUESTION_ID) long qid) {
		Question question = questions.findOne(qid);
		User questionOwner = question.getUser();
		questionOwner.addScore();
		questionOwner.addNotification(1);
		question.addRate();
		questions.save(question);
		return true;
	}

	@Override
	@RequestMapping(value=QUESTION_SORT_PATH,method=RequestMethod.GET)
	public @ResponseBody Collection<Question> getSortedQuestionList(
			@RequestParam(FORUM_ID)long fid) {
		return questions.findTop5ByForum_FidOrderByRateDesc(fid);
	}
}
