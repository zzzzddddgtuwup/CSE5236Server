package com.android.server.answerrepository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
	public Collection<Answer> findByUser_Uid(long uid);
	
	public Collection<Answer> findByQuestion_Qid(long qid);
}
