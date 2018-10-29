package org.project.shoppingbackend.dao;

import java.util.List;

import org.project.shoppingbackend.dto.Category;

public interface CategoryDao {

	List<Category> list();
	Category get(int id);
}
