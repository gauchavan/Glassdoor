package com.me.project.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.project.pojo.Company;
import com.me.project.pojo.Internship;
import com.me.project.pojo.User;

public class UserDao extends Dao {
	
	public UserDao() {
		
	}

	public User register(User user) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			System.out.println("Exkkkk");
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	
	public User getUserWithEmailAndPassword(String EmailId, String password) throws Exception{
		try {
			
			System.out.println("email" + EmailId);
			begin();
			Query q = getSession().createQuery("from User where email_id = :emailId and password = :password");
			q.setString("emailId", EmailId);
			q.setString("password", password);	
			User user = (User) q.uniqueResult();
			System.out.println("inside dao" + user);
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + EmailId, e);
		}
		
		/*Criteria cr = getSession().createCriteria(User.class);
		cr.add(Restrictions.like("email_id", "%"+EmailId+"%"));
		cr.add(Restrictions.like("password", "%"+password+"%"));
		User user = (User) cr.uniqueResult();
		System.out.println("inside dao" + user);
		return user;*/
	}
	
	public Internship addInternshipReview(Internship internship) throws Exception {
		
		try {
			begin();
			System.out.println("inside User DAO Add Internship");
			getSession().persist(internship);
			commit();
			return internship;

		} catch (HibernateException e) {
			rollback();
			System.out.println("inside Interview feedback DAO exception");
			throw new Exception("Exception while creating getFeedback: " + e.getMessage());
		}
		
	}

}
