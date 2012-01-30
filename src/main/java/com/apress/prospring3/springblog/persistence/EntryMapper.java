/**
 * Created on Oct 27, 2011
 */
package com.apress.prospring3.springblog.persistence;

import java.util.List;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.domain.SearchCriteriaPage;

/**
 * @author Clarence
 *
 */
public interface EntryMapper {

	List<Entry> findAll();
	
	Entry findById(Long id);
	
	List<Entry> findByCategoryId(String categoryId);
	
	List<Entry> findEntryByCriteria(SearchCriteriaPage searchCriteriaPage);
	
	int findEntryCountByCriteria(SearchCriteriaPage searchCriteriaPage);
	
	void insertEntry(Entry entry);
	
	void updateEntry(Entry entry);
	
	void deleteEntry(Long id);
	
}
