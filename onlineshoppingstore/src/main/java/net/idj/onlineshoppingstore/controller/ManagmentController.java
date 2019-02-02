package net.idj.onlineshoppingstore.controller;
import java.util.List;

import javax.ejb.Handle;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import net.idj.onlineshoppingstore.utility.FileUploadUtility;
import net.idj.onlineshoppingstore.validator.ProductValidator;
import net.idj.shoppingbackend.dao.CategoryDAO;
import net.idj.shoppingbackend.dao.ProductDAO;
import net.idj.shoppingbackend.dto.Category;
import net.idj.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagmentController {
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private ProductDAO productDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagmentController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts (@RequestParam(name="operation", required=false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product();
		
		//set few of the fields
		
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product",nProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submited Successfully");
			} else if(operation.equalsIgnoreCase("category")) {
				mv.addObject("message","Category Submited Successfully");
			}
		}
		
		return mv;
	}
	
	
	@RequestMapping(value="/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProducts (@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		//fetch the product from the database
		Product nProduct = productDao.get(id);

		//set product fetch from database
		mv.addObject("product",nProduct);
		
		return mv;
	}
	
	
	//handling product submission
	//prvo se izvrsi jsp stranica pa onda ovaj ModelAttribue
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request) {
		
		
		//hendle image validator for new products
		if(mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		}else {
			if(!mProduct.getFile().getOriginalFilename().equalsIgnoreCase("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		// check if there are any errors
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("messageField", "Validation field for Product Submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		//create a new product
		if(mProduct.getId() == 0) {
			//create new product if id is 0
			productDao.add(mProduct);
		}else {
			//update the product if id is not 0
			productDao.update(mProduct);
		}
		
		
		
		if(!mProduct.getFile().getOriginalFilename().equalsIgnoreCase("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value= "/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	private String handleProductActivation(@PathVariable int id) {
		
		// is going to fetch the product from the database
		Product product = productDao.get(id);
		
		//activatinig and deactivating based on the vlaue of active field
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productDao.update(product);
		
		return (isActive)? "You have succesfully deactivated the product with id "+ product.getId():
			"You have succesfully activated the product with id "+ product.getId();
		}
	
	
	//to handle Category submission
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		//add new category
		categoryDao.add(category);
		
		
		return "redirect:/manage/products/?operation=category";
	}
	
	
	//returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCateogries(){
		return categoryDao.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}
