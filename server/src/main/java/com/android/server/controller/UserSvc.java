package com.android.server.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.android.server.api.UserSvcApi;
import com.android.server.userrepository.User;
import com.android.server.userrepository.UserRepository;
import com.google.common.collect.Lists;

@Controller
public class UserSvc implements UserSvcApi{
	@Autowired
	private UserRepository user;

	@Override
	@RequestMapping(value=USER_SVC_PATH,method=RequestMethod.GET)
	public @ResponseBody Collection<User> getUserList() {
		return Lists.newArrayList(user.findAll());
	}

	@Override
	@RequestMapping(value=USER_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody int addUser(@RequestBody User u) {
		if(user.findByUsername(u.getUsername()).size()!=0){
			return -1;//username existed
		}else{
			user.save(u);
			return 1;
		}
	}

	@Override
	@RequestMapping(value=USER_VALIDATE_PATH,method=RequestMethod.POST) 
	public @ResponseBody User validate(@RequestBody User u) {
		if( Lists.newArrayList(user.findAll()).contains(u))
			return Lists.newArrayList(user.findByUsername(u.getUsername())).get(0);
		return null;
	}

	@Override
	@RequestMapping(value=USER_NOTIFICATION_PATH,method=RequestMethod.GET)
	public @ResponseBody Collection<Integer> getNotificationSet(
			@RequestParam(USER_NAME)String username) {
		User u = Lists.newArrayList(user.findByUsername(username)).get(0);
		List<Integer> result = new ArrayList<>();
		if(u.isNotification_question_rated()) result.add(1);
		if(u.isNotification_answerd()) result.add(2);
		if(u.isNotification_answer_rated()) result.add(3);
		u.clearNotification();
		user.save(u);
		return result;
	}

	@Override
	@RequestMapping(value=USER_INFO_PATH,method=RequestMethod.GET)
	public @ResponseBody User getInfoByName(
			@RequestParam(USER_NAME) String username) {
		return Lists.newArrayList(user.findByUsername(username)).get(0);
	}		
}
