/**
 * Created on Dec 19, 2011
 */
package com.apress.prospring3.springblog.domain;

import org.joda.time.DateTime;

/**
 * @author Clarence
 *
 */
public class SearchCriteria {

	private String subject;
	
	private String categoryId;
	
	private DateTime fromPostDate;
	
	private DateTime toPostDate;

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
	
}
