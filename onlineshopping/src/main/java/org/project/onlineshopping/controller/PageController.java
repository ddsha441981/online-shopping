package org.project.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.onlineshopping.exception.ProductNotFoundException;
import org.project.shoppingbackend.dao.CategoryDAO;
import org.project.shoppingbackend.dao.ProductDAO;
import org.project.shoppingbackend.dto.Category;
import org.project.shoppingbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	// Logger Class
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired(required = true)
	private CategoryDAO categoryDAO;

	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		// mv.addObject("greeting", "Welcome to Spring MVC ");
		mv.addObject("title", "Home");

		// logger definition
		logger.info("Inside PageController index Method -INFO");
		logger.debug("Inside PageController index Method -DEBUG");

		// passing the list of Categories
		mv.addObject("categories", categoryDAO.list());

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
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickAllProducts", true);
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		// categoryDAO to fetch a single category
		Category category = null;
		category = categoryDAO.get(id);

		mv.addObject("title", category.getName());

		// passing the list of Categories
		mv.addObject("categories", categoryDAO.list());

		// passing the single of Category Object
		mv.addObject("category", category);

		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}

	/*
	 * Viewing a Single Product
	 */

	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");

		Product product = productDAO.get(id);

		// To handle custom Exception here
		if (product == null)
			throw new ProductNotFoundException();

		// Update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		// ...............................

		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);

		return mv;

	}

	// having similar mapping to our flow id
	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");

		return mv;
	}

	/*
	 * Spring Security custom Login mapping
	 */

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		
		ModelAndView mv = new ModelAndView("login");
		
		if(error!=null){
			mv.addObject("message", "Invaild Username and Password!!!");
		}
		
		if(logout!=null){
			mv.addObject("logout", "User has been successfully loged out!!!");
		}
		
		mv.addObject("title", "Login");
		return mv;
	}

	    /* access denied page for unauthorized person*/
		@RequestMapping(value = "/access-denied")
		public ModelAndView accessDenied() {
			ModelAndView mv = new ModelAndView("error");
			mv.addObject("title", "403-Access Denied");
			mv.addObject("errorTitle", "Aha!  Caught You");
			mv.addObject("errorDescription", "You are not authorized to view this page!!!");

			return mv;
		}
	
		/*Logout perform*/
		@RequestMapping(value = "/perform-logout")
		public String logout(HttpServletRequest request, HttpServletResponse response){
			
			//first we are going to fetch the user authentication
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication!=null){
				new SecurityContextLogoutHandler().logout(request, response, authentication);
			}
			return "redirect:/login?logout";
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
