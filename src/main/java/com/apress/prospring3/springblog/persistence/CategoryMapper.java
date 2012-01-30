/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.persistence;

import java.util.List;

import com.apress.prospring3.springblog.domain.Category;

/**
 * @author Clarence
 *
 */
public interface CategoryMapper {

	List<Category> findAll();
	
	List<Category> findAllParentCategory();
	
	List<Category> findAllSubCategory(String parentCategoryId);
	
}
