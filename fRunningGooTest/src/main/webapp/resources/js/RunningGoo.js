
// 날짜 데이터 값 받아오기
$("#meeting-time").change(function(){
	var dateString = $("#meeting-time").val()
	console.log(dateString);
});

$('#selectRunningDistance').hide();
	$('#selectExerciseTime').hide();

// 스포츠 타입 값 받아오기
$("#sportsSelect").change(function(){

	
	// 스포츠 종목 값 0~5 값 불러오기
	var sportsType = $("#sportsSelect").val();
	console.log(sportsType);
	
	// 러닝타임 값에 따라 입력 폼 생기게 하는 JQuery
	if(sportsType==0){
		$('#selectRunningDistance').show();
		$('#selectExerciseTime').hide();
	}else if(sportsType==9999){
		$('#selectRunningDistance').hide();
		$('#selectExerciseTime').hide();
	}else{
		$('#selectRunningDistance').hide();
		$('#selectExerciseTime').show();
	}
	
});



// 멤버의 포인트를 불러오는 AJAX
$('#jeonsong').click(function(){
	$.ajax({
		url : 'viewMemberPoints.do',
		// 세션으로 얻어온 memberID값을 보내야한다.
		type : 'get',
		dataType : 'text',
		success : function(result){
			$('#mbPoint').html(result);
			alert("보유 포인트 조회완료");	
		},
		error : function(err){
			alert("전송실패");
		}
	}); // end-of-ajax
})


// 슬라이더 인풋 태그들의 실시간 값 표시
function ShowSliderValue(sVal)
{
	var obValueView = document.getElementById("slider_value_view");
	obValueView.innerHTML = sVal
}

var RangeSlider = function(){
	var range = $('.slider_range');
    
	range.on('input', function(){		
		ShowSliderValue(this.value);
	});
};

RangeSlider();


