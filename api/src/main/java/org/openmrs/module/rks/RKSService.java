package org.openmrs.module.rks;

import java.util.Date;
import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.rks.model.Category;
import org.openmrs.module.rks.model.CategoryMoney;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RKSService extends OpenmrsService{
	
	/**
	 * Category
	 */
	@Transactional(readOnly = true)
	public List<Category> listCategory(String name,Boolean parent,int min, int max) throws APIException;
	
	@Transactional(readOnly=false)
	public Category saveCategory(Category category) throws APIException;

	@Transactional(readOnly = true)
	public int countListCategory(String name,Boolean parent)  throws APIException;
	
	@Transactional(readOnly = true)
	public Category getCategoryById(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public Category getCategoryByName(String name,Category parent) throws APIException;
		
	@Transactional(readOnly=false)
	public void deleteCategory(Integer id) throws APIException;
	
	
	/**
	 * CategoryMoney
	 */
	@Transactional(readOnly = true)
	public List<CategoryMoney> listCategoryMoney(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome,int min, int max) throws APIException;
	
	@Transactional(readOnly=false)
	public CategoryMoney saveCategoryMoney(CategoryMoney categoryMoney) throws APIException;

	@Transactional(readOnly = true)
	public int countListCategoryMoney(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome)  throws APIException;
	
	@Transactional(readOnly = true)
	public CategoryMoney getCategoryMoneyById(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public CategoryMoney getCategoryMoney(Integer subCategoryId, String transactionType,Date dateIncomeOutcome) throws APIException;
		
	@Transactional(readOnly=false)
	public void deleteCategoryMoney(Integer id) throws APIException;
	


}
