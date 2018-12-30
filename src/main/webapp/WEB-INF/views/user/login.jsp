
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<head>


   

</head>

<body>
	<%@include file="../includes/header.jsp" %>
	
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">로그인</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="/user/login" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="id" name="id" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="passwd" type="password">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="login">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


<script src="/resources/assets/js/jquery.3.2.1.min.js"
	type="text/javascript"></script>
	
</body>
	<script>
	$(document).ready(function(){
		let error =  '<c:out value="${error}"/>';
		if(error.length>0){
			alert(error);	
		}
		
	})
	</script>

</html>
