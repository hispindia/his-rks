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

package org.openmrs.module.rks.web.controller.category;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.rks.RKSService;
import org.openmrs.module.rks.model.Category;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CategoryValidator implements  Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return Category.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	Category category = (Category) command;
    	
    	if( StringUtils.isBlank(category.getName())){
    		error.reject("rks.category.name.required");
    	}
    	
    	RKSService rksService = Context.getService(RKSService.class);
    	Category storeE = rksService.getCategoryByName(category.getName(),category.getParent());
    	if(category.getId() != null){
    		if(storeE != null && storeE.getId().intValue() != category.getId().intValue()){
    			error.reject("rks.category.name.existed");
    		}
    	}else{
    		if(storeE != null){
    	    		error.reject("rks.category.name.existed");
    		}
    	}
    	
    	//if category is PARENT now change to children will wrong
    	if(category.getId() != null ){
    		Category categoryCh = rksService.getCategoryById(category.getId()); 
    		if(categoryCh != null && categoryCh.getParent() == null && CollectionUtils.isNotEmpty(categoryCh.getSubCategories())){
    			if(category.getParent() != null){
    				error.reject("rks.category.name.cantchange");
    			}
    		}
    	}
    	
    	
    }
}
