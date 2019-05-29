<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp"></jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<div class="container">
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center" style="color:red;font-size:18px">${errorMessage}</div>
	
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center">
		<strong><h1>Add new Company!!</h1></strong>
	</div>

	<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-1 col-lg-8 col-lg-offset-2 login_form">
		<form class="form-horizontal" action="${contextPath}/admin/add_company" method="POST">
		
			<div class="form-group">
	    		<label for="company_name" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Company Name</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<input class="form-control" type="text" name="company_name" size="30" required="required" />
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="location" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Company Location</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<input class="form-control" type="text" name="location" size="30" required="required" />
	    		</div>
	  		</div>
		
	  		<br>
	  		<div class="form-group">
	    		<div class="col-sm-12 col-xs-12 col-lg-12 col-md-12 text-center">
	      			<button type="submit" class="btn btn-primary">Submit</button>
	    		</div>
	  		</div>
		</form>
	</div>
	
	
</div>
<jsp:include page="footer.jsp"></jsp:include>