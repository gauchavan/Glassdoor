package com.me.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.project.pojo.Company;
import com.me.project.pojo.Internship;
import com.me.project.pojo.InterviewFeedback;
import com.me.project.pojo.User;

public class HandlerDao extends Dao {
	
	public HandlerDao() {
		
	}
	
	public User getUser(String email){
		Criteria cr = getSession().createCriteria(User.class);
		cr.add(Restrictions.eq("email_id", email));
		User user = (User) cr.uniqueResult();
		return user;
	}
	
	public List<User> getUserList(String email){
		Criteria cr = getSession().createCriteria(User.class);
		cr.add(Restrictions.eq("email_id", email));
		List<User> user = cr.list();
		return user;
	}
	
	
	public Company getCompanyObject(String company_n){
		Criteria cr = getSession().createCriteria(Company.class);
		cr.add(Restrictions.eq("company_name", company_n));
		Company company = (Company) cr.uniqueResult();
		return company;
	}
	
	
	public List<Company> getCompanyObject_Search(String company_n){
		Criteria cr = getSession().createCriteria(Company.class);
		cr.add(Restrictions.like("company_name", "%"+company_n+"%"));
		List<Company> company = cr.list();
		return company;
	}
	
	public List<Company> getCompanyObjectList(String company_n){
		Criteria cr = getSession().createCriteria(Company.class);
		cr.add(Restrictions.eq("company_name", company_n));
		List<Company> company = cr.list();
		return company;
	}
	
	
	public List<Company> getCompanyObjectNoParameter() throws Exception{
		try {
			Query q = getSession().createQuery("from Company");
			List<Company> company = q.list();
			return company;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get company", e);
		}		
	}
	
	public List<Internship> getInternshipFromCompany(Company company) throws Exception{
		try {
			Query q = getSession().createQuery("from Internship where company_id = :companyId");
			q.setLong("companyId", company.getId());
			List<Internship> internship = q.list();
			return internship;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get Internship with specific company", e);
		}	
	}
	

	public Internship getInternship(Long internship_id){
		Criteria cr = getSession().createCriteria(Internship.class);
		cr.add(Restrictions.eq("id", internship_id));
		Internship internship = (Internship) cr.uniqueResult();
		return internship;
	}
	
	
	public List<Internship> getInternshipList() throws Exception{
		try {
			Query q = getSession().createQuery("from Internship");
			List<Internship> internship = q.list();
			return internship;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get internship", e);
		}		
	}
	
	public List<User> getUserByCompanyId(Long company_id) throws Exception{
		try {
			Criteria cr = getSession().createCriteria(User.class);
			cr.createAlias("company", "c");
			cr.add(Restrictions.eq("c.id",company_id));
			List<User> user = cr.list();
			return user;	
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get internship", e);
		}	
	}
	
	
	public List<InterviewFeedback> getInterviewFeedbackByCompanyId(Long company_id) throws Exception{
		try {
			Criteria cr = getSession().createCriteria(InterviewFeedback.class);
			cr.createAlias("company", "c");
			cr.add(Restrictions.eq("c.id",company_id));
			List<InterviewFeedback> interviewFeedback = cr.list();
			return interviewFeedback;	
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get Interview Feedback", e);
		}		
	}
	
	
	
	
	
	
	
}
