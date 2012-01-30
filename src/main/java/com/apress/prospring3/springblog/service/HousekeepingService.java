/**
 * Created on Dec 8, 2011
 */
package com.apress.prospring3.springblog.service;

/**
 * @author Clarence
 *
 */
public interface HousekeepingService {

	/**
	 * Scheduled job to purge audit records.
	 */
	public void auditPurgeJob();
	
}
