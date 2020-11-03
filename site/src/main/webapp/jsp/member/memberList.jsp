<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.pagination li { display: inline-block;}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  border: 1px solid #ddd;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {background-color: #ddd;}

.pagination a:first-child {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}

.pagination a:last-child {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}
</style>
</head>
<body>
	<jsp:include page="../menu/mainMenu.jsp"></jsp:include>
	<hr>
	<div align="center">
		<div><h1>회원목록 정보</h1></div>
		<form name="searchFrm">
			<input type="hidden" name="p">
			<span>이름</span><input name="name">
			<span>권한</span><input name="author">
			<button>검색</button>
		</form>
		<div>
			<table border="1">
				<tr>
					<th width="100">아이디</th>
					<th width="100">이   름</th>
					<th width="200">주   소</th>
					<th width="150">전화번호</th>
					<th width="150">가입일자</th>
					<th width="100">권   한</th>
					<th width="100">사   진</th>
				</tr>
				<c:forEach var="member" items="${members}">
					<tr>
						<td>${member.id}</td>
						<td>${member.name}</td>
						<td>${member.address}</td>
						<td>${member.tel}</td>
						<td>${member.enterdate}</td>
						<td>${member.author}</td>
						<td><img src="${pageContext.request.contextPath}/images/${member.img}" width="20px" height="20px"></td>
					</tr>
				</c:forEach>
			</table>
			<script>
				function goPage(p) {
					//location.href="memberList.do?p="+p;
					searchFrm.p.value = p;
					searchFrm.submit();
				}
			</script>
			<my:paging paging="${paging}" jsfunc="goPage"/>
		</div>
		
	</div>
</body>
</html>