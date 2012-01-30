/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog.service.jpa;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.domain.SearchCriteria;
import com.apress.prospring3.springblog.repository.EntryRepository;
import com.apress.prospring3.springblog.service.EntryService;
import com.google.common.collect.Lists;

/**
 * @author Clarence
 *
 */
@Service("entryService")
@Repository
@Transactional
public class EntryServiceImpl implements EntryService{

	@Autowired
	private EntryRepository entryRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Entry> findAll() {
		return Lists.newArrayList(entryRepository.findAll());
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Entry> findAllByPage(Pageable pageable) {
		return entryRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Entry> findEntryByCriteria(SearchCriteria searchCriteria,
			Pageable pageable) {
		String subject = searchCriteria.getSubject();
		String categoryId = searchCriteria.getCategoryId();
		DateTime fromPostDate = searchCriteria.getFromPostDate();
		DateTime toPostDate = searchCriteria.getToPostDate();
		return entryRepository.findEntryByCriteria(subject, categoryId, fromPostDate, toPostDate, pageable);
	}

	/* (non-Javadoc)
	 * @see com.apress.prospring3.springblog.service.EntryService#findById()
	 */
	@Override
	@Transactional(readOnly=true)
	public Entry findById(Long id) {
		return entryRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.apress.prospring3.springblog.service.EntryService#findByCategoryId()
	 */
	@Override
	@Transactional(readOnly=true)	
	public List<Entry> findByCategoryId(String categoryId) {
		return entryRepository.findByCategoryId(categoryId);
	}

	/* (non-Javadoc)
	 * @see com.apress.prospring3.springblog.service.EntryService#save(com.apress.prospring3.springblog.domain.Entry)
	 */
	@Override
	public Entry save(Entry entry) {
		// If new entry, set post date to current date
		if (entry.getId() == null) {
			entry.setPostDate(new DateTime());
		}
		return entryRepository.save(entry);
	}

	/* (non-Javadoc)
	 * @see com.apress.prospring3.springblog.service.EntryService#delete(com.apress.prospring3.springblog.domain.Entry)
	 */
	@Override
	public void delete(Entry entry) {
		entryRepository.delete(entry);
	}

}
