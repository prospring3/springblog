/**
 * Created on Oct 27, 2011
 */
package com.apress.prospring3.springblog;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.service.EntryService;

/**
 * @author Clarence
 *
 */
public class MyBatisEntryServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:mybatis-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");		
		
		EntryService entryService = ctx.getBean("entryService", EntryService.class);
		
		List<Entry> entries = entryService.findAll();
		
		//System.err.println("Size: " + entries.size());
		for (Entry entry: entries) System.out.println("Entry: " + entry);

	}

}
