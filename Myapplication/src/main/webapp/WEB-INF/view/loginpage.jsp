<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="/static/css/bootstrap.min.css" rel="stylesheet">
<link href="/static/css/style.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <div class="d-none d-md-flex col-7 bg-image">
        <div class="container text-left tit">
          <h1><span style="font-size: 8rem;font-family: 'Bebas Neue';">E-Bank</span></h1><br>
            <span style="font-size: 1.5rem;font-family: 'Bebas Neue';">   Smart way for banking</span>
        </div> 
      </div>
      <div class="col-5">
        <div class="login d-flex align-items-center py-5">
          <div class="container">
            <div class="row">
            <c:choose>
             <c:when test="${mode=='MODE_LOGIN'}">
              <div class="col-md-9 col-lg-8 mx-auto animated fadeInUp">
                <h3 class="login-heading mb-4">Login Here!!</h3>

                <form method="post" action="/login">
                  <c:if test="${not empty error}">
                  	<div class="alert alert-danger">
                  		<c:out value="${error}"></c:out>
                  	</div>
                  </c:if>
                  <div class="form-label-group">
                    <input type="text" <%-- value="${user.username}" --%> class="form-control" placeholder="Email" name="username" required autofocus>
                    <label>Email</label>
                  </div>
  
                  <div class="form-label-group">
                    <input type="password" name="password" <%-- value="${user.password}" --%> class="form-control" placeholder="Password" required>
                    <label for="inputPassword">Password</label>
                  </div>
  
                  <div class="custom-control custom-checkbox mb-3">
                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                    <label class="custom-control-label" for="customCheck1">Remember password</label>
                  </div>
                  <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Login</button>
                  <!-- <div class="text-center">
                    <a class="small" href="/register">Create new acoount?</a>
                  </div> -->
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
              </div>
              </c:when>
              <c:when test="${mode=='MODE_REGISTER'}">
              	<div class="col-md-9 col-lg-8 mx-auto animated fadeInUp">
                <h3 class="login-heading mb-4">Register Now!!</h3>
                
                <form method="POST" action="/save-user">
                  <input type="hidden" name="id" value="${user.id}"/>
                  <div class="form-label-group">
                    <input type="text" value="${user.username}" class="form-control" placeholder="Email address" name="username" required autofocus>
                    <label>Username or Email</label>
                  </div>
  				  <div class="form-label-group">
                    <input type="text" value="${user.firstname}" class="form-control" placeholder="First Name" name="firstname" required>
                    <label>First Name</label>
                  </div>
                  <div class="form-label-group">
                    <input type="text" value="${user.lastname}" class="form-control" placeholder="Last Name" name="lastname" required>
                    <label>Last Name</label>
                  </div>
                  <div class="form-label-group">
                    <input type="text" value="${user.age}" class="form-control" placeholder="Age" name="age" required>
                    <label>Age</label>
                  </div>  				
                  <div class="form-label-group">
                    <input type="password" value="${user.password}" class="form-control" placeholder="Password" name="password" required>
                    <label>Password</label>
                  </div>
                  
                  <div class="form-label-group d-none">
                    <input type="text" value="ROLE_USER" class="form-control" placeholder="Password" name="role" required>
                    <label>Password</label>
                  </div>

                  <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Register</button>
               		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
              </div>	
              </c:when>
             </c:choose>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
<script async>(function(w, d) { w.CollectId = "5f4e5360260f47638dd3935d"; var h = d.head || d.getElementsByTagName("head")[0]; var s = d.createElement("script"); s.setAttribute("type", "text/javascript"); s.setAttribute("src", "https://collectcdn.com/launcher.js"); h.appendChild(s); })(window, document);</script>
<script type="static/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>
