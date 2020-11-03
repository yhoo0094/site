<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 입력화면</title>
</head>
<body>
	<jsp:include page="../menu/mainMenu.jsp"></jsp:include>
	<hr>
	<div align="center">
		<div><h1>회원가입</h1></div>
		<div>
			<form id="frm" name="frm" action="memberInsert.do" method="post"
			enctype="multipart/form-data">			
				<table border="1">
					<tr>
						<th width="150">아이디</th>
						<td width="500"><input type="text" id="id" name="id"></td>
					</tr>
					<tr>
						<th width="150">이   름</th>
						<td ><input type="text" id="name" name="name"></td>
					</tr>
					<tr>
						<th width="150">패스워드</th>
						<td><input type="password" id="password" name="password"></td>
					</tr>
					<tr>
						<th width="150">사진</th>
						<td><input type="file" id="img" name="img"></td>
					</tr>
					<tr>
						<th width="150">주    소</th>
						<td><input type="text" id="address" name="address" size="100"></td>
					</tr>
					<tr>
						<th width="150">전화번호</th>
						<td><input type="text" id="tel" name="tel"></td>
					</tr>
					<tr>
						<th width="150">가입일자</th>
						<td><input type="date" id="enterdate" name="enterdate"></td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<input type="submit" value="가입하기"></input>&nbsp;&nbsp;
								<input type="reset" value="취    소">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>