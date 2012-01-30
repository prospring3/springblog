/**
 * Created on Dec 28, 2011
 */
package com.apress.prospring3.springblog.repository;

import org.springframework.data.repository.CrudRepository;

import com.apress.prospring3.springblog.domain.CommentAttachment;

/**
 * @author Clarence
 *
 */
public interface CommentAttachmentRepository extends CrudRepository<CommentAttachment, Long> {

}
