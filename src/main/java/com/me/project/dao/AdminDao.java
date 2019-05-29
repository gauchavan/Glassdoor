package com.me.project.dao;

import org.hibernate.HibernateException;

import com.me.project.pojo.Company;
import com.me.project.pojo.Internship;
import com.me.project.pojo.User;

public class AdminDao extends Dao { 
	
	public AdminDao() {
		
	}

	public Company addCompany(Company company) throws Exception {
		try {
			begin();
			System.out.println("inside Admin DAO");
			getSession().save(company);
			commit();
			return company;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating Company in AdminDao: " + e.getMessage());
		}
	}
	
	public Internship addInternship(Internship internship) throws Exception {
		try {
			begin();
			System.out.println("inside Admin DAO");
			getSession().save(internship);
			commit();
			return internship;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating Internship in AdminDao: " + e.getMessage());
		}
	}
}
