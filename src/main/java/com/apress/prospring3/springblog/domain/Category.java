/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Clarence
 *
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

	private String categoryId;
	private Category parentCategory;
	private Set<Category> subCategories = new HashSet<Category>();

	public Category() {
	}

	public Category(String categoryId) {
		this.categoryId = categoryId;
	}

	public Category(String categoryId, Category parentCategory,
			Set<Category> subCategories) {
		this.categoryId = categoryId;
		this.parentCategory = parentCategory;
		this.subCategories = subCategories;
	}

	@Id
	@Column(name = "CATEGORY_ID")
	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_CATEGORY_ID")
	public Category getParentCategory() {
		return this.parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
	public Set<Category> getSubCategories() {
		return this.subCategories;
	}

	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}

}
