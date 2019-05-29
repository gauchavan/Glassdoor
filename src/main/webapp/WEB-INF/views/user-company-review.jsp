<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp"></jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<div class="container">
	<p class="bg-success"> ${success_message} </p>
	<p class="bg-danger"> ${error_message} </p>
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center" style="color:red;font-size:18px">${errorMessage}</div>
		Welcome ${sessionScope.user.displayName}
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center">
		<strong><h1>Co-op/Internship Review</h1></strong>
	</div>

	<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-1 col-lg-8 col-lg-offset-2 login_form" style="margin-top:50px">
		<form class="form-horizontal" action="${contextPath}/user/company_review" method="POST">
		
			<div class="form-group">
	    		<label for="company_name" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Company Name</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<select class="form-control" name="company_name" id="company_name">
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
	    		<label for="internship_position" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Select Internship Position</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<select class="form-control" name="internship_position" id="internship_position">
	      				
					</select>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="intershipSource" class="col-sm-2 col-lg-4 col-md-4 col-xs-2 control-label">Internship/Co-op Source</label>
	    		<div class="col-sm-10 col-lg-8 col-md-8 col-xs-10">
	      			<input class="form-control" type="text" name="intershipSource" size="30" required="required" />
	    		</div>
	  		</div>
	 
			
			<input type="hidden" value="${sessionScope.user.email_id}" name="user_email" />
			<input type="hidden" value="${contextPath}/getInternshipAjax" name="url_internshipAjax" id="url_internshipAjax" />
	  		<br>
	  		<div class="form-group">
	    		<div class="col-sm-12 col-xs-12 col-lg-12 col-md-12 text-center">
	      			<button type="submit" class="btn btn-primary">Submit</button>
	    		</div>
	  		</div>
		</form>
	</div>
	
	
</div>
<script type="text/javascript">
$(document).ready(function(){
	var url_ = $("#url_internshipAjax").val();

	$('#company_name').on('change', function() {
		var company_selected = $("#company_name").val();
    	$.post( url_, {company_name:company_selected })
	  		.done(function(result) {
	  			$('#internship_position').html("");
	  			console.log(result);
	  			$('#internship_position').append('<option value="Select Internship" >Select Internship</option>');
	  			for(var prop in result) {
	  			    var item = result[prop];
	  			  	$('#internship_position').append('<option value="' + result[prop].id + '">' + result[prop].internshipPosition + '</option>');
	  			}
		  		
	  		});
	});
});	


</script>
<jsp:include page="footer.jsp"></jsp:include>