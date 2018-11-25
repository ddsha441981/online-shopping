/**
 * 
 */
package org.project.shoppingbackend.dao;

import java.util.List;

import org.project.shoppingbackend.dto.Product;

/**
 * @author Deendayal Kumawat
 *
 */
public interface ProductDAO {
	
	Product get(int productId);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	//business Methods

	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);

}
