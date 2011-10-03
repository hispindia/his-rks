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
import org.openmrs.module.rks.model.CategoryMoney;

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
	public List<Category> listCategory(String name,Boolean parent,int min, int max) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class, "category");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("category.name", "%"+name+"%"));
		}
		if(parent != null ){
			if(parent){
				criteria.add(Restrictions.isNull("category.parent"));
			}else{
				criteria.add(Restrictions.isNotNull("category.parent"));
			}
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

	public int countListCategory(String name,Boolean parent)  throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class, "category");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("category.name", "%"+name+"%"));
		}
		if(parent != null ){
			if(parent){
				criteria.add(Restrictions.isNull("category.parent"));
			}else{
				criteria.add(Restrictions.isNotNull("category.parent"));
			}
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
	
	public Category getCategoryByName(String name,Category parent) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class, "category");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.eq("category.name",name));
		}
		if(parent != null){
			criteria.add(Restrictions.eq("category.parent.id",parent.getId()));
		}
		criteria.setMaxResults(1);
		List<Category> list= criteria.list();
		return  CollectionUtils.isEmpty(list)? null : list.get(0);
	}
	
	/**
	 * CategoryMoney
	 */
	public List<CategoryMoney> listCategoryMoney(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome,int min, int max) throws DAOException{
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CategoryMoney.class, "categoryMoney")
				.createAlias("categoryMoney.subCategory", "category");
		if(category != null && category > 0){
			criteria.add(Restrictions.eq("category.parent.id" ,category));
		}
		if(StringUtils.isNotBlank(searchName)){
			criteria.add(Restrictions.or(Restrictions.like("category.name", "%"+searchName+"%"), Restrictions.like("categoryMoney.description", "%"+searchName+"%")));
		}
		if(StringUtils.isNotBlank(transactionType)){
			criteria.add(Restrictions.eq("categoryMoney.transactionType",transactionType));
		}
		if (!StringUtils.isBlank(fromDateIncomeOutcome) && StringUtils.isBlank(toDateIncomeOutcome)) {
			String startFromDate = fromDateIncomeOutcome + " 00:00:00";
			String endFromDate = fromDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"categoryMoney.dateIncomeOutcome", formatter.parse(startFromDate)), Restrictions.le(
						"categoryMoney.dateIncomeOutcome", formatter.parse(endFromDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListCategoryMoney>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		} 
		else if (StringUtils.isBlank(fromDateIncomeOutcome) && !StringUtils.isBlank(toDateIncomeOutcome)) {
			String startToDate = toDateIncomeOutcome + " 00:00:00";
			String endToDate = toDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"categoryMoney.dateIncomeOutcome", formatter.parse(startToDate)), Restrictions.le(
						"categoryMoney.dateIncomeOutcome", formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListCategoryMoney>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		} 
		else if (!StringUtils.isBlank(fromDateIncomeOutcome) && !StringUtils.isBlank(toDateIncomeOutcome)) {
			String startToDate = fromDateIncomeOutcome + " 00:00:00";
			String endToDate = toDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"categoryMoney.dateIncomeOutcome", formatter.parse(startToDate)), Restrictions.le(
						"categoryMoney.dateIncomeOutcome", formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListCategoryMoney>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		}
		
		if(max > 0){
			criteria.setFirstResult(min).setMaxResults(max);
		}
		List<CategoryMoney> l = criteria.list();
		
		return l;
	}
	
	public CategoryMoney saveCategoryMoney(CategoryMoney categoryMoney) throws DAOException{
		return (CategoryMoney) sessionFactory.getCurrentSession().merge(categoryMoney); 
	}

	public int countListCategoryMoney(Integer category,String searchName,String transactionType,String fromDateIncomeOutcome,String toDateIncomeOutcome)  throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CategoryMoney.class, "categoryMoney")
				.createAlias("categoryMoney.subCategory", "category");
		if(category != null && category > 0){
			criteria.add(Restrictions.eq("category.parent.id" ,category));
		}
		if(StringUtils.isNotBlank(searchName)){
			criteria.add(Restrictions.or(Restrictions.like("category.name", "%"+searchName+"%"), Restrictions.like("categoryMoney.description", "%"+searchName+"%")));
		}
		if(StringUtils.isNotBlank(transactionType)){
			criteria.add(Restrictions.eq("categoryMoney.transactionType",transactionType));
		}
		if (!StringUtils.isBlank(fromDateIncomeOutcome) && StringUtils.isBlank(toDateIncomeOutcome)) {
			String startFromDate = fromDateIncomeOutcome + " 00:00:00";
			String endFromDate = fromDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"categoryMoney.dateIncomeOutcome", formatter.parse(startFromDate)), Restrictions.le(
						"categoryMoney.dateIncomeOutcome", formatter.parse(endFromDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListCategoryMoney>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		} 
		else if (StringUtils.isBlank(fromDateIncomeOutcome) && !StringUtils.isBlank(toDateIncomeOutcome)) {
			String startToDate = toDateIncomeOutcome + " 00:00:00";
			String endToDate = toDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"categoryMoney.dateIncomeOutcome", formatter.parse(startToDate)), Restrictions.le(
						"categoryMoney.dateIncomeOutcome", formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListCategoryMoney>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		} 
		else if (!StringUtils.isBlank(fromDateIncomeOutcome) && !StringUtils.isBlank(toDateIncomeOutcome)) {
			String startToDate = fromDateIncomeOutcome + " 00:00:00";
			String endToDate = toDateIncomeOutcome + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"categoryMoney.dateIncomeOutcome", formatter.parse(startToDate)), Restrictions.le(
						"categoryMoney.dateIncomeOutcome", formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countListCategoryMoney>>Error convert date: "+ e.toString());
				e.printStackTrace();
			}
		}
		
		Number rs =  (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}
	
	public CategoryMoney getCategoryMoneyById(Integer id) throws DAOException{
		return (CategoryMoney) sessionFactory.getCurrentSession().get(CategoryMoney.class, id); 
	}
		
	public void deleteCategoryMoney(Integer id) throws DAOException{
		sessionFactory.getCurrentSession().delete(getCategoryMoneyById(id));
	}

	@Override
	public CategoryMoney getCategoryMoney(Integer subCategoryId,
			String transactionType, Date dateIncomeOutcome) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CategoryMoney.class, "categoryMoney");
		if(subCategoryId != null && subCategoryId > 0){
			criteria.add(Restrictions.eq("categoryMoney.subCategory.id", subCategoryId));
		}
		if(StringUtils.isNotBlank(transactionType)){
			criteria.add(Restrictions.eq("categoryMoney.transactionType",transactionType));
		}
		String date = formatterExt.format(dateIncomeOutcome);
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add(Restrictions.and(Restrictions.ge(
					"categoryMoney.dateIncomeOutcome", formatter.parse(startFromDate)), Restrictions.le(
					"categoryMoney.dateIncomeOutcome", formatter.parse(endFromDate))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: "+ e.toString());
			e.printStackTrace();
		}
		
		criteria.setMaxResults(1);
		List<CategoryMoney> l = criteria.list();
		return CollectionUtils.isNotEmpty(l) ? l.get(0) : null;
	}
	
	
}
