/**
 * Created on Jan 24, 2012
 */
package com.apress.prospring3.springblog.service;

import java.util.List;

import com.apress.prospring3.springblog.domain.Entry;

/**
 * @author Clarence
 *
 */
public interface EntryAuditService {

	/**
	 * Retrieve all audit for an entry base on id
	 * @param id
	 * @return
	 */
	public List<Entry> findAuditById(Long id);
	
}
