package com.android.server.api;

import java.util.Collection;

import retrofit.http.GET;

import com.android.server.forumrepository.Forum;

public interface ForumSvcApi {

	// The path where we expect the VideoSvc to live
	public static final String FORUM_SVC_PATH = "/forum";
	
	public static final String FORUM_SEARCH_PATH = FORUM_SVC_PATH + "/find";
	
	@GET(FORUM_SVC_PATH)
	public Collection<Forum> getForumList();
}
