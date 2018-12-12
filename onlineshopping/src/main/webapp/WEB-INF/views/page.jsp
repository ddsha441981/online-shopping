<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Online Shopping Website Using Spring MVC and Hibernate">
<meta name="author" content="Deendayal Kumawat">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>Online Shopping - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
	
</script>


<!-- Latest compiled and minified JavaScript -->
<script src="${js}/jquery.min.js"></script>

 <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->

<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script> -->

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme  We can change any Theme by using this css-->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap DataTable -->
<link href="${css}/dataTables.bootstrap.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
<!-- loader when page loading -->
<div class="se-pre-con"></div>

	<div class="wrapper">

		<!-- Navigation -->

		<%@ include file="./shared/navbar.jsp"%>

		<!-- Page Content -->

		<div class="content">
			<!--  Loading Home Content Here -->
			<c:if test="${userClickHome == true}">
				<%@ include file="home.jsp"%>
			</c:if>

			<!--  When User Click On About Us Page Load only -->
			<c:if test="${userClickAbout == true}">
				<%@ include file="about.jsp"%>
			</c:if>

			<!--  When User Click On Conatct Us Page Load only -->
			<c:if test="${userClickContact == true}">
				<%@ include file="contact.jsp"%>
			</c:if>
			
			<!--  When User Click On All Product and Category Product Page Load only -->
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@ include file="listProducts.jsp"%>
			</c:if>
			
			<!--  When User Click On Show Product Page Load only -->
			<c:if test="${userClickShowProduct == true}">
				<%@ include file="singleProduct.jsp"%>
			</c:if>
			
			<!--  When User Click On Manage Product Page Load only -->
			<c:if test="${userClickManageProducts == true}">
				<%@ include file="manageProducts.jsp"%>
			</c:if>
			
			<!--  When User Click On Manage Product Page Load only -->
			<c:if test="${userClickShowCart == true}">
				<%@ include file="cart.jsp"%>
			</c:if>

		</div>


		<!-- Footer -->
		<%@ include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		
		<%-- <script src="${js}/jquery.js"></script> --%>
		<%-- <script src="${js}/jquery.min.js"></script> --%>
		
		<!-- Jquery Validator -->
		<script src="${js}/jquery.validate.js"></script>
		
		<!-- Bootsrap core jquery -->
		<script src="${js}/bootstrap.min.js"></script>
 		
		<!-- DataTable Plugin -->
		<script src="${js}/jquery.dataTables.min.js"></script>
		
		<!-- DataTable Bootstrap Script -->
		<script src="${js}/dataTables.bootstrap.min.js"></script>
		
		<!-- Boot Box Plugin -->
		<script src="${js}/bootbox.min.js"></script>
		
		
		<!--  Self Coded JavaScript Code myapp.js-->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>