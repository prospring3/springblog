/**
 * Created on Dec 12, 2011
 */
package com.apress.prospring3.springblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apress.prospring3.springblog.domain.Comment;

/**
 * @author Clarence
 *
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Query("select c from Comment c where c.entry.id = :entryId")
	public List<Comment> findByEntryId(@Param("entryId") Long entryId);
	
}
