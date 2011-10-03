/**
 * 
 */
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
