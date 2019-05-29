package com.me.project;

import java.util.List;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.me.project.dao.HandlerDao;
import com.me.project.dao.InterviewFeedbackDao;
import com.me.project.dao.UserDao;
import com.me.project.pojo.Company;
import com.me.project.pojo.Internship;

import com.me.project.pojo.InterviewFeedback;
import com.me.project.pojo.User;
import com.me.project.validation.InterviewFeedbackValidation;
import com.me.project.validation.UserValidation;

@Controller
public class UserController {

	@Autowired
	@Qualifier("validateInterviewFeedback")
	InterviewFeedbackValidation interviewFeedVal;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/user/dashboard.htm", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return new ModelAndView("redirect:/");
		} else {
			return new ModelAndView("user-dashboard");
		}
	}

	@RequestMapping(value = "/user/company_review", method = RequestMethod.GET)
	public ModelAndView showCompanyReview(HttpServletRequest request, HandlerDao handlerDao, ModelMap map, @ModelAttribute("error_message") String error_message) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return new ModelAndView("redirect:/");
		} else {

			List<Company> company_ = null;
			try {
				company_ = handlerDao.getCompanyObjectNoParameter();
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				map.addAttribute("error_message", "Oops, something went wrong!!");
				return new ModelAndView("user-company-review", "map", map);
			}
			
			map.addAttribute("company", company_);
			return new ModelAndView("user-company-review", "map", map);

		}
	}

	@RequestMapping(value = "/user/company_review", method = RequestMethod.POST)
	public ModelAndView handleCompanyReview(HttpServletRequest request, HandlerDao handlerDao, UserDao userdao,
			ModelMap map) {
		
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return new ModelAndView("redirect:/");
		} else {
			
			String emailID = request.getParameter("user_email");
			System.out.println("Email id First: "+ emailID);
			
			String companyName = "";
			if (request.getParameter("company_name") != null) {
				companyName = request.getParameter("company_name");
			} else {
				map.addAttribute("errorMessage", "Company Name is missing");
				map.addAttribute("error_message", "Oops, something went wrong!!");
				return new ModelAndView("redirect:/user/company_review", "map", map);
			}

			String internshiPosition = "";
			if (request.getParameter("internship_position") != null) {
				internshiPosition = request.getParameter("internship_position");
			} else {
				map.addAttribute("errorMessage", "Internship position is missing");
				map.addAttribute("error_message", "Oops, something went wrong!!");
				return new ModelAndView("redirect:/user/company_review", "map", map);
			}

			String intershipSource = "";
			if (request.getParameter("intershipSource") != null) {
				intershipSource = request.getParameter("intershipSource");
			} else {
				map.addAttribute("errorMessage", "Intership Source is missing");
				map.addAttribute("error_message", "Oops, something went wrong!!");
				return new ModelAndView("redirect:/user/company_review", "map", map);
			}

			
			System.out.println("email id:" +emailID);
			
			
			try {
				List<User> user_ = handlerDao.getUserList(emailID);
				User user_1 = handlerDao.getUser(emailID);
				

				Company company_ = null;
				try {
					company_ = handlerDao.getCompanyObject(companyName);
				} catch (Exception e) {
					e.printStackTrace();
					map.addAttribute("errorMessage", e.getMessage());
					map.addAttribute("error_message", "Oops, something went wrong!!");
					return new ModelAndView("redirect:/user/company_review", "map", map);
				}
				
				List<Company> company_1 = null;
				try {
					company_1 = handlerDao.getCompanyObjectList(companyName);
				} catch (Exception e) {
					e.printStackTrace();
					map.addAttribute("errorMessage", e.getMessage());
					map.addAttribute("error_message", "Oops, something went wrong!!");
					return new ModelAndView("redirect:/user/company_review", "map", map);
				}
				
				
				Internship internship_ = null;
				try {
					Long internshipId = Long.parseLong(internshiPosition);
					internship_ = handlerDao.getInternship(internshipId);
				} catch (Exception e) {
					e.printStackTrace();
					map.addAttribute("errorMessage", e.getMessage());
					return new ModelAndView("redirect:/user/company_review", "map", map);
				}
				Boolean flag = false;
				
				if(user_1.getInternship() != null) {
					Long user_internship_id = user_1.getInternship().getId();
						flag = true;
				}
				
			
				internship_.setCompany(company_);
				internship_.setIntershipSource(intershipSource);
				internship_.setUser(user_);
				user_1.setInternship(internship_);
				user_1.setCompany(company_1);
				
				if(flag == true) {
					System.out.println("Internship User"+user_1.getInternship().getId());
					map.addAttribute("errorMessage", "You can do only one Internship!!");
					map.addAttribute("error_message", "You can do only one Internship!!");
					return new ModelAndView("redirect:/user/company_review", "map", map);
				}else {	
					Internship internship = userdao.addInternshipReview(internship_);	
				}
				
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("wsftet");
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				map.addAttribute("error_message", "Oops, something went wrong!!");
				return new ModelAndView("redirect:/user/company_review", "map", map);

			}
			map.addAttribute("errorMessage", "Successfully added Co-op/Internship review!");
			map.addAttribute("success_message", "Successfully added Co-op/Internship review!");
			return new ModelAndView("success", map);
			
		}

	}

	@RequestMapping(value = "/user/interview_review", method = RequestMethod.GET)
	public ModelAndView showInterviewReview(HttpServletRequest request, HandlerDao handlerDao, ModelMap map) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return new ModelAndView("redirect:/");
		} else {

			List<Company> company_ = null;
			try {
				company_ = handlerDao.getCompanyObjectNoParameter();
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return new ModelAndView("user-interview-review", "map", map);
			}

			map.addAttribute("company", company_);
			request.setAttribute("user_interview_review", new InterviewFeedback());
			return new ModelAndView("user-interview-review", "map", map);
		}
	}

	@RequestMapping(value = "/user/interview_review", method = RequestMethod.POST)
	public ModelAndView handleInterviewReview(HttpServletRequest request, InterviewFeedbackDao interviewFeedDao,
			UserDao userdao, HandlerDao handlerDao, ModelMap map,
			@ModelAttribute("user_interview_review") InterviewFeedback interviewfeed, BindingResult result) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return new ModelAndView("redirect:/");
		} else {

			interviewFeedVal.validate(interviewfeed, result);
			if (result.hasErrors()) {
				return new ModelAndView("user-interview-review", "interviewfeed", interviewfeed);
			}

			String companyName = "";
			if (request.getParameter("company_name") != null) {
				companyName = request.getParameter("company_name");
			} else {
				map.addAttribute("errorMessage", "Company Name is missing");
				return new ModelAndView("user-interview-review", "map", map);
			}

			try {

				String emailID = request.getParameter("user_email");
				User user_ = handlerDao.getUser(emailID);

				Company company_ = null;
				try {
					company_ = handlerDao.getCompanyObject(companyName);
				} catch (Exception e) {
					e.printStackTrace();
					map.addAttribute("errorMessage", e.getMessage());
					return new ModelAndView("user-interview-review", "map", map);
				}

				if (request.getParameter("getOffer").equals("yes")) {
					interviewfeed.setGetOffer(true);
				} else {
					interviewfeed.setGetOffer(false);
				}

				InterviewFeedback interviewfeed_ = interviewFeedDao.getFeedback(interviewfeed, user_, company_);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("wsftet");
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return new ModelAndView("user-interview-review", "map", map);

			}

			map.addAttribute("errorMessage", "Successfully added Interview review!");
			return new ModelAndView("redirect:/user/interview_review", "map", map);
		}
	}

	@RequestMapping(value = "/getInternshipAjax", method = RequestMethod.POST)
	@ResponseBody
	public List<Internship> handleInternshipAjax(HttpServletRequest request, HandlerDao handlerDao, ModelMap map) {

		System.out.println("Inside controller");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return null;
		} else {

			String companyName = "";
			if (request.getParameter("company_name") != null) {
				companyName = request.getParameter("company_name");
			} else {
				return null;
			}

			Company company_ = null;
			try {
				company_ = handlerDao.getCompanyObject(companyName);
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return null;
			}

			List<Internship> internship_ = null;
			try {
				internship_ = handlerDao.getInternshipFromCompany(company_);
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return null;
			}
			String Result = "";
			for (int i = 0; i < internship_.size(); i++) {
				Result += internship_.get(i).getInternshipPosition();
			}

			map.put("internship", internship_);
			return internship_;

			// return Result;

		}
	}
	
	@RequestMapping(value = "/searchCompanyAjax", method = RequestMethod.POST)
	@ResponseBody
	public List<Company> handleSearchCompanyAjax(HttpServletRequest request, HandlerDao handlerDao, ModelMap map) {


		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return null;
		} else {

			String companyName = "";
			if (request.getParameter("search_text") != null) {
				companyName = request.getParameter("search_text");
			} else {
				return null;
			}

			List<Company> company_ = null;
			try {
				company_ = handlerDao.getCompanyObject_Search(companyName);
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return null;
			}

			return company_;

			// return Result;

		}
	}
	
	@RequestMapping(value = "/searchCompanyUserAjax", method = RequestMethod.POST)
	@ResponseBody
	public List<User> handleSearchCompanyUserAjax(HttpServletRequest request, HandlerDao handlerDao, ModelMap map) {


		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return null;
		} else {

			String companyId = "";
			if (request.getParameter("companyId") != null) {
				companyId = request.getParameter("companyId");
			} else {
				return null;
			}

			List<User> user_ = null;
			try {
				Long company_id = Long.parseLong(companyId);
				user_ = handlerDao.getUserByCompanyId(company_id);
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return null;
			}

			return user_;

			// return Result;

		}
	}

	
	@RequestMapping(value = "/searchInterviewQuestionAjax", method = RequestMethod.POST)
	@ResponseBody
	public List<InterviewFeedback> handleSearchInterviewFeedbackAjax(HttpServletRequest request, HandlerDao handlerDao, ModelMap map) {


		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("admin")) {
			return null;
		} else {

			String companyId = "";
			if (request.getParameter("specific_company_id") != null) {
				companyId = request.getParameter("specific_company_id");
			} else {
				return null;
			}

			List<InterviewFeedback> interviewFeedback = null;
			try {
				Long company_id = Long.parseLong(companyId);
				interviewFeedback = handlerDao.getInterviewFeedbackByCompanyId(company_id);
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return null;
			}

			return interviewFeedback;

			// return Result;
		}
	}

	
}
