package net.idj.onlineshoppingstore.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import antlr.StringUtils;
import net.idj.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product =(Product) target;
		
		//wether file has been selected or not
		if(product.getFile()==null ||  product.getFile().getOriginalFilename().equals("")) {
			
			errors.rejectValue("file", null, "Please select an image faile to upload");
			return;
		}
		
		if(! (product.getFile().getContentType().equals("image/png") ||
			 product.getFile().getContentType().equals("image/jepg") ||
			 product.getFile().getContentType().equals("image/gif"))
		  )
		{
			errors.rejectValue("file", null, "Please use only image file for upload!");
			return;
			
		}
		
		
		
		
	}

}
