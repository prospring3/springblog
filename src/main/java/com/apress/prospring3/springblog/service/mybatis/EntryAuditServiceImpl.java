/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.service.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.service.EntryAuditService;

/**
 * @author Clarence
 *
 */
@Service("entryAuditService")
@Repository
@Transactional
public class EntryAuditServiceImpl implements EntryAuditService {

	@Override
	@Transactional(readOnly=true)
	public List<Entry> findAuditById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
