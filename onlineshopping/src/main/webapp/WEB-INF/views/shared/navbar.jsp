  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
 
  <script>
  window.userRole = '${userModel.role}';
  </script>
  
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="${contextRoot}/home">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item" id="about">
              <a class="nav-link" href="${contextRoot}/about">About </a>
            </li>
            <li class="nav-item" id="listProducts">
              <a class="nav-link" href="${contextRoot}/show/all/products">View Products</a>
            </li>
            
            <%--the manage product access only admin --%>
            <security:authorize access="hasAuthority('ADMIN')">
	            <li class="nav-item" id="manageProducts">
	              <a class="nav-link" href="${contextRoot}/manage/products">Manage Products</a>
	            </li>
            </security:authorize>
            
            <li class="nav-item" id="contact">
              <a class="nav-link" href="${contextRoot}/contact">Contact </a>
            </li>
          </ul>
          
          <ul class="nav navbar-nav navbar-right">
          
          <security:authorize access="isAnonymous()">
	          	<li class="nav-item" id="register">
	              <a class="nav-link" href="${contextRoot}/register">Sign Up </a>
	            </li>
	            
	            <li class="nav-item" id="login">
	              <a class="nav-link" href="${contextRoot}/login">Login </a>
	            </li>
            </security:authorize>
          
          	<security:authorize access="isAuthenticated()">
	          	<li class="dropdown" id="userCart">
	         		<a href="javascript:void(0)"
	         			class="btn btn-default dropdown-toggle"
	         			id="dropdownMenu1"
	         			data-toggle="dropdown">
	         			
	         			${userModel.fullName}
	         			<span class="caret"></span>
	         		</a>
	         		
	         		<ul class="dropdown-menu">
	         		<!-- access only user who can add cart -->
	         		<security:authorize access="hasAuthority('USER')">
		         			<li>
		         				<a href="${contextRoot}/cart/show">
		         					<span class="glyphicon glyphicon-shopping-cart"></span>&#160;
		         					<span class="badge">${userModel.cart.cartLines}</span>
		         					- &#8377; ${userModel.cart.grandTotal}
		         				</a>
		         			</li>
		         			<li class="divider" role="separator"></li>
	         			</security:authorize>
	         			
	         			<li>
	         				<a href="${contextRoot}/perform-logout">Logout</a>
	         			</li>
	         			
	         		</ul>
	         		
	          	</li>
          	</security:authorize>
          </ul>
          
        </div>
      </div>
    </nav>
