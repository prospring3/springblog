/**
 * Created on Jan 24, 2012
 */
package com.apress.prospring3.springblog.service.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.service.EntryAuditService;
import com.google.common.collect.Lists;

/**
 * @author Clarence
 *
 */
@Service("entryAuditService")
@Repository
@Transactional
public class EntryAuditServiceImpl implements EntryAuditService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly=true)
	public List<Entry> findAuditById(Long id) {
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
		List<Number> revisions = auditReader.getRevisions(Entry.class, id);
		
		revisions = Lists.reverse(revisions);

		List<Entry> entries = new ArrayList<Entry>();
		Entry entry;
		for (Number revision: revisions) {
			entry = auditReader.find(Entry.class, id, revision);
			entries.add(entry);
		}
		return entries;
	}
	
}
