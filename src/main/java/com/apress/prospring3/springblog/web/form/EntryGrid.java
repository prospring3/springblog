/**
 * Created on Dec 18, 2011
 */
package com.apress.prospring3.springblog.web.form;

import java.util.List;

import com.apress.prospring3.springblog.domain.Entry;

/**
 * @author Clarence
 *
 */
public class EntryGrid {

	private int totalPages;
	
	private int currentPage;
	
	private long totalRecords;
	
	private List<Entry> entryData;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<Entry> getEntryData() {
		return entryData;
	}

	public void setEntryData(List<Entry> entryData) {
		this.entryData = entryData;
	}	
	
}
