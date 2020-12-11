package edu.miu.cs.auctionproject.validator;


import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ProductIdValidator implements ConstraintValidator<CategoryName, String> {
	
	@Autowired
	private CategoryService categoryService;

	public boolean isValid(String name, ConstraintValidatorContext context) {
		Category category;
		try {
			category = categoryService.findAllByName(name.toUpperCase());
			
		} catch (Exception e) {
			return true;
		}
		if(category!= null ) {
			return false;
		}
		return true;
	}
}
