package com.me.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.project.dao.AdminDao;
import com.me.project.dao.HandlerDao;
import com.me.project.pojo.Company;
import com.me.project.pojo.Internship;
import com.me.project.pojo.User;


@Controller
public class AdminController {
	
	@RequestMapping(value = "/admin/dashboard.htm", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("student")) {
			return new ModelAndView("redirect:/");
		} else {
			return new ModelAndView("admin-dashboard");
		}
	}
	
	
	@RequestMapping(value = "/admin/add_company", method = RequestMethod.GET)
	public ModelAndView showAddCompany(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("student")) {
			return new ModelAndView("redirect:/");
		}else {
			return new ModelAndView("admin-addCompany");
		}
	}
	
	
	@RequestMapping(value = "/admin/add_company", method = RequestMethod.POST)
	public ModelAndView handleAddCompany(HttpServletRequest request, Company company,HandlerDao handlerDao,  AdminDao adminDao, ModelMap map) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("student")) {
			return new ModelAndView("redirect:/");
		}else {
			
			String companyName = "";
			if(request.getParameter("company_name") != null) {
				companyName = request.getParameter("company_name");
			}else {
				map.addAttribute("errorMessage", "Company Name is missing");
				return new ModelAndView("admin-addCompany", "map",map);
			}

			String companyLocation = "";
			if(request.getParameter("location") != null) {
				companyLocation = request.getParameter("location");
			}else {
				map.addAttribute("errorMessage", "Company Location is missing");
				return new ModelAndView("admin-addCompany", "map",map);
			}
			
			Company company_ = new Company();
			company_.setCompany_name(companyName);
			company_.setLocation(companyLocation);
			
			try {
				Company company_h = adminDao.addCompany(company_);
				System.out.println("company h"+company_h);
			}catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return new ModelAndView("admin-addCompany", "map",map);
			}	
			
			map.addAttribute("errorMessage", "Successfully added Company to db");
			return new ModelAndView("admin-addCompany");
		}
	}
	
	@RequestMapping(value = "/admin/add_internship", method = RequestMethod.GET)
	public ModelAndView showAddInternship(HttpServletRequest request, Company company, HandlerDao handlerDao, ModelMap map ) {

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("student")) {
			return new ModelAndView("redirect:/");
		}else {
			
			List<Company> company_ = null;
			try {
				company_ = handlerDao.getCompanyObjectNoParameter();
			}catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return new ModelAndView("admin-addInternship", "map",map);
			}
			
			map.addAttribute("company",company_);
			return new ModelAndView("admin-addInternship", "map",map);
		}
	}
	
	
	@RequestMapping(value = "/admin/add_internship", method = RequestMethod.POST)
	public ModelAndView handleAddInternship(HttpServletRequest request, Company company,AdminDao adminDao, HandlerDao handlerDao, ModelMap map ) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("student")) {
			return new ModelAndView("redirect:/");
		}else {

			try{
				
				String companyName = "";
				String internshipPosition = "";
				String internshipDuration = "";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				Date intershipStartDate = new Date();
				Date intershipEndDate;
				if(request.getParameter("company_name") != null && request.getParameter("internshipPosition") != null && request.getParameter("internshipDuration") != null && request.getParameter("intershipStartDate") != null && request.getParameter("intershipEndDate") != null) {
					companyName = request.getParameter("company_name");
					internshipPosition = request.getParameter("internshipPosition");
					internshipDuration = request.getParameter("internshipDuration");
					intershipStartDate = sdf.parse(request.getParameter("intershipStartDate"));
					intershipEndDate =  sdf.parse(request.getParameter("intershipEndDate"));
					
				}else {
					map.addAttribute("errorMessage", "Field is missing");
					return new ModelAndView("redirect:/admin/add_internship");
				}	
				
				Company company_ = null;
				try {
					company_ = handlerDao.getCompanyObject(companyName);
				}catch (Exception e) {
					e.printStackTrace();
					map.addAttribute("errorMessage", e.getMessage());
					return new ModelAndView("redirect:/admin/add_internship");
				}
				
				Internship internship = new Internship();
				internship.setInternshipPosition(internshipPosition);
				internship.setInternshipDuration(internshipDuration);
				internship.setIntershipStartDate(intershipStartDate);
				internship.setIntershipEndDate(intershipEndDate);
				internship.setCompany(company_);
				
				Internship internship_ = adminDao.addInternship(internship);
				
			}catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return new ModelAndView("redirect:/admin/add_internship");
			}
			
			map.addAttribute("errorMessage", "Successfully added Internship to db");
			return new ModelAndView("redirect:/admin/add_internship","map",map);
		}
		
		
	}
	
	
	@RequestMapping(value = "/admin/list_company", method = RequestMethod.GET)
	public ModelAndView handleListCompany(HttpServletRequest request, ModelMap map, HandlerDao handleDao ) {
		
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null || u.getRole().equals("student")) {
			return new ModelAndView("redirect:/");
		}else {
		
			try {
				List<Internship> internshipObject = handleDao.getInternshipList();
				map.addAttribute("internship", internshipObject);
				return new ModelAndView("admin-listCompany", "map",map);
			}catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return new ModelAndView("admin-listCompany", "map",map);
			}
		}	
	}

}
