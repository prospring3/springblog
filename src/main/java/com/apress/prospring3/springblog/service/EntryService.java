/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.domain.SearchCriteria;

/**
 * @author Clarence
 *
 */
public interface EntryService {

	public List<Entry> findAll();
	
	public Entry findById(Long id);
	
	public List<Entry> findByCategoryId(String categoryId);
	
	public Entry save(Entry entry);
	
	public void delete(Entry entry);
	
	public Page<Entry> findAllByPage(Pageable pageable);
	
	public Page<Entry> findEntryByCriteria(SearchCriteria searchCriteria, Pageable pageable);
	
}
