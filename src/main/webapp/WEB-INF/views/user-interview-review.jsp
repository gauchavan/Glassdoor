<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<jsp:include page="header.jsp"></jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<div class="container">
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center" style="color:red;font-size:18px">${errorMessage}</div>
	
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center">
		<strong><h1>Interview Process Review</h1></strong>
	</div>

	<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-1 col-lg-8 col-lg-offset-2 login_form" style="margin-top:50px">
		<form:form class="form-horizontal" action="${contextPath}/user/interview_review" method="POST"  modelAttribute="user_interview_review">
		
			<div class="form-group">
	    		<label for="company_name" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Company Name</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<select class="form-control" name="company_name">
	      				<option value="">Select the Company</option>
	      				<c:if test="${ not empty company}" >
	      					<c:forEach items="${company}" var="company">
					  			<option value="${company.company_name}">${company.company_name}</option>
					  		</c:forEach>		
					  	</c:if>		
					</select>
	    		</div>
	  		</div>
		
			<div class="form-group">
	    		<label for="interviewQuestion" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Interview Questions</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<form:input class="form-control" type="text" path="interviewQuestion" size="30" required="required" /><form:errors path="interviewQuestion" />
	    		</div>
	  		</div>
		
		
			<div class="form-group">
	    		<label for="interviewProcess" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Interview Process</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<form:input class="form-control" type="text" path="interviewProcess" size="30" required="required"  /><form:errors path="interviewProcess" />
	    		</div>
	  		</div>
		
	  		<div class="form-group">
	    		<label for="interviewDifficulty" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Difficulty Level</label>
	    			<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      				<form:input class="form-control" type="text" path="interviewDifficulty" size="30" required="required"  /><form:errors path="interviewDifficulty" />
	    			</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="getOffer" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Got Offer</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<select class="form-control" name="getOffer">
					  	<option value="yes">Yes</option>
					  	<option value="no">No</option>
					</select>
	    		</div>
	  		</div>
	  	
	  		<input type="hidden" value="${sessionScope.user.email_id}" name="user_email" />
	  		<br>
	  		<div class="form-group">
	    		<div class="col-sm-12 col-xs-12 col-lg-12 col-md-12 text-center">
	      			<button type="submit" class="btn btn-primary">Submit</button>
	    		</div>
	  		</div>
		</form:form>
	</div>
	
	
</div>
<jsp:include page="footer.jsp"></jsp:include>