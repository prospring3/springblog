/**
 * Created on Oct 18, 2011
 */
package com.apress.prospring3.springblog.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Clarence
 *
 */
public class AuditorAwareBean implements AuditorAware<String> {

	public String getCurrentAuditor() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String currentUser = null;
		
		if (authentication != null) {
			currentUser = authentication.getName();
		} else {
			// For import entry using batch job
			currentUser = "batch";
		}
		
		return currentUser;
	}

}
