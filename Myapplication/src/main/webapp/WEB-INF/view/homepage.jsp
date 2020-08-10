<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="com.raja.model.ExportExcel" %>
<%@page import="com.raja.model.Fileupload" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eStatment Processing</title>
<link href="/static/css/bootstrap.min.css" rel="stylesheet">
<link href="/static/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script> 
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container king">
      <a class="navbar-brand js-scroll-trigger" href="/home">E-Bank</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav">
          <sec:authorize access="hasRole('ADMIN')">  
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/add">Add</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/edit">View</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/upload">Upload</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/search">Search</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/profile">Profile</a>
	          </li>
	          <li class="nav-item float-right">
	            <a class="nav-link js-scroll-trigger" href="/logout">Logout</a>
	          </li>
          </sec:authorize>
          <sec:authorize access="hasRole('USER')">
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/upload">Upload</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/search">Search</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/profile">Profile</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/logout">Logout</a>
	          </li>
          </sec:authorize>
        </ul>
      </div>
    </div>
  </nav>
  <c:choose>
  <c:when test="${mode=='MODE_HOME'}">
   <header class="text-white header-bg">
    <div class="container text-center">
      <h1 class="font-weight-bold w3-animate-opacity">Welcome Back, Mr/Mrs <c:out value="${user.username}" /></h1>
      <h2 class="lead w3-animate-opacity">E-statement processor was created to reduce your work just follow the steps given below</h2>
    </div>
  </header>
  <section class="sec">
  	<div class="container text-center read w3-animate-opacity">
  		<h1 class="font-weight-bold" >Read the instruction before starting your work</h1>
  	</div>
  	<div class="container step1 w3-animate-left">
	  	<div class="row">
		    <div class="col">
		      <div class="text-center text-box">
		  		 	<h1>Step 1:</h1><br>
		  		 	<h6>First of all choose your .csv<br>file from your system by clicking Upload button</h6>
		  	   </div>
		    </div>
		    <div class="col">
		      <div>
		  			<img src="/static/img/upload.png" width="500" height="500" alt="not loaded" class="img-thumbnail">
		  	  </div>
		    </div>
	  </div>
	 </div>
	 <div class="container w3-animate-right">
	  	<div class="row">
	  	    <div class="col">
		      <div>
		  			<img src="/static/img/search.jpg" width="500" height="500" alt="not loaded" class="img-thumbnail">
		  	  </div>
		    </div>
		    <div class="col">
		      <div class="text-center text-box">
		  		 	<h1>Step 2:</h1><br>
		  		 	<h6>Search the transaction details of specific account holders<br>By searching in the search box</h6>
		  	   </div>
		    </div>
	  </div>
	 </div>
	 <div class="container w3-animate-left">
	  	<div class="row">
		    <div class="col">
		      <div class="text-center text-box">
		  		 	<h1>Step 3:</h1><br>
		  		 	<h6>You can view the searched records<br>And you can download the record of<br>specific user as .csv file again</h6>
		  	   </div>
		    </div>
		    <div class="col">
		      <div>
		  			<img src="/static/img/imp.png" width="500" height="500" alt="not loaded" class="img-thumbnail">
		  	  </div>
		    </div>
	  </div>
	 </div>
  </section>
  <footer class="py-2 bg-dark">
    <div class="container">
       <div class="social-media d-flex justify-content-center">
       		<a href="#" class="fa fa-facebook"></a>
			<a href="#" class="fa fa-twitter"></a>
			<a href="#" class="fa fa-google"></a>
			<a href="#" class="fa fa-linkedin"></a>
       </div>
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>
  </c:when>
  <c:when test="${mode=='MODE_ADD' || mode=='MODE_ADD_USER'}">
	  <header class="text-white header-bg-about">
	    <div class="container text-center">
	      <h1 class="font-weight-bold w3-animate-opacity">Add User</h1>
	    </div>
	  </header>
	  <section class="sec">
  	<div class="container text-center csv-head">
  		<h2 class="font-weight-bold w3-animate-opacity">Enter the details to add user</h2><br><br>
  	</div>
  	<div class="container csv-body w3-animate-opacity">
  		<div class="card add-user-box">
  			<form method="POST" action="/add-user">
			  	<div class="card-body">
			  			<c:set var="size" value="${fn:length(lists)}"></c:set>
			  			<div class="form-group">
    						<label for="username">User ID</label>
						    <input type="text" class="form-control" placeholder="Enter UserID" name="userid">
						 </div>
			  			<div class="form-group d-none">
						    <input type="text" value="true" name="active">
						</div>
				  		<div class="form-group">
    						<label for="username">Name</label>
						    <input type="text" class="form-control" placeholder="Enter name" name="username">
						 </div>
						 <div class="form-group">
    						<label for="email">Email ID</label>
						    <input type="email" class="form-control" placeholder="Enter email" name="email">
						 </div>
						 <div class="form-group">
						 	<label for="email">Role</label>
						    <input type="text" class="form-control" name="role" placeholder="Enter role">
						</div>
						 <div class="form-group">
    						<label for="password">Password</label>
						    <input type="text" class="form-control" placeholder="Enter password" name="password">
						 </div>
						 <div class="float-right">
						 	<button type="submit" class="btn btn-primary">Add User</button>
						 </div>	
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
  		</div><br><br>
  		<c:choose>
  		<c:when test="${ mode=='MODE_ADD_USER'}">
	  		<div class="container text-center">
	  			<h2 class="font-weight-bold text-uppercase">Fill all the fields !!</h2>
	  		</div>
	  	</c:when>
	  	</c:choose>
  	</div>
  </section>
  </c:when>
  <c:when test="${mode=='MODE_EDIT'}">
  	  <header class="text-white header-bg-about">
	    <div class="container text-center">
	      <h1 class="font-weight-bold w3-animate-opacity">View User</h1>
	    </div>
	  </header>
	  <section class="sec">
	  		<div class="container">
	  			<h2 class="font-weight-bold w3-animate-opacity">List of User's:</h2>
	  		</div>
	  		<br><br>
	  		
	  		<div class="container">
		  		<table class="table">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col">Id</th>
					      <th scope="col">UserName</th>
					      <th scope="col">Email</th>
					      <th scope="col">Password</th>
					      <th scope="col">Role</th>
					      <th scope="col">Action</th>
					    </tr>
					  </thead>
						  <tbody>
						  	    <c:forEach var="list" items="${lists}">
						  	    	<c:set var="name" value="${list.role}"></c:set>
						  	    	<c:if test="${!fn:containsIgnoreCase(name, 'ROLE_ADMIN')}">
								      <tr scope="row">
								      		 <td><c:out value="${list.id }"/></td>
								             <td><c:out value="${list.username}" /></td>
								             <td><c:out value="${list.email}" /></td>
								             <td><c:out value="${list.password}" /></td>
								             <td><c:out value="${list.role}" /></td>
								             <td>
								             	<form method="POST" action="/delete-user">
								             		<input type="text"  class="d-none" name="email" value="${list.id}">
								             		<button class="btn btn-primary">Delete</button>
								             		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								             	</form>
								             </td>
								       </tr>
								     </c:if>
							   </c:forEach>
					  	</tbody>
				</table>
			</div>
	  </section>
  </c:when>
  <c:when test="${mode=='MODE_UPLOAD'}">
  <header class="text-white header-bg-up">
    <div class="container text-center">
      <h1 class="font-weight-bold w3-animate-opacity">File Upload</h1>
    </div>
  </header>
  <section class="sec">
  	<div class="container text-center csv-head">
  		<h1 class="font-weight-bold w3-animate-opacity">Upload Your file for further work</h1><br><br>
		<c:if test="${not empty error}">
			<div class="container text-center" style="width:500px">
	              <p class="font-weight-bold alert alert-danger"><c:out value="${error}" /></p>
	         </div>
	    </c:if>  	
  	</div>
  	<div class="container csv-body w3-animate-opacity">
  		<div class="card inner-box">
  			<form method="POST" action="/search" enctype="multipart/form-data">
  			  <div>
  			  	<h4>Choose your .csv file from your device</h4><br>
  			  </div>
			  <div class="card-body">
				  	<div class="text-center csv-box float-left">
				  		<input type="file" name="file" accept=".csv" class="file-box"/><br><br><br>
				  	</div>
				    <div class="float-right">
			  			<button type="submit" class="btn btn-primary">Upload</button>
		    		</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
  		</div>
  		<br><br>
  	</div>
  </section>
  </c:when>
  <c:when test="${mode=='MODE_SEARCH'}">
  <header class="text-white header-bg-s">
    <div class="container text-center">
      <h1 class="font-weight-bold w3-animate-opacity">Search Details</h1>	
    </div>
  </header>
  <section class="sec">
  	<div class="container text-center csv-head">
  		<h1 class="font-weight-bold w3-animate-opacity">Search your client details</h1><br><br>
  	</div>
  	<div class="container d-flex">
  		<div class="container w3-animate-opacity">
		  <div class="card inner-box">
				<div class="card-body">
					<div>
						<h4 class="text-left">Search by Account Number</h4><br><br>
					</div>
					<div>
					    <form class="form-inline md-form mr-auto" method="post" action="/search-clients">
				       		<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="find">
			          		<button class="btn btn-primary btn-rounded btn-sm my-0 waves-effect waves-light search-button" type="submit">Search</button>
			          		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        </form>
					</div>
			  </div>
			</div>
		</div>
		<div class="container w3-animate-opacity">
		  <div class="card inner-box">
				<div class="card-body">
					<div>
						<h4 class="text-left">Search by Date format</h4><br><br>
					</div>
					<div>
					    <form class="form-inline md-form mr-auto" method="post" action="/search-clientsbydate">
					    	<div  class="d-flex">
						    	<div>
						    		<label for="fromDate" class="float-left">From:</label>
					       			<input class="form-control mr-sm-2" type="date"  name="fromDate">
						    	</div>
						    	<div>
						    		<label for="endDate" class="float-left">To:</label>
					       			<input class="form-control mr-sm-2" type="date"  name="endDate">
						    	</div>
					    	</div><br><br><br><br>
					    	<div class="container ">
					    		<button class="btn btn-primary btn-rounded btn-sm my-0 waves-effect waves-light search-button float-right" type="submit">Find Records</button>
					    	</div>
					    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				       	</form>
					</div>
			  </div>
			</div>
		</div>
  	</div>
      <br><br>
      	<c:if test="${not empty error}">
			<div class="container text-center" style="width:500px">
	              <p class="font-weight-bold alert alert-danger"><c:out value="${error}" /></p>
	         </div>
	    </c:if>  
  </section>
  </c:when>
  <c:when test="${mode=='MODE_SEARCH_CLIENT' || mode=='MODE_DOWNLOAD'}">
	  <header class="text-white header-bg-s">
	    <div class="container text-center">
	      <h1 class="font-weight-bold w3-animate-opacity">Search Details</h1>
	    </div>
	  </header>
	  <section class="sec">
		  	<div class="container text-center csv-head">
		  		<h1 class="font-weight-bold w3-animate-opacity">Search your client details</h1><br><br>
		  	</div>
		  	<c:if test="${files.size()==0}">
				<div class="container text-center">
					<h2 class="font-weight-bold text-uppercase">There is no records in this Account number !!!</h2>
				</div>
			</c:if>
		  	<div class="container w3-animate-opacity">
		  		<div class="card inner-box">
					  <div class="card-body">
						  	<div>
							    <h4 class="text-left">Enter account number and press search</h4><br><br>
						  	</div>
						    <div>
						        <form class="form-inline md-form mr-auto" method="post" action="/search-clients">
						          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="find">
						          <button class="btn btn-primary btn-rounded btn-sm my-0 waves-effect waves-light search-button" type="submit">Search</button>
						          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						        </form>
						    </div>
					  </div>
				</div>
		   </div><br><br>
		   <c:if test="${files.size()>0}">
		   <div class="container">
		  		<h2>Account Number: ${find}</h2><br><br>
		  		<table class="table">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col">Transaction ID</th>
					      <th scope="col">AccNo</th>
					      <th scope="col">Date</th>
					      <th scope="col">Time</th>
					      <th scope="col">Description</th>
					      <th scope="col">Withdrawals</th>
					      <th scope="col">Credits</th>
					      <th scope="col">Running Balance</th>
					    </tr>
					  </thead>
					  <c:set var="total" value="${0}" />
					  <tbody>
						  <c:choose>
							  	<c:when test="${mode=='MODE_SEARCH_CLIENT'}">
								    <c:forEach var="file" items="${files}">
								                <tr scope="row">
								                	<td><c:out value="${file.trans_id}" /></td>
								                    <td><c:out value="${file.accno}" /></td>
								                    <td><c:out value="${file.date}" /></td>
								                    <td><c:out value="${file.time}" /></td>
								                    <td><c:out value="${file.description}" /></td>
								                    <td><c:out value="${file.withdrawal}" /></td>
								                    <td><c:out value="${file.credits}" /></td>
								                  	<c:choose>
								                  		<c:when test="${file.withdrawal==0}">
								                  			<fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${file.credits}" />
									                  		<c:set var="total" value="${total+i}" />
									                  		<td><c:out value="${total}"></c:out></td>
									                  		</c:when>
								                  		<c:when test="${file.credits==0}">
								                  			<fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${file.withdrawal}" />
									                  		<c:set var="total" value="${total-i}" />
									                  		<td><c:out value="${total}"></c:out></td>
								                  		</c:when>
								                  	</c:choose>
								                </tr>
							         </c:forEach>
						         </c:when>
					       </c:choose>
					  </tbody>
				</table>
			</div>
			<br><br><br>
			<div class="container">
				  <div class="row d-flex ml-auto">
				    <div class="col-sm move">
				      <form method="post" action="/export-csv">
						<input type="text" value="${find}" class="form-control mr-sm-2 d-none" name="export">
						<button class="btn btn-primary download-btn">Export as .csv</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					  </form>
				    </div>
				    <div class="col-sm move">
				      <form method="post" action="/export-pdf">
						<input type="text" value="${find}" class="form-control mr-sm-2 d-none" name="export">
						<button class="btn btn-primary download-btn">Export as .pdf</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					  </form>
				    </div>
				    <div class="col-sm move">
				      <form method="post" action="/export-excel">
						<input type="text" value="${find}" class="form-control mr-sm-2 d-none" name="export">
						<button class="btn btn-primary download-btn">Export as xlsx</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					  </form>
				    </div>
				  </div>
			</div>
		</c:if>
	  </section>
  </c:when>
  <c:when test="${mode=='MODE_DOWNLOAD_BY_DATE'}">
  	<header class="text-white header-bg-s">
	    <div class="container text-center">
	      <h1 class="font-weight-bold w3-animate-opacity">Search Details</h1>
	    </div>
	</header>
	<section class="sec">
		<div class="container w3-animate-opacity">
		  <div class="card inner-box">
				<div class="card-body">
					<div>
						<h4 class="text-left">Search by Date format</h4><br><br>
					</div>
					<div>
					    <form class="form-inline md-form mr-auto" method="post" action="/search-clientsbydate">
					    	<div  class="d-flex">
						    	<div>
						    		<label for="fromDate" class="float-left">From:</label>
					       			<input class="form-control mr-sm-2" type="date"  name="fromDate">
						    	</div>
						    	<div>
						    		<label for="endDate" class="float-left">To:</label>
					       			<input class="form-control mr-sm-2" type="date"  name="endDate">
						    	</div>
					    	</div><br><br><br><br>
					    	<div class="container ">
					    		<button class="btn btn-primary btn-rounded btn-sm my-0 waves-effect waves-light search-button float-right" type="submit">Find Records</button>
					    	</div>
					    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				       	</form>
					</div>
			  </div>
			</div>
		</div>
		<br><br>
		<c:if test="${files.size()>0}">
		<div class="container">
	  		<h2>Account Statements between ${start} and ${end}</h2><br><br>
	  		<table class="table">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">Transaction ID</th>
				      <th scope="col">AccNo</th>
				      <th scope="col">Date</th>
				      <th scope="col">Time</th>
				      <th scope="col">Description</th>
				      <th scope="col">Withdrawals</th>
				      <th scope="col">Credits</th>
				      <th scope="col">Running Balance</th>
				    </tr>
				  </thead>
				  <c:set var="total" value="${0}" />
				  <tbody>
					  <c:choose>
						  	<c:when test="${mode=='MODE_DOWNLOAD_BY_DATE'}">
							    <c:forEach var="file" items="${files}">
							                <tr scope="row">
							                	<td><c:out value="${file.trans_id}" /></td>
							                    <td><c:out value="${file.accno}" /></td>
							                    <td><c:out value="${file.date}" /></td>
							                    <td><c:out value="${file.time}" /></td>
							                    <td><c:out value="${file.description}" /></td>
							                    <td><c:out value="${file.withdrawal}" /></td>
							                    <td><c:out value="${file.credits}" /></td>
							                  	<c:choose>
							                  		<c:when test="${file.withdrawal==0}">
							                  			<fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${file.credits}" />
								                  		<c:set var="total" value="${total+i}" />
								                  		<td><c:out value="${total}"></c:out></td>
								                  		</c:when>
							                  		<c:when test="${file.credits==0}">
							                  			<fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${file.withdrawal}" />
								                  		<c:set var="total" value="${total-i}" />
								                  		<td><c:out value="${total}"></c:out></td>
							                  		</c:when>
							                  	</c:choose>
							                </tr>
						         </c:forEach>
					         </c:when>
				       </c:choose>
				  </tbody>
			</table>
		</div>
		<br><br><br>
			 <div class="container">
			  	<div class="row d-flex ml-auto">
					<div class="col-sm move">
					      <form method="post" action="/exportbydate-csv">
							<input type="text" value="${start}" class="form-control mr-sm-2 d-none" name="start">
							<input type="text" value="${end}" class="form-control mr-sm-2 d-none" name="end">
							<button class="btn btn-primary download-btn">Export as .csv</button>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						  </form>
					 </div>
					 <div class="col-sm move">
					      <form method="post" action="/exportbydate-pdf">
							<input type="text" value="${start}" class="form-control mr-sm-2 d-none" name="start">
							<input type="text" value="${end}" class="form-control mr-sm-2 d-none" name="end">
							<button class="btn btn-primary download-btn">Export as .pdf</button>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						  </form>
					 </div>
					 <div class="col-sm move">
					      <form method="post" action="/exportbydate-excel">
							<input type="text" value="${start}" class="form-control mr-sm-2 d-none" name="start">
							<input type="text" value="${end}" class="form-control mr-sm-2 d-none" name="end">
							<button class="btn btn-primary download-btn">Export as xlsx</button>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						  </form>
					 </div>
				</div>
			</div>
	</c:if>
	<c:if test="${files.size()==0}">
		<div class="container text-center">
				<h2 class="font-weight-bold text-uppercase">There is no records between this range !!!</h2>
		</div>
	</c:if>
