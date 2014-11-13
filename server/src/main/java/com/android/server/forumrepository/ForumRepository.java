package com.android.server.forumrepository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends CrudRepository<Forum, Long>{
	
	@Query("select u from Forum u where u.name like ?")
	Collection<Forum> searchByPartName(String regex);
}
