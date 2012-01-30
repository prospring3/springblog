/**
 * Created on Dec 28, 2011
 */
package com.apress.prospring3.springblog.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.CommentAttachment;
import com.apress.prospring3.springblog.repository.CommentAttachmentRepository;
import com.apress.prospring3.springblog.service.CommentAttachmentService;

/**
 * @author Clarence
 *
 */
@Service("commentAttachmentService")
@Repository
@Transactional
public class CommentAttachmentServiceImpl implements CommentAttachmentService {

	@Autowired
	private CommentAttachmentRepository attachmentRepository;
	
	@Override
	@Transactional(readOnly=true)
	public CommentAttachment findById(Long id) {
		return attachmentRepository.findOne(id);
	}	
	
}
