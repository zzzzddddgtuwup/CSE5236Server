package com.android.server.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.android.server.api.AnswerSvcApi;
import com.android.server.repository.Answer;
import com.android.server.repository.AnswerRepository;
import com.android.server.repository.UserRepository;
import com.google.common.collect.Lists;

@Controller
public class AnswerSvc implements AnswerSvcApi {

	@Autowired
	private AnswerRepository answers;
	
	@Autowired 
	private UserRepository user;
	
	@Override
	@RequestMapping(value=ANSWER_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Answer> getMyAnswerList() {
		return Lists.newArrayList(answers.findAll());
	}

	@Override
	@RequestMapping(value=ANSWER_USER_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Answer> findByUserName(
			@RequestParam(USER_NAME) String username) {
		long uid = Lists.newArrayList(user.findByUsername(username)).get(0).getUid();
		return answers.findByUser_Uid(uid);
	}

	@Override
	@RequestMapping(value=ANSWER_SVC_PATH,method=RequestMethod.POST)
	public @ResponseBody boolean addAnswer(@RequestBody Answer answer) {
		answers.save(answer);
		return true;
	}
}
