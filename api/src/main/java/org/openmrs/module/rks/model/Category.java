/**
 *  Copyright 2011 Health Information Systems Project of India
 *
 *  This file is part of RKS module.
 *
 *  RKS module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  RKS module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with RKS module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

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
