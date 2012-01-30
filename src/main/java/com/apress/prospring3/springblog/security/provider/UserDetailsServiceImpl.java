/**
 * Created on Dec 20, 2011
 */
package com.apress.prospring3.springblog.security.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.apress.prospring3.springblog.domain.AppUser;
import com.apress.prospring3.springblog.domain.Role;
import com.apress.prospring3.springblog.service.UserService;

/**
 * @author Clarence
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);	
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		logger.info("Loading user record for user name: {}", userName);
		
		UserDetails userDetails = null;   
		
		AppUser user = userService.findByUserName(userName);
		
		if (user != null) {
			
			String password = user.getPassword();
            
            Set<Role> roles = user.getRoles();
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (Role role: roles) {
                String roleName = role.getRoleId();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
                authorities.add(grantedAuthority);
            }   
            
            userDetails = new User(userName, password, authorities);
			
		} else {
            // If username not found, throw exception
            throw new UsernameNotFoundException("User " + userName + " not found");
		}
		
		return userDetails;
	}

}
