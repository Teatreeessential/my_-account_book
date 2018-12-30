<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png"
	href="/resources/assets/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Light Bootstrap Dashboard by Creative Tim</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />


<!-- Bootstrap core CSS     -->
<link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="/resources/assets/css/animate.min.css" rel="stylesheet" />

<!--  Light Bootstrap Table core CSS    -->
<link href="/resources/assets/css/light-bootstrap-dashboard.css?v=1.4.0"
	rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="/resources/assets/css/demo.css" rel="stylesheet" />


<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300'
	rel='stylesheet' type='text/css'>
<link href="/resources/assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
<!-- 카운터 css -->
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/user/balance">나만의 가계부</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-dashboard"></i>
							<p class="hidden-lg hidden-md">Dashboard</p>
					</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <i class="fa fa-globe"></i> <b
							class="caret hidden-lg hidden-md"></b>
							<p class="hidden-lg hidden-md">
								5 Notifications <b class="caret"></b>
							</p>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#">Notification 1</a></li>
							<li><a href="#">Notification 2</a></li>
							<li><a href="#">Notification 3</a></li>
							<li><a href="#">Notification 4</a></li>
							<li><a href="#">Another notification</a></li>
						</ul></li>
					<li><a href=""> <i class="fa fa-search"></i>
							<p class="hidden-lg hidden-md">Search</p>
					</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">
							<p>
								Dropdown <b class="caret"></b>
							</p>

					</a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something</a></li>
							<li class="divider"></li>
							<li><a href="#">Separated link</a></li>
						</ul></li>

					<li>
					
					<c:choose>
					    <c:when test="${sessionScope.member eq null}">
					     <a href="/user/login">
							<p>Log in</p>
						</a>
					    </c:when>
					    <c:otherwise>
					    <a href="/user/logout">
							<p>Log out</p>
						  </a>
					    </c:otherwise>
					</c:choose>
										
					</li>
					<li class="separator hidden-lg"></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>