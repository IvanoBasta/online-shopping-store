package net.idj.onlineshoppingstore.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
			}
		}
		
		return mv;
	}
	
	//handling product submission
	//prvo se izvrsi jsp stranica pa onda ovaj ModelAttribue
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request) {
		
		new ProductValidator().validate(mProduct, results);
		
		// check if there are any errors
		
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation field for Product Submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		//create a new product
		productDao.add(mProduct);
		
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	//returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCateogries(){
		return categoryDao.list();
	}
	
	
}
