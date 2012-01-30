/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.springblog.domain.AppUser;
import com.apress.prospring3.springblog.service.UserService;

/**
 * @author Clarence
 *
 */
public class MyBatisUserServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:mybatis-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");				

		UserService userService = ctx.getBean("userService", UserService.class);
		
		AppUser appUser = userService.findByUserName("clarence");
		
		System.out.println("User name: " + appUser.getUserName());
	}

}
