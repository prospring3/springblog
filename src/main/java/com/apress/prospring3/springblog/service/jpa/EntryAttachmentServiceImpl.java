/**
 * Created on Dec 15, 2011
 */
package com.apress.prospring3.springblog.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.EntryAttachment;
import com.apress.prospring3.springblog.repository.EntryAttachmentRepository;
import com.apress.prospring3.springblog.service.EntryAttachmentService;

/**
 * @author Clarence
 *
 */
@Service("entryAttachmentService")
@Repository
@Transactional
public class EntryAttachmentServiceImpl implements EntryAttachmentService {

	@Autowired
	private EntryAttachmentRepository attachmentRepository;
	
	@Override
	@Transactional(readOnly=true)
	public EntryAttachment findById(Long id) {
		return attachmentRepository.findOne(id);
	}	
	
}
