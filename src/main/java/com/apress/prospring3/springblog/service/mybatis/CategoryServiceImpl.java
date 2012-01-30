/**
 * Created on Jan 25, 2012
 */
package com.apress.prospring3.springblog.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.springblog.domain.Category;
import com.apress.prospring3.springblog.persistence.CategoryMapper;
import com.apress.prospring3.springblog.service.CategoryService;

/**
 * @author Clarence
 *
 */
@Service("categoryService")
@Repository
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	@Transactional(readOnly=true)
	public List<Category> findAll() {
		return categoryMapper.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> findAllParentCategory() {
		return categoryMapper.findAllParentCategory();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> findAllSubCategory(String parentCategoryId) {
		return categoryMapper.findAllSubCategory(parentCategoryId);
	}

}
