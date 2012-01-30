/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog.service.jpa;

import org.springframework.stereotype.Service;

import com.apress.prospring3.springblog.service.HelloService;

/**
 * @author Clarence
 *
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.apress.prospring3.springblog.service.HelloService#sayHello()
	 */
	@Override
	public String sayHello() {
		return "Hello JPA Imlementation!";
	}

}
