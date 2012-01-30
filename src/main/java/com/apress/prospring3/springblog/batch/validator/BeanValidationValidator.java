package com.apress.prospring3.springblog.batch.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Batch validator to perform validation using Bean Validation API.
 * 
 * @author Clarence
 *
 */
public class BeanValidationValidator<Entry> implements Validator<Entry> {

	@Autowired
	private javax.validation.Validator validator;
	
	public void validate(Entry value) throws ValidationException {
		
		Set<ConstraintViolation<Entry>> violations = new HashSet<ConstraintViolation<Entry>>();
		violations = validator.validate(value);
		if(!violations.isEmpty()) { 
			throw new ValidationException("Validation failed for " + value + ": " + 
					violationsToString(violations)); 
		} 
	}
	
	private String violationsToString(Set<ConstraintViolation<Entry>> violations) {
		
		StringBuilder violationMessage = new StringBuilder();
		
		for (ConstraintViolation<Entry> violation: violations) {
			violationMessage.append(violation.getMessage() + "|");
		}
		
		return violationMessage.toString();
	}	
	
}
