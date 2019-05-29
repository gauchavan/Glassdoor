package com.me.project.dao;

import org.hibernate.HibernateException;

import com.me.project.pojo.Company;
import com.me.project.pojo.InterviewFeedback;
import com.me.project.pojo.User;


public class InterviewFeedbackDao extends Dao {
	
	public InterviewFeedbackDao() {
		
	}
	
	public InterviewFeedback getFeedback(InterviewFeedback interviewFeed, User user, Company company) throws Exception {
		try {
			begin();
			System.out.println("inside Interview feedback DAO");
			interviewFeed.setUser(user);
			interviewFeed.setCompany(company);
			getSession().persist(interviewFeed);
			commit();
			return interviewFeed;

		} catch (HibernateException e) {
			rollback();
			System.out.println("inside Interview feedback DAO exception");
			throw new Exception("Exception while creating getFeedback: " + e.getMessage());
		}
	}

}
