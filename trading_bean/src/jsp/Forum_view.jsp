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

	<jsp:useBean class="bean.Forum" id="forum" scope="application"/>
	<jsp:useBean class="bean.TradingPlace" id="tradingPlace" scope="application"/>
	<jsp:useBean class="bean.User" id="user" scope="session"/>

	<div id="wrap">
		<div id="main" class="container">

			<h4><%= user.getNickname() %>(<%= user.getEmail() %>)</h4>

			<% 
			ArrayList posts = forum.getPosts();
			pageContext.setAttribute("allPosts", posts);

			ArrayList securities = tradingPlace.getSecurities();
			pageContext.setAttribute("allSecurities", securities);

			%>

			<c:forEach items="${allSecurities}" var="current">
			<br><c:out value="${current.name}" />
			<c:out value="${current.type}" />
			</c:forEach>




		<form action="/trading_bean/TradeController">
			Text<input type="text" name="text"><br>
			<input type="submit"></form>


			<%=request.getParameter("message")%>


			<h3>Add a security</h3>
			<form action="/trading_bean/TradeController">
				<input type="hidden" name="action" value="addSecurity">
				<input type="text" name="security"><br>
				<button type="submit" id="user-btn" class="btn btn-primary">Add</button>
			</form>

			<h3>Lägg en köp/säljorder på ett värdepapper</h3>
			<form action="/TradeController">
				<input type="hidden" name="action" value="addOrder">
				Värdepapper: <select name="security">
				<option value="Ericsson">Ericsson</option>
				<option value="Telia">Telia</option>
				<option value="Volvo">Volvo</option>
			</select><br>
			Köp: <input type="radio" name="buyOrSell" value="B" checked>
			Sälj: <input type="radio" name="buyOrSell" value="S"><br>
			Pris: <input type="text" name="price" value=""><br>
			Antal: <input type="text" name="amount" value=""><br>
			<input type="submit" value="Utför">
		</form>

		<h3>Visa avslutade affärer i ett värdepapper</h3>
		<form action="/TradeController">
			<input type="hidden" name="action" value="viewTrades">
			Värdepapper: <select name="security">
			<option value="Ericsson">Ericsson</option>
			<option value="Telia">Telia</option>
			<option value="Volvo">Volvo</option>
		</select><br>
		<input type="submit" value="Utför">
	</form>

</div>
</div>
</body>
</html>
