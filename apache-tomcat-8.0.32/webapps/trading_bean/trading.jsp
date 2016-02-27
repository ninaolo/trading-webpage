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

	<jsp:useBean class="bean.Forum" id="forum" scope="application"/>
	<jsp:useBean class="bean.User" id="user" scope="session"/>
	<jsp:useBean class="bean.Post" id="post" scope="request"/>

	<jsp:setProperty name="forum" property="*"/>
	<jsp:setProperty name="user" property="*"/>
	<jsp:setProperty name="post" property="*"/>

	<div id="wrap">
		<div id="main" class="container">

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

			<% if(session.isNew()){ %>


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

					<%
				}
				%>

			</div>
		</div>

	</div>
</div>


<!-- jQuery -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- Bootstrap JavaScript, needed if you want for instance tabs, models, popovers etc. -->
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

</body>
</html>
