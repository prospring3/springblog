/**
 * Created on Dec 28, 2011
 */
package com.apress.prospring3.springblog.service;

import com.apress.prospring3.springblog.domain.CommentAttachment;

/**
 * @author Clarence
 *
 */
public interface CommentAttachmentService {

	public CommentAttachment findById(Long id);
	
}
