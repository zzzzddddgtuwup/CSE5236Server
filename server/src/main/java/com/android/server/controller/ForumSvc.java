package com.android.server.controller;

import java.util.Collection;

import com.android.server.api.ForumSvcApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.android.server.repository.Forum;
import com.android.server.repository.ForumRepository;
import com.google.common.collect.Lists;

@Controller
public class ForumSvc implements ForumSvcApi{
	
	@Autowired
	private ForumRepository forum;
	
	@Override
	@RequestMapping(value=ForumSvcApi.FORUM_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Forum> getForumList() {
		return Lists.newArrayList(forum.findAll());
	}
}
