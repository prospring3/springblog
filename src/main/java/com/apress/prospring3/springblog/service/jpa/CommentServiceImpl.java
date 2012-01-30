/**
 * Created on Dec 12, 2011
 */
package com.apress.prospring3.springblog.service.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.Comment;
import com.apress.prospring3.springblog.repository.CommentRepository;
import com.apress.prospring3.springblog.service.CommentService;

/**
 * @author Clarence
 *
 */
@Service("commentService")
@Repository
@Transactional
public class CommentServiceImpl implements CommentService {

	final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);	

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CommentRepository commentResository;

	@Override
	@Transactional(readOnly=true)
	public Comment findById(Long id) {
		return commentResository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Comment> findByEntryId(Long entryId) {
		logger.info("Finding comments for entry with id {}", entryId);
		return commentResository.findByEntryId(entryId);
	}

	@Override
	public List<String> findReplyToByEntryId(Long entryId) {
		TypedQuery<String> query = em.createNamedQuery("Comment.findReplyToByEntryId", String.class);
		query.setParameter("entryId", entryId);
		return query.getResultList();
	}

	@Override
	public Comment save(Comment comment) {
		return commentResository.save(comment);
	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		
	}

}
