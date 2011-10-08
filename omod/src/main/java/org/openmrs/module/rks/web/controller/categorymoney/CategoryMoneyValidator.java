/**
 *  Copyright 2010 Health Information Systems Project of India
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

package org.openmrs.module.rks.web.controller.categorymoney;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.rks.RKSService;
import org.openmrs.module.rks.model.CategoryMoney;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 
 * <p> Class: CategoryMoneyValidator </p>
 * <p> Package: org.openmrs.module.rks.web.controller.categorymoney </p> 
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Aug 10, 2011 9:12:21 AM </p>
 * <p> Update date: Aug 10, 2011 9:12:21 AM </p>
 *
 */
public class CategoryMoneyValidator implements  Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return CategoryMoney.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	CategoryMoney categoryMoney = (CategoryMoney) command;
    	
    	if( categoryMoney.getSubCategory() == null){
    		error.reject("rks.categoryMoney.subCategory.required");
    	}
    	if( StringUtils.isBlank(categoryMoney.getTransactionType() )){
    		error.reject("rks.categoryMoney.transactionType.required");
    	}
    	RKSService inventoryService = Context.getService(RKSService.class);
    	CategoryMoney categoryMoneyE = inventoryService.getCategoryMoney(categoryMoney.getSubCategory().getId(), categoryMoney.getTransactionType(), categoryMoney.getDateIncomeOutcome());
    	if(categoryMoney.getId() != null){
    		if(categoryMoneyE != null && categoryMoneyE.getId().intValue() != categoryMoney.getId().intValue()){
    			error.reject("rks.categoryMoney.existed");
    		}
    	}else{
    		if(categoryMoneyE != null){
    	    		error.reject("rks.categoryMoney.existed");
    		}
    	}
    	
    	
    }
}
