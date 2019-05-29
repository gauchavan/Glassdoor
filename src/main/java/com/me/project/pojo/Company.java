package com.me.project.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private long id;
	
	@Column(name="companyname", unique=true, nullable=false)
	private String company_name;
	
	@Column(name="location")
	private String location;

	@JsonIgnore
	@ManyToMany(mappedBy="company")
	private Collection<User> userDetails = new ArrayList<User>();
	
	@JsonIgnore
	@OneToMany(mappedBy="company",cascade=CascadeType.PERSIST)
	private Collection<InterviewFeedback> feedback = new ArrayList<InterviewFeedback>();
	
	@JsonIgnore
	@OneToMany(mappedBy="company", cascade=CascadeType.PERSIST)
	private Collection<Internship> internship = new ArrayList<Internship>();
	
	public Company() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setInternship(Collection<Internship> internship) {
		this.internship = internship;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Collection<User> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Collection<User> userDetails) {
		this.userDetails = userDetails;
	}

	public Collection<InterviewFeedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(Collection<InterviewFeedback> feedback) {
		this.feedback = feedback;
	}

	public Collection<Internship> getInternship() {
		return internship;
	}
	
}
