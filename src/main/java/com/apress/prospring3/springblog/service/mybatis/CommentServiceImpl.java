/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.service.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.Comment;
import com.apress.prospring3.springblog.service.CommentService;

/**
 * @author Clarence
 *
 */
@Service("commentService")
@Repository
@Transactional
public class CommentServiceImpl implements CommentService {

	@Override
	@Transactional(readOnly=true)
	public Comment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Comment> findByEntryId(Long entryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<String> findReplyToByEntryId(Long entryId) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
