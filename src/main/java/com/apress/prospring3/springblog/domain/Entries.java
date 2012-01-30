/**
 * Created on Dec 12, 2011
 */
package com.apress.prospring3.springblog.domain;

import java.util.List;

/**
 * @author Clarence
 *
 */
public class Entries {

	private List<Entry> entries;

	public Entries() {
	}
	
	public Entries(List<Entry> entries) {
		this.entries = entries;
	}
	
	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
}
