package org.project.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.project.shoppingbackend.dao.ProductDAO;
import org.project.shoppingbackend.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * Single */
	
	@Override
	public Product get(int productId) {
		try{
			return sessionFactory
					.getCurrentSession()
					.get(Product.class, Integer.valueOf(productId));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/*
	 * List*/

	@Override
	public List<Product> list() {
		
		return sessionFactory
				.getCurrentSession()
				  .createQuery("FROM Product",Product.class)
				  	.getResultList();
	}

	/*
	 * Add*/
	
	@Override
	public boolean add(Product product) {
		try{
			sessionFactory.getCurrentSession().persist(product);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/*
	 * update*/
	
	@Override
	public boolean update(Product product) {
		try{
		
			sessionFactory.getCurrentSession().update(product);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	/*
	 * delete*/

	@Override
	public boolean delete(Product product) {
		try{
			product.setActive(false);
			//call the update method
			return this.update(product);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	
	/*
	 * Business Methods*/
	
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts,Product.class)
						.setParameter("active", true)
							.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsByCategory,Product.class)
						.setParameter("active", true)
						.setParameter("categoryId",categoryId)
							.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product WHERE active = :active ORDER BY id",Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
							.setMaxResults(count)
							.getResultList();
	}

}
