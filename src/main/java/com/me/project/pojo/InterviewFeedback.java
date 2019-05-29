package com.me.project.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.validation.BindingResult;

@Entity
@Table(name="interviewfeedback")
public class InterviewFeedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	private long id;
	
	@Column(name="interviewquestion")
	private String interviewQuestion;
	
	@Column(name="interviewprocess")
	private String interviewProcess;
	
	@Column(name="interviewdifficulty")
	private String interviewDifficulty;
	
	@Column(name="getoffer")
	private Boolean getOffer;

	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public InterviewFeedback() {
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getInterviewQuestion() {
		return interviewQuestion;
	}

	public void setInterviewQuestion(String interviewQuestion) {
		this.interviewQuestion = interviewQuestion;
	}

	
	public String getInterviewProcess() {
		return interviewProcess;
	}

	public void setInterviewProcess(String interviewProcess) {
		this.interviewProcess = interviewProcess;
	}

	
	public String getInterviewDifficulty() {
		return interviewDifficulty;
	}

	public void setInterviewDifficulty(String interviewDifficulty) {
		this.interviewDifficulty = interviewDifficulty;
	}

	
	public Boolean getGetOffer() {
		return getOffer;
	}

	public void setGetOffer(Boolean getOffer) {
		this.getOffer = getOffer;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
}
