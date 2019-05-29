package com.me.project.validation;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.me.project.pojo.User;

@SuppressWarnings("deprecation")
public class LoginValidation implements Validator {

	public boolean supports(Class<?> type) {
       return type.isAssignableFrom(User.class);
    }
	
	
	public void validate(Object o, Errors errors){
		System.out.println("login heyy");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email_id", "empty-email_id", "Email Address cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Password cannot be blank");
    }


	@Override
	public void setEventHandler(ValidationEventHandler handler) throws JAXBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ValidationEventHandler getEventHandler() throws JAXBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validate(Object subrootObj) throws JAXBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateRoot(Object rootObj) throws JAXBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setProperty(String name, Object value) throws PropertyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getProperty(String name) throws PropertyException {
		// TODO Auto-generated method stub
		return null;
	}

}
