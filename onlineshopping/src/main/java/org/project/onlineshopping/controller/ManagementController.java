package org.project.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.project.onlineshopping.util.FileUploadUtility;
import org.project.shoppingbackend.dao.CategoryDAO;
import org.project.shoppingbackend.dao.ProductDAO;
import org.project.shoppingbackend.dto.Category;
import org.project.shoppingbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.onlineshopping.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	//Logger Class
		private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation ){
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		Product nProduct = new Product();
		
		//set few of field
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product",nProduct);
		
		if(operation!=null){
			
			if(operation.equals("product")){
				mv.addObject("message", "Product Submited Successfully!");
			}
			else if(operation.equals("category")){
				mv.addObject("message", "Category Submited Successfully!");
			}
		}
		
		return mv;
	}
	
	//edit the product
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id){
			
			ModelAndView mv = new ModelAndView("page");
			
			mv.addObject("userClickManageProducts", true);
			mv.addObject("title", "Manage Products");
			
			//fetch the product from the database
			Product nProduct = productDAO.get(id);
			//set the product fetch from  database
			mv.addObject("product",nProduct);
			
			return mv;
			
			
	}
	
	//handling product submission
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,HttpServletRequest request){
		
		
		if(mProduct.getId() == 0){
			
			//handle image validation for new products
			new ProductValidator().validate(mProduct,results);
		}
		else{
			if(mProduct.getFile().getOriginalFilename().equals("")){
				
				new ProductValidator().validate(mProduct,results);
			}
		}
		
		
		//check if there are any errors
		if(results.hasErrors()){
			
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission !!!");

			
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		if(mProduct.getId() == 0){
			
			//create a new product record if id is 0
			productDAO.add(mProduct);
		}
		else{
			//update the product in database if id is not 0
			productDAO.update(mProduct);
		}
		//file
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
			
		}
		
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	//handle product activated or  deactivated
	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		
		System.out.println("In Activation Controller");
		//is going to fetch the product from the database
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		
		//activating or deactivating based on the value of active field 
		product.setActive(!product.isActive());
		//updating the product
		productDAO.update(product);
		
		
		return (isActive)? 
				"You have successfully deactivated the product with id " +product.getId() 
				:"You have successfully activated the product with id " +product.getId();
	}
	
	
	//to handle category submission
	@RequestMapping(value = "/category",method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		
		System.out.println("in category controller");
		//Add new Category
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	
	//returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		
		return categoryDAO.list();
	}
	
	
	@ModelAttribute("category")
	public Category getCategory(){
		
		return new Category();
	}
}
