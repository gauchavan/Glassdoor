<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
  
<jsp:include page="header.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container">
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center">
		<h1>Thank You!!!</h1>
		
		<h3>Thank you for Registering and verifying your Email address.</h3>
		
		<h3>Please Login - <a href="${contextPath}/login.htm">Login Here</a></h3>
	</div>
</div>