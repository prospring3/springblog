/**
 * Created on Dec 13, 2011
 */
package com.apress.prospring3.springblog.service;

import java.util.List;

import com.apress.prospring3.springblog.domain.Category;

/**
 * @author Clarence
 *
 */
public interface CategoryService {

	public List<Category> findAll();
	
	public List<Category> findAllParentCategory();
	
	public List<Category> findAllSubCategory(String parentCategoryId);
	
}
