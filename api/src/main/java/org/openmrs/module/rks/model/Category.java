/**
 * 
 */
package org.openmrs.module.rks.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * @author cht
 *
 */
public class Category implements  Serializable {

	
	 private static final long serialVersionUID = 1L;
	  private Integer id;
	  private String name;
	  private String description;
	  private Date createdOn;
	  private String createdBy;
	  private Boolean retired = false;
	  private Category parent;
	  private Set<Category> subCategories;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getRetired() {
		return retired;
	}
	public void setRetired(Boolean retired) {
		this.retired = retired;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public Set<Category> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	  
	  
}
