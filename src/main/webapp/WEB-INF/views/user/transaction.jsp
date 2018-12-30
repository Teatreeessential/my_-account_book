<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
</head>
<body>
	<%@include file="../includes/header.jsp"%>
	<div class="row">
		<div class="col-md-3">
			<div class="alert alert-info">
				<span>${balance.bank_name}의 계좌 입니다.</span>
			</div>
		</div>
		<div class="col-md-3">
		<div class="alert alert-info">
			<span>계좌명:${balance.product_name}</span>
		</div>
		</div>
		<div class="col-md-3">
		<div class="alert alert-info">
			<span>계좌의종류:${balance.account_type}</span>
		</div>
		</div>
		
		
		<div class="col-md-3">
		<div class="alert alert-info">
			<span class="counter">계좌잔액: ${balance.balance_amt}</span>

		</div>
		</div>
		
	</div>
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




			</div>


			<div class="row">
				<!--가장 많이 사용한 가게 -->
				<div class="col-md-4">
					<div class="card ">
						<div class="header">
							<h4 class="title">가장 많이 이용한 가게</h4>
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
										<c:forEach var='item' items='${branch_map}'>
											<tr>
												<td>${item.key}</td>
												<td>${item.value}</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>

							</div>


						</div>
						<div class="footer">
							<hr>
							<div class="stats">
								<i class="fa fa-history"></i> 한달 기준
							</div>
						</div>
					</div>
				</div>
				<!-- 가장 많이 지출한 날 -->
				<div class="col-md-4">
					<div class="card ">
						<div class="header">
							<h4 class="title">가장 많은 지출이 있던 날</h4>
							<p class="category">(기준:결제금액)</p>
						</div>
						<div class="content">
							<div class="content table-responsive table-full-width">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>날짜</th>
											<th>금액</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var='item' items='${top_three_consume_map}'>
											<tr>
												<td>${item.key}</td>
												<td>${item.value}</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>

							</div>


						</div>
						<div class="footer">
							<hr>
							<div class="stats">
								<i class="fa fa-history"></i> 한달 기준
							</div>
						</div>
					</div>
				</div>
				<!-- 가장 많이 수입이 있던 날 -->
				<div class="col-md-4">
					<div class="card ">
						<div class="header">
							<h4 class="title">가장 많은 수입이 있던 날</h4>
							<p class="category">(기준:입금금액)</p>
						</div>
						<div class="content">
							<div class="content table-responsive table-full-width">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>날짜</th>
											<th>금액</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var='item' items='${top_three_income_map}'>
											<tr>
												<td>${item.key}</td>
												<td>${item.value}</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>

							</div>


						</div>
						<div class="footer">
							<hr>
							<div class="stats">
								<i class="fa fa-history"></i> 한달 기준
							</div>
						</div>
					</div>
				</div>
			</div>




















		</div>
	</div>






	<%@include file="../includes/footer.jsp"%>
</body>


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
	let balance_amt = ${balance.balance_amt}; 

	
	//잔액을 위한 카운터

		
	transform_number($('.counter'), 200, 'fixed_width');


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
