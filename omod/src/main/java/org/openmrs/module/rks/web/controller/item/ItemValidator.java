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

package org.openmrs.module.rks.web.controller.item;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.rks.RKSService;
import org.openmrs.module.rks.model.Item;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 
 * <p> Class: ItemValidator </p>
 * <p> Package: org.openmrs.module.rks.web.controller.item </p> 
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Aug 10, 2011 9:12:21 AM </p>
 * <p> Update date: Aug 10, 2011 9:12:21 AM </p>
 *
 */
public class ItemValidator implements  Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return Item.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	Item item = (Item) command;
    	
    	if( item.getCategory() == null){
    		error.reject("rks.item.category.required");
    	}
    	if( StringUtils.isBlank(item.getTransactionType() )){
    		error.reject("rks.item.transactionType.required");
    	}
    	RKSService inventoryService = Context.getService(RKSService.class);
    	Item itemE = inventoryService.getItem(item.getCategory().getId(), item.getTransactionType(), item.getDateIncomeOutcome());
    	if(item.getId() != null){
    		if(itemE != null && itemE.getId().intValue() != item.getId().intValue()){
    			error.reject("rks.item.existed");
    		}
    	}else{
    		if(itemE != null){
    	    		error.reject("rks.item.existed");
    		}
    	}
    	
    	
    }
}
