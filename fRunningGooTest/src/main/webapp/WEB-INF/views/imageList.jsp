<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지리스트</title>
</head>
<body>

	<!-- 
	1. 이미지 파일 첨부를 한 레코드를 5개 정도만 입력 
	2. 이 페이지에 3열로 이미지들만 출력
	3. 해당 이미지를 선택하여 레코드 상세보기
	4. 각 조에서 한 분의 imageList.jsp 	
	-->
	<table>
		<tr><td>이미지들</td></tr>
		<tr>
		<c:set var="cnt" value="1"/>
		<c:forEach items="${boardList }" var="board">
			<c:if test="${board.b_realfname != null}">
				<td>
					<a href='getBoard.do?b_id=${board.b_id}'><img src="resources/upload/${board.b_realfname}" width="200px"></a>
				</td>
				<c:if test="${cnt % 3 == 0 }">
					<tr/>
				</c:if>
				<c:set var="cnt" value="${cnt+1}"></c:set>
			</c:if>
		</c:forEach>
		</tr>
	</table>

</body>
</html>