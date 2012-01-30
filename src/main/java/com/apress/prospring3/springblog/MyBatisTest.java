/**
 * Created on Oct 27, 2011
 */
package com.apress.prospring3.springblog;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.springblog.domain.Entry;
import com.apress.prospring3.springblog.service.EntryService;

/**
 * @author Clarence
 *
 */
public class MyBatisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:mybatis-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");		
		
		EntryService entryService = ctx.getBean("entryService", EntryService.class);
		
		List<Entry> entries = entryService.findByCategoryId("Spring");
		
		for (Entry entry: entries) {
			System.out.println(entry);
		}	
		
		System.out.println("Finding entry with id 1");
		Entry entry = entryService.findById(1l);
		System.out.println(entry);	
		
		// insert entry
		entry = new Entry();
		entry.setSubject("Testing entry clarence");
		entry.setBody("Testing entry clarence");
		entry.setPostDate(new DateTime());
		entry.setCategoryId("Spring");
		entry.setCreatedBy("clarence");
		DateTime currentDateTime = new DateTime();
		entry.setCreatedDate(currentDateTime);
		entry.setLastModifiedBy("clarence");
		entry.setLastModifiedDate(currentDateTime);
		entryService.save(entry);
		System.out.println("New entry insert successfully");
		
        entries = entryService.findByCategoryId("Spring");
		
		for (Entry entryTemp: entries) {
			System.out.println(entryTemp);
		}
		
		// Delete entry
		System.out.println("Deleting entry with id 2");
		entry = entryService.findById(2l);
		entryService.delete(entry);
		
        entries = entryService.findByCategoryId("Spring");
		
		for (Entry entryTemp: entries) {
			System.out.println(entryTemp);
		}	
		
		// Update entry
		System.out.println("Updating entry with id 1");
		entry = entryService.findById(1l);
		entry.setSubject("Updated entry subject crap");
		currentDateTime = new DateTime();
		entry.setLastModifiedDate(currentDateTime);
		entryService.save(entry);
		System.out.println("Entry updated successfully");
		
        entries = entryService.findByCategoryId("Spring");
		
		for (Entry entryTemp: entries) {
			System.out.println(entryTemp);
		}			

	}

}
