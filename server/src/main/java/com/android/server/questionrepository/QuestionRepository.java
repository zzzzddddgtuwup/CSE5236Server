package com.android.server.questionrepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends CrudRepository<Question, Long>{
	public List<Question> findByUser_Uid(long uid);
	
	public List<Question> findByForum_Fid(long fid);
	
	@Query("select q from Question q where q.content like ?1 and q.forum.fid = ?2")
	public List<Question> searchByQuestionAndForum(String regex, long forumId);
	
}
