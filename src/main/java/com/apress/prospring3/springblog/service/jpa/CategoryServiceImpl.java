/**
 * Created on Dec 13, 2011
 */
package com.apress.prospring3.springblog.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.Category;
import com.apress.prospring3.springblog.repository.CategoryRepository;
import com.apress.prospring3.springblog.service.CategoryService;
import com.google.common.collect.Lists;

/**
 * @author Clarence
 *
 */
@Service("categoryService")
@Repository
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Category> findAll() {
		return Lists.newArrayList(categoryRepository.findAll());
	}

	@Override
	@Transactional(readOnly=true)	
	public List<Category> findAllParentCategory() {
		return categoryRepository.findAllParentCategory();
	}

	@Override
	@Transactional(readOnly=true)	
	public List<Category> findAllSubCategory(String parentCategoryId) {
		return categoryRepository.findAllSubCategory(parentCategoryId);
	}

}
