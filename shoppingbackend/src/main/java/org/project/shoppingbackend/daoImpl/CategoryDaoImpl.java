package org.project.shoppingbackend.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.project.shoppingbackend.dao.CategoryDao;
import org.project.shoppingbackend.dto.Category;
import org.springframework.stereotype.Repository;

@Repository("CategoryDao")
public class CategoryDaoImpl implements CategoryDao {

	private static List<Category> categories = new ArrayList<>();

	static {

		Category category = new Category();
		// adding first Category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some descripton of Television");
		category.setImageUrl("CAT-1.png");
		
		categories.add(category);

		
		// adding Second Category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is some descripton of Mobile");
		category.setImageUrl("CAT-2.png");
		
		categories.add(category);
		
		
		// adding Third Category
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is some descripton of Laptop");
		category.setImageUrl("CAT-3.png");

		categories.add(category);
		
		
		// adding Third Category
		category = new Category();
		category.setId(4);
		category.setName("Other");
		category.setDescription("This is some descripton of Other");
		category.setImageUrl("CAT-4.png");

		categories.add(category);
	}

	@Override
	public List<Category> list() {
		
		return categories;
	}

	@Override
	public Category get(int id) {
		
		//enhanced for loop
		for(Category category : categories){
			
			if(category.getId() ==id) return category;
		}
		return null;
	}

}
