<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Mail</title>
<style>
*{ box-sizing: border-box; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
body{ background-color: #ECEFF1; }
.container{
  width: 100%;
}
.content{
  background-color: #ffffff;
  max-width: 580px;
  margin: 0 auto;
  margin-top: 20px;
  border: 2px solid #90A4AE80;
}

.content-main{
  padding: 2rem 2.5rem;
  display: flex;
  flex-direction: column;
}

.content-title{
  margin: 0;
  font-weight: 600;
  color: #0b151a;
  margin-bottom: 1rem;
}

.content-msg{
  font-weight: 500;
  color: #081216;
  padding: 0;
  margin: 0;
}

.v-gap{
  margin: 4rem 0;
}

.btn{
  text-decoration: none;
  padding: 12px 24px;
  background-color: #0f140d;
  color: #ffffff;
  font-weight: 600;
  /* border: 1.5px solid #2e2136; */
  align-self: center;
  border-radius: 4px;
  transition: .3s all;
}
.btn:hover{
  background-color: #605461;
}

.content-footer{
  border-top: 2px solid #1e242780;
  background-color: #2f2331;
  padding: 2rem 2.5rem;
}
.content-footer .content-msg{
  color: #ffffff;
  font-size: .8rem;
  letter-spacing: .8px;
}
</style>
</head>
<body>
	<div class="container">
	  <div class="content">
	    <div class="content-main">
	       <div>
	       		<h1 class="content-title text-uppercase">
	        		Hi <span style="text-transform: uppercase;">${username}</span>,
			      </h1>
			      <p class="content-msg">
			        Let's make your work smarter and simpler you can login using the details given below,<br><br>
			        Email - ${email}<br>
			        Password - ${password}
			      </p>
           </div>
           <br>
		  <div style="margin: auto;text-decoration:none">
		  		<a href="http://localhost:8080/login" class="btn v-gap">
			        Login
			     </a>
          </div>
	    </div>
	    <div class="content-footer">
	      <p class="content-msg">
	        Regards,<br>
	        General Manager(E-Bank),<br>
	        Contact-9677693357
	      </p>
	    </div>
	  </div>
	</div>
</body>
</html>