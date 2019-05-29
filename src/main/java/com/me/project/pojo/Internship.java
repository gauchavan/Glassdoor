package com.me.project.pojo;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;


@Entity
@Table(name="internship")
public class Internship {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private long id;
	
	@Column(name="startDate")
	private Date intershipStartDate;
	
	@Column(name="endDate")
	private Date intershipEndDate;
	
	@Column(name="position")
	private String internshipPosition;
	
	@Column(name="duration")
	private String internshipDuration;

	@Column(name="source")
	private String intershipSource;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.PERSIST)
	private Collection<User> user = new  ArrayList<User>();
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;

	public Internship() {
		
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public Date getIntershipStartDate() {
		return intershipStartDate;
	}
	
	public void setIntershipStartDate(Date intershipStartDate) {
		this.intershipStartDate = intershipStartDate;
	}
	
	
	public Date getIntershipEndDate() {
		return intershipEndDate;
	}
	public void setIntershipEndDate(Date intershipEndDate) {
		this.intershipEndDate = intershipEndDate;
	}
	
	
	public String getInternshipPosition() {
		return internshipPosition;
	}
	public void setInternshipPosition(String internshipPosition) {
		this.internshipPosition = internshipPosition;
	}
	
	
	public String getIntershipSource() {
		return intershipSource;
	}

	public void setIntershipSource(String intershipSource) {
		this.intershipSource = intershipSource;
	}
	
	
	public String getInternshipDuration() {
		return internshipDuration;
	}

	public void setInternshipDuration(String internshipDuration) {
		this.internshipDuration = internshipDuration;
	}

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
