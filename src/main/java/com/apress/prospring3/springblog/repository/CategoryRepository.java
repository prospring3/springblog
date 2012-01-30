/**
 * Created on Dec 13, 2011
 */
package com.apress.prospring3.springblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apress.prospring3.springblog.domain.Category;

/**
 * @author Clarence
 *
 */
public interface CategoryRepository extends CrudRepository<Category, String> {
	
	@Query("select c from Category c where c.parentCategory is null")
	public List<Category> findAllParentCategory();
	
	@Query("select c from Category c where c.parentCategory.categoryId = :parentCategoryId")
	List<Category> findAllSubCategory(@Param("parentCategoryId") String parentCategoryId);
	
}
