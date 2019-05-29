<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp"></jsp:include>

<style>
.login_form{
	padding: 30px;
    background-color: #e5e5e5;
    border-radius: 5px;
    margin-top: 30px;}
</style>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container" style="margin-botton:50px">
	<div class="col-md-4 col-md-offset-4 col-xs-12 col-sm-12 col-lg-4 col-lg-offset-4 login_form">
		<form:form class="form-horizontal" action="${contextPath}/login.htm" method="POST"  modelAttribute="login_user">
	  		<div class="form-group">
	    		<label for="emailId" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Email Id</label>
	    			<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      				<form:input class="form-control" type="email" path="email_id" size="30" required="required" /><form:errors path ="email_id" />
	    			</div>
	  		</div>
	  		<div class="form-group">
	    		<label for="password" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Password</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<form:input class="form-control" type="password" path="password" size="30" required="required" /><form:errors path="password" />
	    		</div>
	  		</div>
	  		<div class="form-group">
	    		<div class="col-sm-offset-1 col-sm-10" style="padding: 0px 7px;">
	     	 		<div class="checkbox">
	        			<label>
	          				<input type="checkbox" value="remember" name="remember_me"> Remember me
	        			</label>
	      			</div>
	    		</div>
	  		</div>
	  		<div class="form-group">
	    		<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 text-center">
	      			<button type="submit" class="btn btn-primary">Login</button>
	    		</div>
	  		</div>
		</form:form>
		
		<div class="col-sm-12" style="padding: 0px 22px;">
			<a href="${contextPath}/forgotpassword.htm">Forgot password?</a>&nbsp;&nbsp;&nbsp;
		</div>
	</div>
	
</div>
		
	
	
	
	
 <jsp:include page="footer.jsp"></jsp:include>
 
 
 
 



