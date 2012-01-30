/**
 * Created on Dec 20, 2011
 */
package com.apress.prospring3.springblog.service;

import com.apress.prospring3.springblog.domain.AppUser;

/**
 * @author Clarence
 *
 */
public interface UserService {

	public AppUser findByUserName(String userName);
	
}
