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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="user") 
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private long id;
	
	@Column(name="email",unique=true,nullable=false)
	private String email_id;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone")
	private long phoneNumber;
	
	@Column(name="username")
	private String displayName;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="status")
	private int status;
	
	@JsonIgnore
	@ManyToMany
	private Collection<Company> company = new  ArrayList<Company>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade=CascadeType.PERSIST)
	private Collection<InterviewFeedback> feedback = new  ArrayList<InterviewFeedback>();
	
	@JsonIgnore
	@OneToOne
	private Internship internship ;
	
	@Column(name="role")
	private String role;
	
	public User() {
		 
	}	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Collection<Company> getCompany() {
		return company;
	}

	public void setCompany(Collection<Company> company) {
		this.company = company;
	}


	public Collection<InterviewFeedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(Collection<InterviewFeedback> feedback) {
		this.feedback = feedback;
	}

	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
}
