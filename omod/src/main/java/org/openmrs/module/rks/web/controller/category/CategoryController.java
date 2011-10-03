package org.openmrs.module.rks.web.controller.category;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.rks.RKSService;
import org.openmrs.module.rks.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;


@Controller("RKSCategoryController")
@RequestMapping("/module/rks/category.form")
public class CategoryController {
	
Log log = LogFactory.getLog(this.getClass());
	
	@RequestMapping(method = RequestMethod.GET)
	public String firstView(@ModelAttribute("category") Category category, @RequestParam(value="categoryId",required=false) Integer id, Model model) {
		RKSService rksService =Context.getService(RKSService.class);
		if( id != null ){
			category = rksService.getCategoryById(id);
			model.addAttribute("category",category);
		}
		
		return "/module/rks/category/form";
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryPropertyEditor());
	}
	@ModelAttribute("parents")
	public List<Category> parents( HttpServletRequest request){
		RKSService rksService =Context.getService(RKSService.class);
		List<Category> parents = rksService.listCategory("", true, 0, 0);
		if(CollectionUtils.isNotEmpty(parents)){
			Integer categoryId = NumberUtils.toInt(request.getParameter("categoryId"));
			Category category = rksService.getCategoryById(categoryId);
			if(category != null){
				parents.remove(category);
			}
		}
		return parents;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("category") Category category, BindingResult bindingResult, HttpServletRequest request, SessionStatus status) {
		new CategoryValidator().validate(category, bindingResult);
		//storeValidator.validate(store, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/module/rks/category/form";
			
		}else{
			RKSService rksService = Context.getService(RKSService.class);
			//save store
			category.setCreatedBy(Context.getAuthenticatedUser().getGivenName());
			category.setCreatedOn(new Date());
			rksService.saveCategory(category);
			
			status.setComplete();
			return "redirect:/module/rks/categoryList.form";
		}
	}
}
