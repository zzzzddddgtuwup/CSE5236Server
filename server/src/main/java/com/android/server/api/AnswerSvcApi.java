package com.android.server.api;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import com.android.server.answerrepository.Answer;

public interface AnswerSvcApi {
	public static final String USER_NAME = "username";
	
	public static final String QUESTION_ID = "question_id";
	
	public static final String ANSWER_ID = "answer_id";

	public static final String ANSWER_SVC_PATH = "/answer";

	public static final String ANSWER_USER_SEARCH_PATH = 
			ANSWER_SVC_PATH + "/find";
	
	public static final String ANSWER_BY_QUESTION_ID_PATH =
			ANSWER_SVC_PATH + "/findbyId";
	
	public static final String ANSWER_RATE_PATH = 
			ANSWER_SVC_PATH + "/rate";
	
	@GET(ANSWER_SVC_PATH)
	public Collection<Answer> getMyAnswerList();
	
	@GET(ANSWER_USER_SEARCH_PATH)
	public Collection<Answer> findByUserName(@Query(USER_NAME) String username);
	
	@POST(ANSWER_SVC_PATH)
	public boolean addAnswer(@Body Answer answer);
	
	@GET(ANSWER_BY_QUESTION_ID_PATH)
	public Collection<Answer> findByQuestionId(@Query(QUESTION_ID) long qid);
	
	@GET(ANSWER_RATE_PATH)
	public boolean rateAnswerById(@Query(ANSWER_ID) long aid);
}
