<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<!-- Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Lato:300,100,400' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Quicksand:400,300' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	<!-- Bootstrap -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">

	<!--Style -->
	<link type="text/css" href="../css/site.css" rel="stylesheet">
	<link type="text/css" href="../css/navbar.css" rel="stylesheet">
	
	<title>Trading</title>

</head>

<body>


	<%@ page import="java.util.ArrayList" %>

	<jsp:useBean class="bean.Trade" id="trade" scope="request"/>
	<jsp:useBean class="bean.User" id="user" scope="session"/>
	<jsp:useBean class="bean.Order" id="order" scope="request"/>
	<jsp:useBean class="bean.Security" id="security" scope="request"/>

	<jsp:setProperty name="trade" property="*"/> <!-- VAD ÄR DETTA? -->
	<jsp:setProperty name="user" property="*"/>
	<jsp:setProperty name="order" property="*"/>
	<jsp:setProperty name="security" property="*"/>

	<div id="wrap">
		<div id="main" class="container">

		<!-- NAVIGATION BAR -->

			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container-fluid">
					<ul class="nav navbar-nav navbar-left">
						<li><a href="">Home</a></li>
					</ul>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="">About us</a></li>
							<li><a href="">Help</a></li>
						</ul>
					</div>
				</div>
			</nav>

			<!-- CREATE USER -->

			<% if(session.isNew()) { %>
				<h1>Welcome!</h1>
				<div class="row">
					<form role="form">
						<div class="form-group">
							Nickname<input type="text" name="nickname" class="form-control">
							Email<input type="text" name="email" class="form-control">
						</div>
						<button type="submit" id="user-btn" class="btn btn-primary">Create User</button>
					</form>
				</div>
			
			<% } else { %>

			<div id="sidebar" class="col-xs-2">
				<h4>Securities</h4>
				Lista med securities
			</div>

			<div id="securities">
				<div class="col-xs-4 col-xs-offset-2">

					<img src="../images/stock.png" height="150" width="190">

					<h2>Security name</h2>

					<div id="orders">
						All orders here
					</div>

					<b><p>Place an order</p></b>
					<div class="row">
						<form role="form">
							<div class="form-group col-xs-6">
								Quantity<input type="text" name="quantity" class="form-control">
							</div>
							<div class="form-group col-xs-6">
								Price<input type="text" name="price" class="form-control">
							</div>
							<button type="submit" id="search-btn" class="btn btn-primary">Place order</button>
						</form>
					</div>

				</div>
			</div>

			<div id="userbar" class="col-xs-2 col-xs-offset-2">
				<h4>User information</h4>
				<b>Nickname: </b><%=request.getParameter("nickname")%><br>
				<b>Email: </b><%=request.getParameter("email")%>
			</div>

			<%
			} %> <!-- END ELSE -->
			

		</div><!-- END MAIN -->
	</div> <!-- END WRAP -->


	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- Bootstrap JavaScript, needed if you want for instance tabs, models, popovers etc. -->
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

</body>
</html>
