<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

</head>
<body>
	<nav class="navbar navbar-default navbar-fixed">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navigation-example-2">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Dashboard</a>
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
					<li><a href="">
							<p>Account</p>
					</a></li>
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
					<li><a href="#">
							<p>Log out</p>
					</a></li>
					<li class="separator hidden-lg"></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">
					<div class="card">

						<div class="header">
							<h4 class="title">이번 달 총 지출,수입</h4>
							<p class="category">(기준:한달단위)</p>
						</div>
						<div class="content">
							<canvas id="sum_chart" width="400" height="400"></canvas>
						</div>
					</div>
				</div>

				<!-- 총 수입 -->
				<div class="col-md-4">
					<div class="card">
						<div class="header">
							<h4 class="title">한달간의 총 수입</h4>
							<p class="category">(기준:일자별)</p>
						</div>
						<div class="content">
							<canvas id="income_chart" width="400" height="400"></canvas>
						</div>
					</div>
				</div>
				<!-- 총지출 -->
				<div class="col-md-4">
					<div class="card ">
						<div class="header">
							<h4 class="title">한달간의 총 지출</h4>
							<p class="category">(기준:일자별)</p>
						</div>
						<div class="content">

							<canvas id="consume_chart" width="400" height="400"></canvas>

						</div>
					</div>
				</div>




			</div>


			<div class="row">
				<!--가장 많이 사용한 가게 -->
				<div class="col-md-4">
					<div class="card ">
						<div class="header">
							<h4 class="title">당신이 가장 많이 이용한 가게</h4>
							<p class="category">(기준:결제횟수)</p>
						</div>
						<div class="content">
							<div class="content table-responsive table-full-width">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>가게이름</th>
											<th>결제횟수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${branch_map}" begin=0 end=2 step=1 varStatus="status">
										<tr>
											<td>${item.key}</td>
											<td>${item.value}</td>
										</tr>
										</c:forEach>

									</tbody>
								</table>

							</div>

							<div class="footer">
								<hr>
								<div class="stats">
									<i class="fa fa-history"></i> Updated 3 minutes ago
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 가장 많이 지출한 날 -->
				<div class="col-md-4">
					<div class="card ">
						<div class="header">
							<h4 class="title">가장 많은 지출이 있던 날</h4>
							<p class="category">Backend development</p>
						</div>
						<div class="content">
							<div class="content table-responsive table-full-width">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>가게이름</th>
											<th>결제횟수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${branch_map}" begin=0 end=2 step=1 varStatus="status">
										<tr>
											<td>${item.key}</td>
											<td>${item.value}</td>
										</tr>
										</c:forEach>

									</tbody>
								</table>

							</div>

							<div class="footer">
								<hr>
								<div class="stats">
									<i class="fa fa-history"></i> Updated 3 minutes ago
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 가장 많이 수입이 있던 날 -->
				<div class="col-md-4">
					<div class="card ">
						<div class="header">
							<h4 class="title">가장 많은 수입이 있던 날</h4>
							<p class="category">Backend development</p>
						</div>
						<div class="content">
							<div class="content table-responsive table-full-width">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>가게이름</th>
											<th>결제횟수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${branch_map}" begin=0 end=2 step=1 varStatus="status">
										<tr>
											<td>${item.key}</td>
											<td>${item.value}</td>
										</tr>
										</c:forEach>

									</tbody>
								</table>

							</div>

							<div class="footer">
								<hr>
								<div class="stats">
									<i class="fa fa-history"></i> Updated 3 minutes ago
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>




















		</div>
	</div>







</body>
<!-- chart.js -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.js"></script>

<!--   Core JS Files   -->
<script src="/resources/assets/js/jquery.3.2.1.min.js"
	type="text/javascript"></script>
<script src="/resources/assets/js/bootstrap.min.js"
	type="text/javascript"></script>

<!--  Charts Plugin -->
<script src="/resources/assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="/resources/assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="/resources/assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="/resources/assets/js/demo.js"></script>

<script>
$(document).ready(function() {
	const obj_to_list = function(obj) {

		let arr1 = new Array();
		let arr2 = new Array();

		Object.keys(obj).forEach(function(k) {
			arr1.push(k)
			arr2.push(obj[k])
		});
		return new Array(arr1, arr2);
};
	let income_list = obj_to_list(${income_map});
	let consume_list = obj_to_list(${consume_map});
	let total_income = ${total_income};
	let total_consume = ${total_consume};
	

	
	
		//얘네 다 obj 임.
		//그래프로 나타낼 것들
		



	//수입과 지출을 위한 도넛 차트
	var sum_chart = $("#sum_chart");
	var myDoughnutChart = new Chart(sum_chart, {
		type : 'doughnut',
		options : {},
		data : {
			labels: ['수입','지출'],
			    datasets: [{
			        data: [total_income,total_consume],
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)',
						'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255,99,132,1)',
						'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
			    }],

			    // These labels appear in the legend and in the tooltips when hovering different arcs
			    
		}
	});
	
		var income_chart = document.getElementById("income_chart").getContext('2d');
		var myChart = new Chart(income_chart, {
			type : 'bar',
			data : {
				labels : income_list[0],
				datasets : [ {
					label : '# of Votes',
					data : income_list[1],
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255,99,132,1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				}
			}
		});
		var consume_chart = document.getElementById("consume_chart").getContext('2d');
		var myChart = new Chart(consume_chart, {
			type : 'bar',
			data : {
				labels : consume_list[0],
				datasets : [ {
					label : '# of Votes',
					data : consume_list[1],
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255,99,132,1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				}
			}
		});
	
	})
</script>
</html>
