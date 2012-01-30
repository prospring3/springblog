/**
 * Created on Jan 24, 2012
 */
package com.apress.prospring3.springblog.web.blogapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.service.EntryAuditService;
import com.apress.prospring3.springblog.web.form.EntryGrid;

/**
 * @author Clarence
 *
 */
@RequestMapping("/audit")
@Controller
public class AuditController {

	private static final Logger logger = LoggerFactory.getLogger(AuditController.class);
	
	@Autowired
	private EntryAuditService entryAuditService;
	
	@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
	@ResponseBody
	public EntryGrid listEntryAudit(@PathVariable("id") Long id) {
		
		logger.info("Retrieving audit records for Entry with id: {}", id);
		
		List<Entry> auditEntries = entryAuditService.findAuditById(id);
		
		// Construct the grid data that will return as JSON data
		EntryGrid entryGrid = new EntryGrid();
		entryGrid.setCurrentPage(1);
		entryGrid.setTotalPages(1);
		entryGrid.setTotalRecords(auditEntries.size());
		entryGrid.setEntryData(auditEntries);		
		
		return entryGrid;
	}
	
}
