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

package org.openmrs.module.rks.impl;

import java.util.Date;
import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.rks.RKSService;
import org.openmrs.module.rks.db.RKSDAO;
import org.openmrs.module.rks.model.Category;
import org.openmrs.module.rks.model.Item;

public class RKSServiceImpl  extends BaseOpenmrsService implements  RKSService {

	 public RKSServiceImpl(){
	    }
	    
	    protected RKSDAO dao;
		
		public void setDao(RKSDAO dao) {
			this.dao = dao;
		}
		
		/**
		 * Category
		 */
		public List<Category> listCategory(String name, int min, int max) throws APIException{
			return dao.listCategory(name,  min, max);
		}
		
		public Category saveCategory(Category category) throws APIException{
			return dao.saveCategory(category);
		}

		public int countListCategory(String name )  throws APIException{
			return dao.countListCategory(name );
		}
		
		public Category getCategoryById(Integer id) throws APIException{
			return dao.getCategoryById(id);
		}
			
		public void deleteCategory(Integer id) throws APIException{
			dao.deleteCategory(id);
		}
		
		
		@Override
		public Category getCategoryByName(String name ) throws APIException {
			// TODO Auto-generated method stub
			return dao.getCategoryByName(name );
		}

		/**
		 * Item
		 */
		public List<Item> listItem(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome,int min, int max) throws APIException{
			return dao.listItem(category ,searchName, transactionType, fromDateIncomeOutcome, toDateIncomeOutcome, min, max);
		}
		
		public Item saveItem(Item item) throws APIException{
			return dao.saveItem(item);
		}

		public int countListItem(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome)  throws APIException{
			return dao.countListItem(category ,searchName, transactionType, fromDateIncomeOutcome,toDateIncomeOutcome);
		}
		
		public Item getItemById(Integer id) throws APIException{
			return dao.getItemById(id);
		}
			
		public void deleteItem(Integer id) throws APIException{
			dao.deleteItem(id);
		}

		public Item getItem(Integer categoryId,
				String transactionType, Date dateIncomeOutcome)
				throws APIException {
			// TODO Auto-generated method stub
			return dao.getItem(categoryId, transactionType, dateIncomeOutcome);
		}
		
}
