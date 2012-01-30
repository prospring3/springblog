/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.service.EntryAuditService;

/**
 * @author Clarence
 *
 */
public class JpaEntryAuditTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jpa-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");
		
		EntryAuditService entryAuditService = ctx.getBean("entryAuditService", EntryAuditService.class);
	
		List<Entry> entries = entryAuditService.findAuditById(1l);
		
		for (Entry entry: entries) {
			System.out.println(entry);
		}
		
	}

}
