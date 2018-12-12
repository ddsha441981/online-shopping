package org.project.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.project.shoppingbackend.dao.UserDAO;
import org.project.shoppingbackend.dto.Address;
import org.project.shoppingbackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired 
	SessionFactory sessionFactory;
	
	
	@Override
	public boolean addUser(User user) {
		
		try{
			sessionFactory.getCurrentSession().persist(user);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean addAddress(Address address) {
		try{
			
			sessionFactory.getCurrentSession().persist(address);
			return true;
		}
		catch(Exception e){
			
			e.printStackTrace();
			return false;
		}
		
	}

	/*@Override
	public boolean updateCart(Cart cart) {
		try{
			sessionFactory.getCurrentSession().update(cart);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
*/
	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email"; 
		try{
			
			return sessionFactory.getCurrentSession()
									.createQuery(selectQuery,User.class)
										.setParameter("email", email)
											.getSingleResult();
		}
		catch(Exception ex){
			
			return null;
		}
		
	}

	@Override
	public List<Address> listShippingAddresses(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :isShipping ORDER BY id DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("userId", userId)
						.setParameter("isShipping", true)
							.getResultList();
		
	}

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND billing = :isBilling";
		try{
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("userId", userId)
						.setParameter("isBilling", true)
						.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	
	/*@Override
	public boolean updateAddress(Address address) {
		try {			
			sessionFactory.getCurrentSession().update(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}	*/
	
	

	/*@Override
	public User get(int id) {
		try {			
			return sessionFactory.getCurrentSession().get(User.class, id);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}*/

	/*@Override
	public Address getAddress(int addressId) {
		try {			
			return sessionFactory.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
*/
	/*@Override
	public Address getBillingAddress(User user) {
		
		String selectQuery = "FROM Address WHERE user = :user AND billing = :billing";
		try{
			
			return sessionFactory.getCurrentSession()
									.createQuery(selectQuery, Address.class)
										.setParameter("user", user)
											.setParameter("billing", true)
												.getSingleResult();
									
								
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public List<Address> listShippingAddresses(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND shipping = :shipping";
		try{
			
			return sessionFactory.getCurrentSession()
									.createQuery(selectQuery, Address.class)
										.setParameter("user", user)
											.setParameter("shipping", true)
												.getResultList();
									
								
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}*/

	/*@Override
	public boolean addCart(Cart cart) {
		try{
			sessionFactory.getCurrentSession().persist(cart);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}*/
	
	

}
