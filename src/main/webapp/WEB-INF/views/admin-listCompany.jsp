<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp"></jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<div class="container">
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center" style="color:red;font-size:18px">${errorMessage}</div>
	
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center">
		<strong><h1>All the Company Listed!!</h1></strong>
	</div>

	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 login_form" style="margin-top:50px">
		<table class="table table-striped">
			<thead>
				<tr>
					<td>Company Name</td>
					<td>Internship/Co-op Position</td>
					<td>Internship/Co-op Duration</td>
					<td>Internship/Co-op Start Date</td>
					<td>Internship/Co-op End Date</td>
					<td>Internship/Co-op Internship Source</td>
					<td>User</td>
					
				</tr>
			</thead>
			<tbody>
				<c:if test="${ not empty map}" >
					<c:forEach items="${map.internship}" var="internshipObject">
						<tr>
							<td>${internshipObject.company.company_name}</td>
							<td>${internshipObject.internshipPosition}</td>
							<td>${internshipObject.internshipDuration}</td>
							<td>${internshipObject.intershipStartDate}</td>
							<td>${internshipObject.intershipEndDate}</td>
							<td>${internshipObject.intershipSource}</td>
							<c:forEach items="${internshipObject.user}" var="user">
								<td>${user.displayName}</td>
							</c:forEach>
						</tr>
			  		</c:forEach>		
			  	</c:if>		
			
				
			</tbody>
		</table>
	</div>
	
	
</div>
<jsp:include page="footer.jsp"></jsp:include>