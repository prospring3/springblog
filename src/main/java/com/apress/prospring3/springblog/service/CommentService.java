/**
 * Created on Dec 12, 2011
 */
package com.apress.prospring3.springblog.service;

import java.util.List;

import com.apress.prospring3.springblog.domain.Comment;

/**
 * @author Clarence
 *
 */
public interface CommentService {
	
	public Comment findById(Long id);
	
	public List<Comment> findByEntryId(Long entryId);
	
	public Comment save(Comment comment);
	
	public void delete(Comment comment);
	
	public List<String> findReplyToByEntryId(Long entryId);
}
