/**
 * Created on Dec 15, 2011
 */
package com.apress.prospring3.springblog.repository;

import org.springframework.data.repository.CrudRepository;

import com.apress.prospring3.springblog.domain.EntryAttachment;

/**
 * @author Clarence
 *
 */
public interface EntryAttachmentRepository extends CrudRepository<EntryAttachment, Long> {
	
}
