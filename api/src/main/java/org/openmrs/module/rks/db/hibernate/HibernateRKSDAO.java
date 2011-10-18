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

package org.openmrs.module.rks.db.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.rks.db.RKSDAO;
import org.openmrs.module.rks.model.Category;
import org.openmrs.module.rks.model.Item;

public class HibernateRKSDAO implements RKSDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat formatterExt = new SimpleDateFormat("dd/MM/yyyy");
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Hibernate session factory
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * Set session factory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Category
	 */
	public List<Category> listCategory(String name,int min, int max) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class, "category");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("category.name", "%"+name+"%"));
		}
		
		if(max > 0){
			criteria.setFirstResult(min).setMaxResults(max);
		}
		List<Category> l = criteria.list();
		
		return l;
	}
	
	public Category saveCategory(Category category) throws DAOException{
		return (Category) sessionFactory.getCurrentSession().merge(category); 
	}

	public int countListCategory(String name)  throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class, "category");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("category.name", "%"+name+"%"));
		}
		
		Number rs =  (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}
	
	public Category getCategoryById(Integer id) throws DAOException{
		return (Category)sessionFactory.getCurrentSession().get(Category.class, id);
	}
		
	public void deleteCategory(Integer id) throws DAOException{
		sessionFactory.getCurrentSession().delete(getCategoryById(id));
	}
	
	public Category getCategoryByName(String name) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class, "category");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.eq("category.name",name));
		}
		
		criteria.setMaxResults(1);
		List<Category> list= criteria.list();
		return  CollectionUtils.isEmpty(list)? null : list.get(0);
	}
	
	/**
	 * Item
	 */
	public List<Item> listItem(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome,int min, int max) throws DAOException{
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Item.class, "item")
				.createAlias("item.category", "category");
		if(category != null && category > 0){
			criteria.add(Restrictions.eq("category.id" ,category));
		}
		if(StringUtils.isNotBlank(searchName)){
			criteria.add(Restrictions.or(Restrictions.like("category.name", "%"+searchName+"%"), Restrictions.like("item.description", "%"+searchName+"%")));
		}
		if(StringUtils.isNotBlank(transactionType)){
			criteria.add(Restrictions.eq("item.transactionType",transactionType));
		}
		if (!StringUtils.isBlank(fromDateIncomeOutcome) && StringUtils.isBlank(toDateIncomeOutcome)) {
			String startFromDate = fromDateIncomeOutcome + " 00:00:00";
			String endFromDate = fromDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"item.dateIncomeOutcome", formatter.parse(startFromDate)), Restrictions.le(
						"item.dateIncomeOutcome", formatter.parse(endFromDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListItem>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		} 
		else if (StringUtils.isBlank(fromDateIncomeOutcome) && !StringUtils.isBlank(toDateIncomeOutcome)) {
			String startToDate = toDateIncomeOutcome + " 00:00:00";
			String endToDate = toDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"item.dateIncomeOutcome", formatter.parse(startToDate)), Restrictions.le(
						"item.dateIncomeOutcome", formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListItem>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		} 
		else if (!StringUtils.isBlank(fromDateIncomeOutcome) && !StringUtils.isBlank(toDateIncomeOutcome)) {
			String startToDate = fromDateIncomeOutcome + " 00:00:00";
			String endToDate = toDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"item.dateIncomeOutcome", formatter.parse(startToDate)), Restrictions.le(
						"item.dateIncomeOutcome", formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListItem>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		}
		
		if(max > 0){
			criteria.setFirstResult(min).setMaxResults(max);
		}
		List<Item> l = criteria.list();
		
		return l;
	}
	
	public Item saveItem(Item item) throws DAOException{
		return (Item) sessionFactory.getCurrentSession().merge(item); 
	}

	public int countListItem(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome)  throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Item.class, "item")
				.createAlias("item.category", "category");
		if(category != null && category > 0){
			criteria.add(Restrictions.eq("category.id" ,category));
		}
		if(StringUtils.isNotBlank(searchName)){
			criteria.add(Restrictions.or(Restrictions.like("category.name", "%"+searchName+"%"), Restrictions.like("item.description", "%"+searchName+"%")));
		}
		if(StringUtils.isNotBlank(transactionType)){
			criteria.add(Restrictions.eq("item.transactionType",transactionType));
		}
		if (!StringUtils.isBlank(fromDateIncomeOutcome) && StringUtils.isBlank(toDateIncomeOutcome)) {
			String startFromDate = fromDateIncomeOutcome + " 00:00:00";
			String endFromDate = fromDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"item.dateIncomeOutcome", formatter.parse(startFromDate)), Restrictions.le(
						"item.dateIncomeOutcome", formatter.parse(endFromDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListItem>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		} 
		else if (StringUtils.isBlank(fromDateIncomeOutcome) && !StringUtils.isBlank(toDateIncomeOutcome)) {
			String startToDate = toDateIncomeOutcome + " 00:00:00";
			String endToDate = toDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"item.dateIncomeOutcome", formatter.parse(startToDate)), Restrictions.le(
						"item.dateIncomeOutcome", formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListItem>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		} 
		else if (!StringUtils.isBlank(fromDateIncomeOutcome) && !StringUtils.isBlank(toDateIncomeOutcome)) {
			String startToDate = fromDateIncomeOutcome + " 00:00:00";
			String endToDate = toDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"item.dateIncomeOutcome", formatter.parse(startToDate)), Restrictions.le(
						"item.dateIncomeOutcome", formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListItem>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		}
		
		Number rs =  (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}
	
	public Item getItemById(Integer id) throws DAOException{
		return (Item) sessionFactory.getCurrentSession().get(Item.class, id); 
	}
		
	public void deleteItem(Integer id) throws DAOException{
		sessionFactory.getCurrentSession().delete(getItemById(id));
	}

	@Override
	public Item getItem(Integer subCategoryId,
			String transactionType, Date dateIncomeOutcome) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Item.class, "item");
		if(subCategoryId != null && subCategoryId > 0){
			criteria.add(Restrictions.eq("item.category.id", subCategoryId));
		}
		if(StringUtils.isNotBlank(transactionType)){
			criteria.add(Restrictions.eq("item.transactionType",transactionType));
		}
		String date = formatterExt.format(dateIncomeOutcome);
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add(Restrictions.and(Restrictions.ge(
					"item.dateIncomeOutcome", formatter.parse(startFromDate)), Restrictions.le(
					"item.dateIncomeOutcome", formatter.parse(endFromDate))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: "+ e.toString());
			e.printStackTrace();
		}
		
		criteria.setMaxResults(1);
		List<Item> l = criteria.list();
		return CollectionUtils.isNotEmpty(l) ? l.get(0) : null;
	}
	
	
}