</section>
</c:when>
<c:when test="${mode=='MODE_PROFILE' || mode=='MODE_UPDATE_PASSWORD'}">
	<header class="text-white header-bg-up">
	    <div class="container text-center">
	      <h1 class="font-weight-bold w3-animate-opacity">Profile</h1>
	    </div>
    </header>
    <section class="sec">
    		<c:choose>
    			<c:when test="${mode=='MODE_PROFILE'}">
		    		<br><br>
		    		<div class="card profile-box">
		    			<div class="card-body">
		    				<div>
		    					<label class="font-weight-bold">Username:</label>
		    					<c:out value="${user.username}"/>
		    				</div>
		    				<div>
		    					<label class="font-weight-bold">Email ID:</label>
		    					<c:out value="${user.email}"/>
		    				</div>
		    				<div>
		    					<label class="font-weight-bold">Password:</label>
		    					<c:out value="${user.password}"/>
		    				</div>
		    				<div>
		    					<label class="font-weight-bold">Role:</label>
		    					<c:out value="${user.role}"/>
		    				</div><br>
		    				<!-- <div class="text-center">
		    					<a href="/update-password" class="btn btn-primary float-left">Change Password</a>
		    				</div> -->
		    			</div>
		    		</div>
		    	</c:when>
		    	<c:when test="${mode=='MODE_UPDATE_PASSWORD'}">
		    		<div>
		    			<h2 class="text-center font-weight-bold">Update Password</h2>
		    		</div>
		    		<br><br>
		    		<div class="card password">
		    			<div class="card-body">
		    				<form method="POST" action="/change-password">
				    				<div>
				    					<label for="password" >Enter your new password:</label><br>
							       		<input class="form-control mr-sm-2" type="text"  name="password">
							       		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							       	</div>
							       	<br>
							       	<div>
							       		<button class="btn btn-primary float-right">Update password</button>
							       	</div>
		    				</form>
		    			</div>
		    		</div>
		    	</c:when>
    		</c:choose>
    </section>
