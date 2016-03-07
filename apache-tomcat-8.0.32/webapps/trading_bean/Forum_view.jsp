<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

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
	
	<title>Trading View</title>

</head>

<body>
	<%@ page import="java.util.ArrayList" %>

	<jsp:useBean class="bean.TradingPlace" id="trading_place" scope="application"/>
	<jsp:useBean class="bean.User" id="user" scope="session"/>

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
							<li><a href=""><%=user.getNickname()%></a></li>
							<li><a href=""><%=user.getEmail()%></a></li>
						</ul>
					</div>
				</div>
			</nav>

			<%
			ArrayList securities = trading_place.getSecurities();
			pageContext.setAttribute("allSecurities", securities);
			%>


			<div id="sidebar" class="col-xs-2">
				<h4>All Securities</h4>

				<c:forEach items="${allSecurities}" var="current">
				<br><c:out value="${current.name}" />
				</c:forEach>

			</div>

			<div class="col-xs-4 col-xs-offset-2">
				<h3>Add a security</h3>
				<form action="/trading_bean/TradeController">
					<input type="hidden" name="action" value="addSecurity">
					<input type="text" name="security"><br><br>
					<button type="submit" id="user-btn" class="btn btn-primary">Add</button>
				</form>

			<h3>Make a buy/sell order on a security</h3>
			<form action="/trading_bean/TradeController">
				<input type="hidden" name="action" value="addOrder">
				Security: 
				<select name="security">

					<c:forEach items="${allSecurities}" var="current">
					<option value="${current.name}">${current.name}</option>
					</c:forEach>

				</select><br>
				Buy: <input type="radio" name="buyOrSell" value="buy" checked>
				Sell: <input type="radio" name="buyOrSell" value="sell"><br>
				Price: <input type="text" name="price" value=""><br>
				Amount: <input type="text" name="amount" value=""><br><br>
				<button type="submit" class="btn btn-primary">Order</button>
			</form>

			<h3>Show orders and finished trades for a security</h3>
			<form action="/trading_bean/TradeController">
				<input type="hidden" name="action" value="viewTrades">
				Security:
				<select name="showSecurity">
					<c:forEach items="${allSecurities}" var="current">
					<option value="${current.name}">${current.name}</option>
					</c:forEach>
				</select><br><br>
				<button type="submit" class="btn btn-primary">Show</button>
			</form>
		</div>

		<div class="col-xs-2 col-xs-offset-2">
		<h3><%=request.getParameter("showSecurity")%></h3><br>
			<div id="orderbar">
				<h4>Orders</h4>

			<%
			ArrayList orders = trading_place.getOrders(request.getParameter("showSecurity"));
			pageContext.setAttribute("securityOrders", orders);

			ArrayList trades = trading_place.getTrades(request.getParameter("showSecurity"));
			pageContext.setAttribute("securityTrades", trades);
			%>




				<c:forEach items="${securityOrders}" var="current">
				<br><c:out value="${current}" />
				</c:forEach>


			</div>
			<div id="tradebar">
				<h4>Trades</h4>

				<c:forEach items="${securityTrades}" var="current">
				<br><c:out value="${current}" />
				</c:forEach>
			</div>
		</div>


	</div>
</div>

</body>
</html>
