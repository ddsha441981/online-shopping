package org.project.onlineshopping.controller;

import org.project.onlineshopping.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

	// Logger Class
	private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result ){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);

		if(result!=null){
			//multiple case
			switch(result){
			case "updated":
				mv.addObject("message", "Cart Line has been updated successfully!!!");
				break;
			case "added":
				mv.addObject("message", "Cart Line has been added successfully!!!");
				break;
			case "modified":
				mv.addObject("message", "One or more items inside cart has been modified!");
				break;
			case "deleted":
				mv.addObject("message", "Cart Line has been remove successfully!!!");
				break;
			case "maximum":
				mv.addObject("message", "Cart Line has reached maximum count!!!");
				break;
			case "unavailable":
				mv.addObject("message", "product quantity is not available !!!");
				break;
			case "error":
				mv.addObject("message", "Something went worng!!!");
				break;
			}
		}
		else {
			String response = cartService.validateCartLine();
			if(response.equals("result=modified")) {
				mv.addObject("message", "One or more items inside cart has been modified!");
			}
		}
			mv.addObject("cartLines", cartService.getCartLines());
		
		return mv;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String  updateCart(@PathVariable int cartLineId, @RequestParam int count){
		//this method declaration is cart service class
		String response = cartService.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String  updateCart(@PathVariable int cartLineId){
		//this method declaration is cart service class
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}
	
	//add cart from products and view on cartLine
	@RequestMapping("/add/{productId}/product")
	public String  addCart(@PathVariable int productId){
		//this method declaration is cart service class
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}
	
	/* after validating it redirect to checkout
	 * if result received is success proceed to checkout 
	 * else display the message to the user about the changes in cart page
	 * */	
	@RequestMapping("/validate")
	public String validateCart() {	
		String response = cartService.validateCartLine();
		if(!response.equals("result=success")) {
			return "redirect:/cart/show?"+response;
		}
		else {
			return "redirect:/cart/checkout";
		}
	}
}
