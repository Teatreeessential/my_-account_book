<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap start -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> page title </title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.3.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<!-- bootstrap end -->
</head>
<body>
<html>
<head>
    <!--Load the AJAX API-->
    <script type="text/javascript" 
    src="https://www.gstatic.com/charts/loader.js">
    </script>
</head>

  <body>
    <!--pie chart가 실제로 그려질 부분-->
    <div id="chart_div"></div>
    
<script type="text/javascript">
      // 구글 오픈 API에서 차트 객체 로드 
      google.charts.load('current', {'packages':['corechart']});
      // Google Visualization API  로드시 callback 사용할 콜백 함수 설정.
      google.charts.setOnLoadCallback(drawChart);
      // 이 함수에서 데이터 설정 및 차트를 그린다.
      function drawChart() {
        // 구글 차트는 데이터 테이블이라는 객체로 차트의 데이터를 전달한다.
        var data = new google.visualization.DataTable();
        //열 설정 2개를 설정하고 데이터 타입, 열이름
        data.addColumn('string', 'ㅇㅇ');
        data.addColumn('number', 'ㅇㅇ');
        //행 추가 5개의 행을 추가한다. 
        //파이차트는  전체 합의 비율로 표시
        data.addRows([
          ['간식', 3],
          ['의류', 2],
          ['하하', 1],
          ['Zucchini', 1],
          ['Pepperoni', 3]
        ]);
        // chart 옵션 설정 범례, 가로 세로 
        var options = {'title':'당신의 월별 지출 수입금액',
                       'width':400,
                       'height':300};
        // 파이 차트 객체 생성 및 div 태그에 내용 전달
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        //차트 그리고 태그, 옵션
        chart.draw(data, options);
      }
</script>    
  </body>
</html>
</body>
</html>