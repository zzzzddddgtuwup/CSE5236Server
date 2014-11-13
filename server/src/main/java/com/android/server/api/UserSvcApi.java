package com.android.server.api;

import java.util.Collection;

import com.android.server.userrepository.User;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface UserSvcApi {
	public static final String USER_SVC_PATH = "/user";
	
	@GET(USER_SVC_PATH)
	public Collection<User> getUserList();
	
	@POST(USER_SVC_PATH)
	public boolean addUser(@Body User user);
}
