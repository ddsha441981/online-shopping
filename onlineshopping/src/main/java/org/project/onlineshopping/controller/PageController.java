package org.project.onlineshopping.controller;

import org.project.shoppingbackend.dao.CategoryDao;
import org.project.shoppingbackend.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		// mv.addObject("greeting", "Welcome to Spring MVC ");
		mv.addObject("title", "Home");

		// passing the list of Categories
		mv.addObject("categories", categoryDao.list());

		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}

	/*
	 * Method to load all the products and based on category
	 */

		@RequestMapping(value = "/show/all/products")
		public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passing the list of Categories
		mv.addObject("categories", categoryDao.list());

		mv.addObject("userClickAllProducts", true);
		return mv;
	}
		
		@RequestMapping(value  = "/show/category/{id}/products")
		public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		//categoryDAO to fetch a single category
		Category category = null;
		category = categoryDao.get(id);
		
		mv.addObject("title", category.getName());

		// passing the list of Categories
		mv.addObject("categories", categoryDao.list());

		// passing the single of Category Object
		mv.addObject("category", category);

		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}


	/*
	 * @RequestParam is a query string Its for Test Purpose
	 */
	/*
	 * @RequestMapping(value = "/test") public ModelAndView
	 * test(@RequestParam(value = "greeting",required = false ) String greeting)
	 * {
	 * 
	 * if(greeting == null){ greeting = "Hello There"; } ModelAndView mv = new
	 * ModelAndView("page"); mv.addObject("greeting", greeting); return mv; }
	 */

	/*
	 * @@PathVariable Its for Test Purpose
	 */
	/*
	 * @RequestMapping(value = "/test/{greeting}") public ModelAndView
	 * test(@PathVariable("greeting") String greeting) {
	 * 
	 * if(greeting == null){ greeting = "Hello There"; } ModelAndView mv = new
	 * ModelAndView("page"); mv.addObject("greeting", greeting); return mv; }
	 */
}
