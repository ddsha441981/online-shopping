package org.project.onlineshopping.controller;

import java.util.List;

import org.project.shoppingbackend.dao.ProductDAO;
import org.project.shoppingbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/json/data")
public class JsonDataController {

	// Logger Class
		private static final Logger logger = LoggerFactory.getLogger(JsonDataController.class);

	
	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value = "/all/products")
	@ResponseBody
	public List<Product> getAllProducts() {

		return productDAO.listActiveProducts();
	}
	
	@RequestMapping(value = "/admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin() {

		return productDAO.list();
	}

	@RequestMapping(value = "/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory (@PathVariable int id) {

		return productDAO.listActiveProductsByCategory(id);
	}
}
