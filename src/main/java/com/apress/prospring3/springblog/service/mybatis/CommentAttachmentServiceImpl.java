/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.service.mybatis;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.CommentAttachment;
import com.apress.prospring3.springblog.service.CommentAttachmentService;

/**
 * @author Clarence
 *
 */
@Service("commentAttachmentService")
@Repository
@Transactional
public class CommentAttachmentServiceImpl implements CommentAttachmentService {

	@Override
	@Transactional(readOnly=true)
	public CommentAttachment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
