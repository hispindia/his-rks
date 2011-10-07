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
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author cht
 *
 */
public class CategoryMoney implements  Serializable {
	
	  public static final String[] TRANSACTION_NAMES = {"Income sources", "Expenditure sources" };
	  
	  private static final long serialVersionUID = 1L;
	  private Integer id;
	  private Date createdOn;
	  private String createdBy;
	  private Boolean retired = false;
	  private Category subCategory;
	  private String transactionType;
	  private String description;
	  private Date dateIncomeOutcome;
	  private BigDecimal amount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Category getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(Category subCategory) {
		this.subCategory = subCategory;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateIncomeOutcome() {
		return dateIncomeOutcome;
	}
	public void setDateIncomeOutcome(Date dateIncomeOutcome) {
		this.dateIncomeOutcome = dateIncomeOutcome;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	  
	  
	  
}
