/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog.repository;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.apress.prospring3.springblog.domain.Entry;

/**
 * @author Clarence
 *
 */
public interface EntryRepository extends PagingAndSortingRepository<Entry, Long> {

	public List<Entry> findByCategoryId(String categoryId);
	
	@Query("select e from Entry e where e.subject like :subject and e.categoryId like :categoryId and e.postDate between :fromPostDate and :toPostDate")
	public Page<Entry> findEntryByCriteria(@Param("subject") String subject,
			                               @Param("categoryId") String categoryId, 
			                               @Param("fromPostDate") DateTime fromPostDate, 
			                               @Param("toPostDate") DateTime toPostDate,
			                               Pageable pageable);
	
}
