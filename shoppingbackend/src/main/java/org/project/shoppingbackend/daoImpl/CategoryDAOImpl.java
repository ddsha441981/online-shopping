package org.project.shoppingbackend.daoImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.shoppingbackend.dao.CategoryDAO;
import org.project.shoppingbackend.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	SessionFactory sessionFactory;

	/** For Testing Purpose static data view for fronted Page */
	/*
	 * private static List<Category> categories = new ArrayList<>();
	 * 
	 * static {
	 * 
	 * Category category = new Category(); // adding first Category
	 * category.setId(1); category.setName("Television");
	 * category.setDescription("This is some descripton of Television");
	 * category.setImageUrl("CAT-1.png");
	 * 
	 * categories.add(category);
	 * 
	 * 
	 * // adding Second Category category = new Category(); category.setId(2);
	 * category.setName("Mobile"); category.setDescription(
	 * "This is some descripton of Mobile"); category.setImageUrl("CAT-2.png");
	 * 
	 * categories.add(category);
	 * 
	 * 
	 * // adding Third Category category = new Category(); category.setId(3);
	 * category.setName("Laptop"); category.setDescription(
	 * "This is some descripton of Laptop"); category.setImageUrl("CAT-3.png");
	 * 
	 * categories.add(category);
	 * 
	 * 
	 * // adding Third Category category = new Category(); category.setId(4);
	 * category.setName("Other"); category.setDescription(
	 * "This is some descripton of Other"); category.setImageUrl("CAT-4.png");
	 * 
	 * categories.add(category); }
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Category> list() {

		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	/* Getting Single Category based on id */
	@Override
	public Category get(int id) {

		/** Use For viewing static data for fronted page Testing Purpose */

		/*
		 * //enhanced for loop for(Category category : categories){
		 * 
		 * if(category.getId() ==id) return category; }
		 */

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	/* Updating a  single category*/
	@Override
	public boolean update(Category category) {
		
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
