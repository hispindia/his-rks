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
