package org.project.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.project.shoppingbackend.dao.CategoryDAO;
import org.project.shoppingbackend.dto.Category;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CategoryTestCase {

	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private  Category category; 
	
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("org.project.shoppingbackend");
		
		//context.scan("org.project.shoppingbackend.config");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO"); 
	}
	
	/* Test Case For Add Method */
	/*@Test
	public void testAddCategory(){
		category = new Category();
		
		category.setName("Mobile");
		category.setDescription("This is some descripton of Mobile");
		category.setImageUrl("CAT-105.png");
		assertEquals("Successfully added a category inside the table ", true,categoryDAO.add(category));
	}*/
	
	
	/* Test Case For get single category  from the table */
	/*@Test
	public void testGetCategory(){
		
		category = categoryDAO.get(3);
		assertEquals("Successfully fetched a single category from the table! ", "Mobile",category.getName());
	}*/
	
	
	/* Test Case For get single category  in the table */
	/*@Test
	public void testUpdateCategory(){
		
		category = categoryDAO.get(1);
		category.setName("TV");
		assertEquals("Successfully updated a single category in the table! ", true,categoryDAO.update(category));
	}*/
	
	
	/* Test Case For delete single category  in the table */
	/*@Test
	public void testDeleteCategory(){
		
		category = categoryDAO.get(3);
		assertEquals("Successfully deleted a single category in the table! ", true,categoryDAO.delete(category));
	}*/
	
	
	/* Test Case For delete single category  in the table 
	@Test
	public void testListCategory(){
		
		assertEquals("Successfully fetched  categories from the table! ", 2,categoryDAO.list().size());
	}*/
	
	
	@Test
	public void testCRUDCategory(){
		
		//Add Operation
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("This is some descripton of Laptop");
		category.setImageUrl("CAT-1.png");
		assertEquals("Successfully added a category inside the table ", true,categoryDAO.add(category));
		
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is some descripton of Television");
		category.setImageUrl("CAT-2.png");
		assertEquals("Successfully added a category inside the table ", true,categoryDAO.add(category));
		
		//Fetching and updating the category
		
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Successfully updated a single category in the table! ", true,categoryDAO.update(category));

		
		//Delete the category
		
		assertEquals("Successfully deleted a single category in the table! ", true,categoryDAO.delete(category));

		//fetching the list
	
		assertEquals("Successfully fetched  categories from the table! ", 1,categoryDAO.list().size());
	}
}

