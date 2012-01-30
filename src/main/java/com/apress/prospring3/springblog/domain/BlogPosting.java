/**
 * Created on Nov 1, 2011
 */
package com.apress.prospring3.springblog.domain;

import org.joda.time.DateTime;

/**
 * @author Clarence
 *
 */
public interface BlogPosting {
    
    public String getBody();
    public void setBody(String body);
    
    public DateTime getPostDate();
    public void setPostDate(DateTime postDate);
    
    public String getSubject();
    public void setSubject(String subject);	
	
}
