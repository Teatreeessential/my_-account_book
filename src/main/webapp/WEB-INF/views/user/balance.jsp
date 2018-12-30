<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>page title</title>
</head>
<body>
	<%@include file="../includes/header.jsp"%>
	<div class="content">
		<div class="container-fluid">
			<div class="row">
				<c:forEach var='item' items='${top_three_income_map}'>
					<tr>
						<td>${item.key}</td>
						<td>${item.value}</td>
					</tr>
				</c:forEach>
				<c:forEach var="item" items='${balance_list}' varStatus='status'>
					<div class="col-md-4">
						<div class="card card-user">
							<div class="image">
								<a href="/user/transaction/${item.fintech_use_num}"><img
									src="${item.bank_code_tran}"
									alt="..."></a>
							</div>
							<div class="content">
								<a href="/user/transaction/${item.fintech_use_num}"><h4 class="title">${item.bank_name}</h4></a>
								<h5>
									<p class="category">계좌종류</p>
									${item.account_type}
								</h5>
								<h5>
									<p class="category">계좌이름</p>
									${item.product_name}
								</h5>
								<h5>
									<p class="category">계좌잔액</p>
									${item.balance_amt}
								</h5>
								<h5>
									<p class="category">사용가능금액</p>
									${item.available_amt}
								</h5>

							</div>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>
<

	</div>
	</div>

</body>


<script src="/resources/counter/counter.js"
	type="text/javascript"></script>

<script src="/resources/assets/js/jquery.3.2.1.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function(){
		transform_number($('.counter'), 200, 'fixed_width');
	})
</script>
</html>
