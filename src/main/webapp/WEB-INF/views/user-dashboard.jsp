<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp"></jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="container" style="padding:0px">
	
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center" style="color:red;font-size:18px;padding: 10px;">${errorMessage}</div>
	<p class="bg-success"> ${success_message} </p>
	<p class="bg-danger"> ${error_message} </p>
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-left" style="padding:0px">
	
		<div class="col-md-6 col-xs-12 col-sm-12 col-lg-6 text-left" style="font-size:30px;font-weight:600">
			Welcome ${sessionScope.user.displayName}
		</div>
		
		<div class="col-md-6 col-xs-12 col-sm-12 col-lg-6 text-right">
			<a href="${contextPath}/user/company_review" class="btn btn-primary">Write Company Review</a> &nbsp;&nbsp;&nbsp;
			<a href="${contextPath}/user/interview_review" class="btn btn-primary">Write Interview Review</a>
		</div>
	
		<div class="col-md-12 col-sm-12 col-xs-12 col-lg-12">
			<div class="col-md-12 col-sm-12 col-xs-12 col-lg-12" style="background-color:#e5e5e5;border:1px solid #ddd;padding:20px;margin-top:50px;border-radius:5px">
				<div class="col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xs-12 col-sm-12">
					 <div class="input-group">
					     <input type="text" class="form-control" id="search_field" placeholder="Search for Company Name">
					     <span class="input-group-btn">
					        <button class="btn btn-default" type="button" onclick="searchCompany()">Search!</button>
					  	 </span>
					 </div><!-- /input-group -->
				</div>
			</div>
		</div>
		<br>
		<div class="col-md-12 col-sm-12 col-xs-12 col-lg-12" id="searchResult" style="padding: 10px 15px;">
			
		</div>
		<br>
		<div class="col-md-12 col-sm-12 col-xs-12 col-lg-12" id="interviewFeedbackResult" style="padding: 10px 15px;">
			
		</div>
		
		<input type="hidden" value="${sessionScope.user.email_id}" name="user_email" />
		
		<input type="hidden" value="${contextPath}/searchCompanyAjax" id="url_searchCompanyAjax"/>
		<input type="hidden" value="${contextPath}/searchCompanyUserAjax" id="url_searchCompanyUserAjax"/>
		<input type="hidden" value="${contextPath}/searchInterviewQuestionAjax" id="url_searchInterviewQuestionAjax"/>
		
	</div>
</div>

<script>
function searchCompany(){
	var url_ = $("#url_searchCompanyAjax").val();
	var url_2 = $("#url_searchCompanyUserAjax").val();
	$("#searchResult").html("");
	var searchText = $("#search_field").val();
	
	if(searchText != ""){
		$.post( url_, {search_text:searchText })
  		.done(function(result) {
  			
  			if(result){
  				for(var prop in result) {
  	 				$("#searchResult").append("<div style='background-color: #f1f1f1;padding: 10px;'><h5><strong>Interview Review</strong></h5><h5>Company Name: "+result[prop].company_name+"</h5><h5>Location: "+result[prop].location+"</h5><button class='btn-xs btn-primary' onclick='interviewQuestion("+result[prop].id+")'>Interview Question</button</div>")
  	 				
  		  			$.post( url_2, {companyId:result[prop].id })
  		  	  		.done(function(result_) {
  			  	  		for(var prop in result_) {
  			  			  $('#searchResult').append("<div style='background-color: #f1f1f1;padding: 0px 10px 10px;'><h5 style='margin:0px'>Student did Co-op/Internship: "+result_[prop].displayName+"</h5></div>");
  			  			}
  		  	  		});
  	  			}
  			}else{
  				$("#searchResult").append("<div><h4> No result Found</h4></div>");
  			}
  		});
	}else{
		$("#searchResult").append("Please enter Company Name!!");
	}
}

function interviewQuestion(company_id)
{
	var url_3 = $("#url_searchInterviewQuestionAjax").val();
	$("#interviewFeedbackResult").html("");
	if(company_id != ""){
		$.post( url_3, {specific_company_id:company_id})
  		.done(function(result) {
  			if(result){
  				$("#interviewFeedbackResult").append('<table class="table table-striped"><thead><tr><td class="col-lg-2">Interview Question</td><td class="col-lg-2">Interview Process</td><td class="col-lg-2">Interview Difficulty</td><td class="col-lg-2">Got Offer</td><td class="col-lg-2">Company name</td><td class="col-lg-2">User</td></th></thead></table>');
  				for(var prop in result) {
  	 				$("#interviewFeedbackResult").append('<table class="table table-striped"><tbody><tr><td class="col-lg-2">'+result[prop].interviewQuestion+'</td><td class="col-lg-2">'+result[prop].interviewProcess+'</td><td class="col-lg-2">'+result[prop].interviewDifficulty+'</td><td class="col-lg-2">'+result[prop].getOffer+'</td><td class="col-lg-2">'+result[prop].company.company_name+'</td><td class="col-lg-2">'+result[prop].user.displayName+'</td></tr></tbody></table>');
  				}	
  			}else{
  				$("#interviewFeedbackResult").append("<div>Result not found!!!</div>");
  			}
  		});  
	}else{
		$("#interviewFeedbackResult").append("<div>Result not found!!!</div>");
	}	
}
</script>
<jsp:include page="footer.jsp"></jsp:include>