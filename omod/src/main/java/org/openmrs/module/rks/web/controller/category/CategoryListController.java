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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.rks.RKSService;
import org.openmrs.module.rks.model.Category;
import org.openmrs.module.rks.util.PagingUtil;
import org.openmrs.module.rks.util.RequestUtil;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RKSCategoryListController")
@RequestMapping("/module/rks/categoryList.form")
public class CategoryListController {
	 Log log = LogFactory.getLog(this.getClass());
		@RequestMapping(method=RequestMethod.POST)
	    public String deleteStores(@RequestParam("ids") String[] ids,HttpServletRequest request){
			String temp = "";
	    	HttpSession httpSession = request.getSession();
			Integer categoryId  = null;
			try{
				RKSService rksService =Context.getService(RKSService.class);
				if( ids != null && ids.length > 0 ){
					for(String sId : ids )
					{
						categoryId = Integer.parseInt(sId);
						if( categoryId!= null && categoryId > 0)
						{
							rksService.deleteCategory(categoryId);
						}else{
							//temp += "We can't delete store="+store.getName()+" because that store is using please check <br/>";
							temp = "This Category cannot be deleted as it is in use";
						}
					}
				}
			}catch (Exception e) {
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
				"Can not delete category ");
				log.error(e);
			}
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, StringUtils.isBlank(temp) ?  "category.deleted" : temp);
	    	
	    	return "redirect:/module/rks/categoryList.form";
	    }
		
		@RequestMapping(method=RequestMethod.GET)
		public String listStore(@RequestParam(value="pageSize",required=false)  Integer pageSize, 
		                         @RequestParam(value="currentPage",required=false)  Integer currentPage,
		                         @RequestParam(value="searchName",required=false)  String name,
		                         Map<String, Object> model, HttpServletRequest request){
			
			RKSService rksService = Context.getService(RKSService.class);
			
			int total = rksService.countListCategory(name );
			
			PagingUtil pagingUtil = new PagingUtil( RequestUtil.getCurrentLink(request) , pageSize, currentPage, total );
			
			List<Category> categories = rksService.listCategory(name , pagingUtil.getStartPos(), pagingUtil.getPageSize());
			
			model.put("categories", categories );
			
			model.put("pagingUtil", pagingUtil);
			model.put("searchName", name);
			
			return "/module/rks/category/list";
		}
}
