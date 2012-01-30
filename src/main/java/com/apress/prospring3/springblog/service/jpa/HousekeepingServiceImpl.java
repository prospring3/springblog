/**
 * Created on Dec 8, 2011
 */
package com.apress.prospring3.springblog.service.jpa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.service.HousekeepingService;

/**
 * @author Clarence
 *
 */
@Service("housekeepingService")
@Repository
@Transactional
public class HousekeepingServiceImpl implements HousekeepingService {

    @Value("${audit.record.history.days}")
	private int auditHistoryDays;
	
	@Scheduled(cron="0 0 0 * * ?")
	public void auditPurgeJob() {
	    // Purge audit record logic goes here	
	}

}
