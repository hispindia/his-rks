package org.openmrs.module.rks.db;

import java.util.Date;
import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.rks.model.Category;
import org.openmrs.module.rks.model.CategoryMoney;

public interface RKSDAO {

	/**
	 * Category
	 */
	public List<Category> listCategory(String name,Boolean parent, int min, int max) throws DAOException;
	
	public Category saveCategory(Category category) throws DAOException;

	public int countListCategory(String name,Boolean parent)  throws DAOException;
	
	public Category getCategoryById(Integer id) throws DAOException;
		
	public void deleteCategory(Integer id) throws DAOException;
	
	public Category getCategoryByName(String name,Category parent) throws DAOException;
	
	/**
	 * CategoryMoney
	 */
	public List<CategoryMoney> listCategoryMoney(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome,int min, int max) throws DAOException;
	
	public CategoryMoney saveCategoryMoney(CategoryMoney categoryMoney) throws DAOException;

	public int countListCategoryMoney(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome)  throws DAOException;
	
	public CategoryMoney getCategoryMoneyById(Integer id) throws DAOException;
	
	public CategoryMoney getCategoryMoney(Integer subCategoryId, String transactionType,Date dateIncomeOutcome) throws DAOException;
		
	public void deleteCategoryMoney(Integer id) throws DAOException;
}
