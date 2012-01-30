/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.AppUser;
import com.apress.prospring3.springblog.persistence.UserMapper;
import com.apress.prospring3.springblog.service.UserService;

/**
 * @author Clarence
 *
 */
@Service("userService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional(readOnly=true)
	public AppUser findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}

}
