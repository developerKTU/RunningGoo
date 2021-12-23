<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resources/css/runninggooMain.css">
<link rel="stylesheet" href="resources/css/navandfooter.css">
<!-- 	<link rel="stylesheet" href="resources/css/templatemo-style.css">
 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	type="text/javascript"></script>
<script src="resources/js/plugins.js"></script>
<script src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<title>런닝구 (test)</title>
</head>
<body>
	<!-- header -->
	<nav>
		<div class="logo">
			<a href="index.jsp">DO<em>GETHER</em></a>
		</div>
		<div class="menu-icon">
			<span></span>
		</div>
	</nav>

	<!-- DB에서 forEach 돌며 값 불러오기 -->
	<div class="col-12 rn_room_list_wrap">
			<div class="col-12 runninggoo_card_wrap">
				<div class="col-12 runninggoo_card_inner create mb30">
					<div class="col-12 rn_card_user_profile">
						<div class="col-0 rng_createRoom_title">
							<h1>런닝구 방만들기</h1>
						</div>
					</div>
					<div class="col-12 kakao_map">
						<div id="map" style="width:100%;height:350px;"></div>
						<button onclick="zoomIn()">지도레벨 - 1</button>
    					<button onclick="zoomOut()">지도레벨 + 1</button>
						<p class="rn_room_hashtag">#장소검색</p>
						<div>
            				<form onsubmit="searchPlaces(); return false;">
                				<input type="text" value="" id="keyword" size="30"> 
                				<button type="submit">검색하기</button> 
            				</form>
        				</div>
        				<ul id="placesList"></ul>
        				<div id="pagination"></div>
					</div>
					
					<div class="col-12 rn_card_check_box">
						
						<form action="rngInsert.do" method="post">
							<input id="getLat" name="locationLat" style="display:none">
							<input id="getLong" name="locationLong" style="display:none">
							<input id="getFullAddress" name="location" style="display:none">
							<p class="rn_room_hashtag">#만나는 날짜</p>
							<input type="datetime-local" id="meeting-time" name="meetingTime">
							<!-- <input type="datetime-local" id="meeting-time"
       						name="meetingTime" value="2018-06-12T19:30"
       						min="2018-06-07T00:00" max="2018-06-14 00:00"> -->
       						<div id="result"></div>
							<div class="col-12 rn_card_host_input_comment_box">
								<p class="rn_room_hashtag">#호스트 한마디</p>
								<textarea class="create_host_comment" name="hostComment" id="create_host_comment"></textarea>
							</div>
							<div class="col-4 rn_card_sports_event_choice">
								<p class="rn_room_hashtag">#스포츠 종목</p>
								<select id="sportsSelect" name="sportsType">
									<option value=9999>종목선택...</option>
									<option value=0>런닝</option>
									<option value=1>발야구</option>
									<option value=2>피구</option>
									<option value=3>테니스</option>
									<option value=4>배드민턴</option>
									<option value=5>프리스비</option>
								</select>
								<div id="selectRunningDistance">
									<p class="rn_room_hashtag">#런닝 뛸 거리</p>
									<span>1km</span>
  									<input type="range" id="runningDistanceInput" class="slider_range" name="runningDistance" min="1" max="10" value="1" step="1" 
  											oninput="document.getElementById('slider_value_view1').innerHTML=this.value+'km';">
  									<span>10km</span>
  									<font size=2 id="slider_value_view1">0</font>
  									
								</div>
								<div id="selectExerciseTime">
									<p class="rn_room_hashtag">#운동시간</p>
									<span>1시간</span>
  									<input type="range" id="sportsTimeInput" class="slider_range" name="sportsTime" min="1" max="4" value="1" step="1" 
  											oninput="document.getElementById('slider_value_view2').innerHTML=this.value+'시간';">
  									<span>4시간</span>
  									<font size=2 id="slider_value_view2">0</font>
								</div>
							</div>
							<div class="col-4 rn_card_sports_age_choice">
								<p class="rn_room_hashtag">#연령대</p>
								<span>20대</span>
  								<input type="range" id="maxMembers" class="slider_range" name="ageGeneration" min="20" max="50" value="20" step="10" 
  										oninput="document.getElementById('slider_value_view3').innerHTML=this.value+'대';">
  								<span>50대</span>
  								<font size=2 id="slider_value_view3">0</font>
							</div>
							<div>
								<p class="rn_room_hashtag">#최대 인원수</p>
								<!-- value가 30이면 20~30대, 40이면 20~40대, 50이면 20~50대 -->
								<span>2명</span>
  								<input type="range" id="maxMembers" class="slider_range" name="maxMembers" min="2" max="9" value="5" step="1" 
  										oninput="document.getElementById('slider_value_view4').innerHTML=this.value+'명';">
  								<span>9명</span>
  								<font size=2 id="slider_value_view4">0</font>
							</div>
							<div>
							<p class="rn_room_hashtag">#보유 포인트</p>
							<!-- <form action="viewMemberPoints.do" method="POST">
								<input type="text" value="10000" disabled> Points
								<input type="submit" value="포인트 조희">
							</form> -->
							<table>
								<tr>
									<td><div id="mbPoint"></div> Points</td>
									<td><div id="jeonsong">조회</div></td>
								</tr>
							</table>
						</div>
							<div class="col-12 roomCreateBtn">
								<input type="submit" id="roomCreate" value="roomCreate">
							</div>
						</form>
			
				</div>
			</div>
		</div>
</div>

	<!-- <!-- footer
	<footer id="footer">
		<div class="container-fluid">
			<div class="col-md-12">
				<p id="ff">Copyright &copy; 2018 Company Name | Designed by
					TemplateMo</p>
			</div>
		</div>
	</footer> -->
	
	<div class="rngForEachLength">${rnRoomCNT }</div>
	
	
	<script type="text/javascript"src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b002992ff5db3facd06fff2ffcf08711"></script>
	<script type="text/javascript" src="resources/js/services.js"></script>


	<script type="text/javascript" src="resources/js/kakaoMapCreate.js"></script>
	<script src="resources/js/RunningGoo.js"></script>
	
</body>
</html>