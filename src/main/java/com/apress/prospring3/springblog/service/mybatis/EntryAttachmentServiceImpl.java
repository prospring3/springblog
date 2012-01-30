/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.service.mybatis;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.EntryAttachment;
import com.apress.prospring3.springblog.service.EntryAttachmentService;

/**
 * @author Clarence
 *
 */
@Service("entryAttachmentService")
@Repository
@Transactional
public class EntryAttachmentServiceImpl implements EntryAttachmentService {

	@Override
	@Transactional(readOnly=true)
	public EntryAttachment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
