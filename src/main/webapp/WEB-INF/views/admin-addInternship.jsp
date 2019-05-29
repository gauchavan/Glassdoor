<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp"></jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<div class="container">
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center" style="color:red;font-size:18px">${errorMessage}</div>

	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center">
		<strong><h1>Add new Internship!!</h1></strong>
	</div>
	<br><br><br>
	<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-1 col-lg-8 col-lg-offset-2">
		<form class="form-horizontal" action="${contextPath}/admin/add_internship" method="POST">
		
			<div class="form-group">
	    		<label for="internshipPosition" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Internship Position</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<input class="form-control" type="text" name="internshipPosition" size="30" required="required" />
	    		</div>
	  		</div>
	  		
	  		
	  		<div class="form-group">
	    		<label for="internshipDuration" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Internship Duration</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<input class="form-control" type="text" name="internshipDuration" size="30" required="required" />
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="intershipStartDate" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Internship Start Date</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<input class="form-control" type="date" name="intershipStartDate" size="30" required="required" />
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="intershipEndDate" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Internship End Date</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<input class="form-control" type="date" name="intershipEndDate" size="30" required="required" />
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="company_name" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Company Name</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<select class="form-control" name="company_name">
	      				<c:if test="${ not empty company}" >
	      					<c:forEach items="${company}" var="company">
					  			<option value="${company.company_name}">${company.company_name}</option>
					  		</c:forEach>		
					  	</c:if>		
					</select>
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