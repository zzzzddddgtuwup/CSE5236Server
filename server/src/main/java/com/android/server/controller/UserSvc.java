package com.android.server.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public @ResponseBody boolean addUser(@RequestBody User u) {
		user.save(u);
		return true;
	}

	@Override
	@RequestMapping(value=USER_VALIDATE_PATH,method=RequestMethod.POST) 
	public @ResponseBody User validate(@RequestBody User u) {
		if( Lists.newArrayList(user.findAll()).contains(u))
			return Lists.newArrayList(user.findByUsername(u.getUsername())).get(0);
		return null;
	}
}
