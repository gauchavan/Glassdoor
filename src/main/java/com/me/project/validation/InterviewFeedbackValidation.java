package com.me.project.validation;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.me.project.pojo.InterviewFeedback;

public class InterviewFeedbackValidation implements Validator {

	public boolean supports(Class<?> type) {
        return type.isAssignableFrom(InterviewFeedback.class);
    }
	
	
	public void validate(Object o, Errors errors){
		System.out.println("interview feedback validation page");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interviewQuestion", "empty-interviewQuestion", "Interview Question cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interviewProcess", "empty-interviewProcess", "Interview Process cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interviewDifficulty", "empty-interviewDifficulty", "Interview Difficulty cannot be blank");
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "getOffer", "empty-getOffer", "Get offer cannot be blank");
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


	@Override
	public boolean validate(Object subrootObj) throws JAXBException {
		// TODO Auto-generated method stub
		return false;
	}

}
