package com.android.server.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.android.server.answerrepository.Answer;
import com.android.server.answerrepository.AnswerRepository;
import com.android.server.api.AnswerSvcApi;
import com.android.server.questionrepository.QuestionRepository;
import com.android.server.userrepository.User;
import com.android.server.userrepository.UserRepository;
import com.google.common.collect.Lists;

@Controller
public class AnswerSvc implements AnswerSvcApi {

	@Autowired
	private AnswerRepository answers;
	
	@Autowired 
	private UserRepository user;
	
	@Autowired 
	private QuestionRepository questionRepository;
	
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
	@RequestMapping(value=ANSWER_ADD_PATH,method=RequestMethod.GET)
	public @ResponseBody boolean addAnswer(
			@RequestParam(ANSWER_CONTENT)String content,
			@RequestParam(USER_NAME)String username,
			@RequestParam(QUESTION_ID)long qid) {
		Answer answer = new Answer(content);
		answer.setQuestion(questionRepository.findOne(qid));
		answer.setUser(Lists.newArrayList(user.findByUsername(username)).get(0));
		answer.getUser().addAnswer_count();
		answer.getQuestion().getUser().addNotification(2);
		answers.save(answer);
		return true;
	}

	@Override
	@RequestMapping(value=ANSWER_BY_QUESTION_ID_PATH,method=RequestMethod.GET)
	public @ResponseBody Collection<Answer> findByQuestionId(
			@RequestParam(QUESTION_ID) long qid) {
		return answers.findByQuestion_Qid(qid);
	}

	@Override
	@RequestMapping(value=ANSWER_RATE_PATH,method=RequestMethod.GET)
	public @ResponseBody boolean rateAnswerById(
			@RequestParam(ANSWER_ID) long aid) {
		Answer a = answers.findOne(aid);
		User answerOwnerUser = a.getUser();
		answerOwnerUser.addScore();
		answerOwnerUser.addNotification(3);
		a.addRate();
		answers.save(a);
		return true;
	}
}
