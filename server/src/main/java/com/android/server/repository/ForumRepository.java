package com.android.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForumRepository extends CrudRepository<Forum, Long>{
}
