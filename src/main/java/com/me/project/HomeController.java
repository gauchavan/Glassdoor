package com.me.project;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.botdetect.web.servlet.Captcha;
import com.me.project.dao.HandlerDao;
import com.me.project.dao.UserDao;
import com.me.project.pojo.User;
import com.me.project.validation.LoginValidation;
import com.me.project.validation.UserValidation;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	@Qualifier("validateUser")
	UserValidation userVal;
	
	@Autowired
	@Qualifier("validateLogin")
	LoginValidation loginVal;
	
	
	public HomeController() {
		/*User user = new User();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setDisplayName("Admin");
		user.setPassword("admin");
		user.setRole("admin");
		user.setStatus(1);
		user.setEmail_id("admin@gmail.com");
		UserDao userdao = new UserDao();
		try {
			User user_ = userdao.register(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	

	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	
	@RequestMapping(value = "/signup.htm", method = RequestMethod.GET)
	public ModelAndView showSignUpForm(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			request.setAttribute("signup_user",user);
			return new ModelAndView("signup");
		}else {
			User u = (User) session.getAttribute("user");
			if(u.getRole().equals("admin")) {
				return new ModelAndView("redirect:/admin/dashboard.htm");
			}else {
				return new ModelAndView("redirect:/user/dashboard.htm");
			}
			
		}
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public ModelAndView showLoginForm(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			request.setAttribute("login_user",user);
			return new ModelAndView("login");
		}else {
			User u = (User) session.getAttribute("user");
			if(u.getRole().equals("admin")) {
				return new ModelAndView("redirect:/admin/dashboard.htm");
			}else {
				return new ModelAndView("redirect:/user/dashboard.htm");
			}
		}
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView handleLoginForm(HttpServletRequest request, HttpServletResponse response, UserDao userdao, ModelMap map, @ModelAttribute("login_user") User user, BindingResult result) {
		System.out.println("results"+result);
		loginVal.validate(user, result);
		if(result.hasErrors())
		{
			return new ModelAndView("login","user", user);
		}
		
		HttpSession session = request.getSession();
		
		String rememberMe = request.getParameter("remember_me");
		
		try {
			User u = userdao.getUserWithEmailAndPassword(user.getEmail_id(), user.getPassword());
			
			if (u != null && u.getStatus() == 1) {
			
			
				session.setAttribute("user", u);
				
				if(u.getRole().equals("admin")) {
					
					if (rememberMe == null) {
						System.out.println("remember me null");
						map.addAttribute("user", u);
						return new ModelAndView("redirect:/admin/dashboard.htm", map);
						
					} else {
					   System.out.println("remember me not null");
	                   Cookie cookie = new Cookie("user_email", user.getEmail_id());
	                   response.addCookie(cookie);
	                   map.addAttribute("user", u);
					   return new ModelAndView("redirect:/admin/dashboard.htm", map);
					}
					
				}else {
					
					if (rememberMe == null) {
						System.out.println("remember me null");
						map.addAttribute("user", u);
						return new ModelAndView("redirect:/user/dashboard.htm", map);
						
					} else {
					   System.out.println("remember me not null");
	                   Cookie cookie = new Cookie("user_email", user.getEmail_id());
	                   response.addCookie(cookie);
	                   map.addAttribute("user", u);
					   return new ModelAndView("redirect:/user/dashboard.htm", map);
					}
					
				}
				
			} else if (u != null && u.getStatus() == 0) {
				
				map.addAttribute("errorMessage", "Please activate your account to login!");
				return new ModelAndView("error", map);
			
			} else {
				
				map.addAttribute("errorMessage", "Invalid username/password!");
				return new ModelAndView("error", map);
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/");
	}
	
	
	@RequestMapping(value = "/signup.htm", method = RequestMethod.POST)
	public ModelAndView handleSignUpForm(HttpServletRequest request, UserDao userdao, ModelMap map, @ModelAttribute("signup_user") User user, BindingResult result){
		
		
		userVal.validate(user, result);
		if(result.hasErrors())
		{
			return new ModelAndView("signup","user", user);
		}
		
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = "";
		
		if(request.getParameter("captchaCode") == null) {
			map.addAttribute("errorMessage", "Captcha cannot be empty!");
			return new ModelAndView("signup", "map",map);
		}else {
			captchaCode = request.getParameter("captchaCode");
		}
		
		HttpSession session = request.getSession();
		System.out.println(captchaCode);
	//	if (captcha.validate(captchaCode)) {
			System.out.println("Inside captcha");
			try {
				user.setStatus(0);
				user.setRole("student");
				User user_ = userdao.register(user);
				Random rand = new Random();
				int randomNum1 = rand.nextInt(5000000);
				int randomNum2 = rand.nextInt(5000000);
				try {
					String str = "http://localhost:8080/project/emailvalidation.htm?email=" + user.getEmail_id() + "&key1="
							+ randomNum1 + "&key2=" + randomNum2;
					session.setAttribute("key1", randomNum1);
					session.setAttribute("key2", randomNum2);
					sendEmail(user.getEmail_id(),
							"Click on this link to activate your account : "+ str);
				} catch (Exception e) {
					System.out.println("Email cannot be sent");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("wsftet");
				e.printStackTrace();
				map.addAttribute("errorMessage", e.getMessage());
				return new ModelAndView("signup", "map",map);
				
			}
		/*} else {
			map.addAttribute("errorMessage", "Invalid Captcha!");
			return new ModelAndView("signup", "map",map);
		}*/

		return new ModelAndView("redirect:/login.htm"); 
	}
	
	@RequestMapping(value = "/emailvalidation.htm", method = RequestMethod.GET)
	public String handleEmailValidation(HttpServletRequest request, UserDao userdao, HandlerDao handlerDao, String emailId, String Key1, String Key2, ModelMap map) {
		
		String emailID = request.getParameter("email");
		String key1 = request.getParameter("key1");
		String key2 = request.getParameter("key2");
		
		User user_ = handlerDao.getUser(emailID);
		System.out.println("hello" + user_.getStatus());
		user_.setStatus(1);
		user_.setRole("student");
		try {
			User u = userdao.register(user_);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		map.addAttribute("emailId",emailId);
		map.addAttribute("Key1",key1);
		map.addAttribute("Key2",Key2);
		
		return "emailvalidate";
	}
	
	
	public void sendEmail(String useremail, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu"); // This user email does not
													// exist
			email.setSubject("Password Reminder");
			email.setMsg(message); // Retrieve email from the DAO and send this
			email.addTo(useremail);
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
	}
	

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String handleLogout(HttpServletRequest request, HttpServletResponse response) { 
		HttpSession session = request.getSession();
		session.invalidate();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        return "redirect:/";
                    }
                }
            }
        }
        
        return "redirect:/";
	}
	
}	
	
