package com.android.server.controller;

import java.util.Collection;

import com.android.server.api.QuestionSvcApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.android.server.questionrepository.Question;
import com.android.server.questionrepository.QuestionRepository;
import com.android.server.userrepository.UserRepository;
import com.google.common.collect.Lists;

@Controller
public class QuestionSvc implements QuestionSvcApi{
	
	@Autowired
	private QuestionRepository questions;
	
	@Autowired
	private UserRepository user;
	
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
	@RequestMapping(value=QUESTION_FORUM_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Question> findByForumId(
			@RequestParam(FORUM_ID) long fid) {
		return questions.findByForum_Fid(fid);
	}

	@Override
	@RequestMapping(value=QUESTION_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody boolean addQuestion(@RequestBody Question q) {
		questions.save(q);
		return true;
	}

}
