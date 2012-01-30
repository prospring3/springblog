/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.domain.SearchCriteria;
import com.apress.prospring3.springblog.service.EntryService;

/**
 * @author Clarence
 *
 */
public class JpaEntryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jpa-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");
		
		EntryService entryService = ctx.getBean("entryService", EntryService.class);
		
		PageRequest pageRequest = new PageRequest(0, 10);

		String subject = "%";
		String categoryId = "Spring";
		DateTime fromPostDate = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1900-01-01");
		DateTime toPostDate = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("2011-12-18");
		
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setSubject(subject);
		searchCriteria.setCategoryId(categoryId);
		searchCriteria.setFromPostDate(fromPostDate);
		searchCriteria.setToPostDate(toPostDate);
		
		Page<Entry> entries = entryService.findEntryByCriteria(searchCriteria, pageRequest);
		System.out.println("No. of entries:" + entries.getNumberOfElements());
		
		//Page<Entry> entries = entryService.findAllByPage(pageRequest);
		//System.out.println("No. of entries:" + entries.getNumberOfElements());
		
	}

}
