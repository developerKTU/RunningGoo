<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>새글등록</title>
</head>
<body>
		<h1>글 등록</h1>		
		<hr>
		<!-- 1. 폼태그에 속성 추가  -->
		<form action="saveBoard.do"  method='post' enctype="multipart/form-data"> 
			<table border="1" cellpadding="0" cellspacing="0">
				<!-- 2. 각 항목에  name 맞추기 -->
				<tr>
					<td bgcolor="orange" width="70">제목</td>
					<td align="left"><input type="text" name="b_title" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">작성자</td>
					<td align="left"><input type="text" size="10" name="b_name" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">내용</td>
					<td align="left"><textarea cols="40" rows="10" name="b_content"></textarea></td>
				</tr>
				<!-- 추가항목 시작 -->
				<tr>
					<td bgcolor="orange">이메일</td>
					<td align="left"><input type="text" name="b_email" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">비밀번호</td>
					<td align="left"><input type="password" name="b_pwd" />
					</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">파일추가</td><td align="left">
					<input type="file" maxlength="60" size="40" name="file" ></td>
				</tr>
				<!-- name="file"은 BoardVO 안에 File file 선언한 이름과 동일해야함 -->
				<!-- 추가항목 끝 -->
				<tr>
					<td colspan="2" align="center"><input type="submit"	value=" 새글 등록 " /></td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="getBoardList.do">글 목록 가기</a>
</body>
</html>