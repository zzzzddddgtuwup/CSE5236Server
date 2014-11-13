package com.android.server.questionrepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends CrudRepository<Question, Long>{
	public List<Question> findByUser_Uid(long uid);
	
	public List<Question> findByForum_Fid(long fid);
	
}
