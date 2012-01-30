/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.springblog.domain.Category;
import com.apress.prospring3.springblog.service.CategoryService;

/**
 * @author Clarence
 *
 */
public class JpaCatergoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jpa-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");
		
		CategoryService categoryService = ctx.getBean("categoryService", CategoryService.class);
		
		List<Category> categories = categoryService.findAllParentCategory();
		
		for (Category category: categories) {
			System.out.println("Category id: " + category.getCategoryId());
			List<Category> subCategories = categoryService.findAllSubCategory(category.getCategoryId());
			for (Category subCategory: subCategories) {
				System.out.println("Sub-category id: " + subCategory.getCategoryId());
			}
		}
	}

}
