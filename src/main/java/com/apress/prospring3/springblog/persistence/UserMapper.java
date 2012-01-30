/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.persistence;

import com.apress.prospring3.springblog.domain.AppUser;

/**
 * @author Clarence
 *
 */
public interface UserMapper {

	AppUser findByUserName(String userName);
	
}
