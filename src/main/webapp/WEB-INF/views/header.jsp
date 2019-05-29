<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
	<!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Webtools Project</title>
  </head>

  <body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${contextPath}/">Webtools Project</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${contextPath}/">Home</a></li>
          <!-- <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>-->
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <c:choose>
				<c:when test="${not empty sessionScope.user}">
				   <li class="dropdown">
		              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user.displayName} <span class="caret"></span></a>
		              <ul class="dropdown-menu">
		              	<c:if test="${sessionScope.user.role eq 'admin'}">
		              		<li><a href="${contextPath}/admin/dashboard.htm">Dashboard</a></li>
		              	</c:if>
		              	<c:if test="${sessionScope.user.role eq 'student'}">
		              		<li><a href="${contextPath}/user/dashboard.htm">Dashboard</a></li>
		              	</c:if>
		       
		                <li><a href="${contextPath}/logout.htm">Logout</a></li>
		              </ul>
		         	</li>
				</c:when>
				<c:otherwise>
				  	<li><a href="${contextPath}/signup.htm">Sign Up</a></li>
            		<li><a href="${contextPath}/login.htm">Login</a></li>
				</c:otherwise>
			</c:choose> 
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
<br><br><br><br>