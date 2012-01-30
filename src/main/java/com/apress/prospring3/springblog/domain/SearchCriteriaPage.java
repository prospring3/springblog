/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.domain;

import org.joda.time.DateTime;

/**
 * @author Clarence
 *
 */
public class SearchCriteriaPage {

	private String subject;
	
	private String categoryId;
	
	private DateTime fromPostDate;
	
	private DateTime toPostDate;
	
	private int offset;
	
	private int pageSize;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public DateTime getFromPostDate() {
		return fromPostDate;
	}

	public void setFromPostDate(DateTime fromPostDate) {
		this.fromPostDate = fromPostDate;
	}

	public DateTime getToPostDate() {
		return toPostDate;
	}

	public void setToPostDate(DateTime toPostDate) {
		this.toPostDate = toPostDate;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
