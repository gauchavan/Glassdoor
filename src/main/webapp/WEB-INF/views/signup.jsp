<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha" %>

<jsp:include page="header.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<style>
	.login_form{
		padding: 30px;
	    background-color: #e5e5e5;
	    border-radius: 5px;
	    margin-top: 30px;
	}
</style>

<div class="container" style="margin-bottom:50px">
	
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center" style="color:red;font-size:18px">${errorMessage}</div>
	
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center">
		<strong><h1>Sign Up</h1></strong>
	</div>

	<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-1 col-lg-8 col-lg-offset-2 login_form">
		<form:form class="form-horizontal" action="${contextPath}/signup.htm" method="POST" commandName="user" modelAttribute="signup_user">
		
			<div class="form-group">
	    		<label for="fname" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">First Name</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<form:input class="form-control" type="text" path="firstName" size="30" required="required" /><form:errors path="firstName" />
	    		</div>
	  		</div>
		
			<div class="form-group">
	    		<label for="lname" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Last Name</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<form:input class="form-control" type="text" path="lastName" size="30" required="required" /><form:errors path="lastName" />
	    		</div>
	  		</div>
		
		
			<div class="form-group">
	    		<label for="username" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Display Name</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<form:input class="form-control" type="text" path="displayName" size="30" required="required"  /><form:errors path="displayName" />
	    		</div>
	  		</div>
		
	  		<div class="form-group">
	    		<label for="emailId" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Email Id</label>
	    			<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      				<form:input class="form-control" type="email" path="email_id" size="30" required="required"  /><form:errors path="email_id" />
	    			</div>
	  		</div>
	  		
	  		
	  		
	  		<div class="form-group">
	    		<label for="password" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Password</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<form:input class="form-control" type="password" path="password" size="30" required="required"  /><form:errors path="password" />
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="captchaCode" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Retype the characters from the picture:</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<%
						// Adding BotDetect Captcha to the page
						Captcha captcha = Captcha.load(request, "CaptchaObject");
						captcha.setUserInputID("captchaCode");
						String captchaHtml = captcha.getHtml();
						out.write(captchaHtml);
					%>
					<br>
					<input id="captchaCode" class="form-control" type="text" name="captchaCode" required="required"/>
	    		</div>
	  		</div>
	  		<br>
	  		<div class="form-group">
	    		<div class="col-sm-12 col-xs-12 col-lg-12 col-md-12 text-center">
	      			<button type="submit" class="btn btn-primary">Sign Up</button>
	    		</div>
	  		</div>
		</form:form>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
