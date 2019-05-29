package com.me.project.validation;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.me.project.pojo.User;

@SuppressWarnings("deprecation")
public class UserValidation implements Validator {
	
	public boolean supports(Class<?> type) {
        return type.isAssignableFrom(User.class);
    }
	
	
	public void validate(Object o, Errors errors){
		System.out.println("heyyyyyyyy");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email_id", "empty-email_id", "Email Address cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Password cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayName", "empty-displayName", "Display name/Username cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty-firstName", "First Name cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty-lastName", "Last Name cannot be blank");
	}

	@Override
	public ValidationEventHandler getEventHandler() throws JAXBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProperty(String name) throws PropertyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEventHandler(ValidationEventHandler handler) throws JAXBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String name, Object value) throws PropertyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateRoot(Object rootObj) throws JAXBException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean validate(Object subrootObj) throws JAXBException {
		// TODO Auto-generated method stub
		return false;
	}
}