</c:when>
  <c:when test="${mode=='MODE_ABOUT'}">
  	  <header class="text-white header-bg-about">
	    <div class="container text-center">
	      <h1 class="font-weight-bold w3-animate-opacity">About Us</h1>
	    </div>
	  </header>
	 <section class="sec">
  		<div class="column">
		    <div class="container">
		      <div class="container about-us ml-auto">
		      	<div class="align-center">
			      	<img src="/static/img/raja1.jpg" alt="raja" style="width:18rem;height:22rem;margin:1rem 0 0 2rem">
		      	</div>
		      	<div class="text-center">
			        <h3 class="font-weight-bold">RAJA M</h3>
		      	</div>
		      	<div class="text-center">
			        <h4 class="font-weight-light">Full Stack Developer</h4>
		      	</div>
		      	<div class="text-center">
		      		<h6 class="font-weight-light">Worked on UI/UX design and back-end</h6>
		      		<h6 class="font-weight-light">using Spring</h6>
		      	</div>
		      	<div class="d-flex footer-icon social">
                       <i class="fa fa-twitter about-twit" ></i>
                       <i class="fa fa-instagram about-insta"></i>
                       <i class="fa fa-linkedin about-link"></i>
                       <i class="fa fa-github about-git"></i>
                 </div>
		      </div>
		    </div>
  		</div>
  	 </section>
  	 <footer class="py-2 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
	    </div>
  	</footer>
  </c:when>
 </c:choose>

<script type="/static/js/bootstrap.min.js"></script>
<script src="/static/jquery/jquery.min.js"></script>
<script src="/static/jquery-easing/jquery.easing.min.js"></script>
<script src="/static/js/scrolling-nav.js"></script>
</body>
</html>