<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp"></jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="container" style="padding:0px">
	
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-center" style="color:red;font-size:18px;padding: 10px;">${errorMessage}</div>
	
	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 text-left" style="padding:0px">
	
		<div class="col-md-6 col-xs-12 col-sm-12 col-lg-6 text-left" style="font-size:30px;font-weight:600">
			Welcome ${sessionScope.user.displayName}
			 ${sessionScope.user.email_id}
		</div>
		
		<div class="col-md-6 col-xs-12 col-sm-12 col-lg-6 text-right">
			<a href="${contextPath}/admin/add_company" class="btn btn-primary">Add Company Details</a> &nbsp;&nbsp;&nbsp;
			<a href="${contextPath}/admin/add_internship" class="btn btn-primary">Add Internship Details</a> &nbsp;&nbsp;&nbsp;
			<a href="${contextPath}/admin/list_company" class="btn btn-primary">Company List</a>
		</div>
			
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>