<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.spring.member.model.vo.Member, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원정보" name="pageTitle"/>
</jsp:include>
<style>
div#update-container{width:400px; margin:0 auto; text-align:center;}
div#update-container input, div#update-container select {margin-bottom:10px;}
</style>
<div id="update-container">
	<form name="memberUpdateFrm" action="${pageContext.request.contextPath}/member/memberUpdateEnd.do" method="post">
		<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="memberId" id="memberId_" value="${m.memberId}" readonly required >
		<input type="text" class="form-control" placeholder="이름" name="memberName" id="memberName" value="${m.memberName}" required>
		<input type="number" class="form-control" placeholder="나이" name="age" id="age" value="${m.age}">
		<input type="email" class="form-control" placeholder="이메일" name="email" id="email" value="${m.email}" required>
		<input type="tel" class="form-control" placeholder="전화번호 (예:01012345678)" name="phone" id="phone" maxlength="11" value="${m.phone}" required>
		<input type="text" class="form-control" placeholder="주소" name="address" id="address" value="${m.address}">
		<select class="form-control" name="gender" required>
		  <option value="" disabled>성별</option>
		  <option value="M" ${m.gender=='M'?selected:''}>남</option>
		  <option value="F" ${m.gender=='F'?selected:''}>여</option>
		</select>
			<div class="form-check-inline form-check">
				취미 : &nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby0" value="운동" <c:forEach items="${m.hobby}" var="item"><c:if test="${item=='운동'}">checked</c:if></c:forEach>>
				<label for="hobby0" class="form-check-label" >운동</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby1" value="등산" <c:forEach items="${m.hobby}" var="item"><c:if test="${item=='등산'}">checked</c:if></c:forEach>>
				<label for="hobby1" class="form-check-label" >등산</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby2" value="독서" <c:forEach items="${m.hobby}" var="item"><c:if test="${item=='독서'}">checked</c:if></c:forEach>>
				<label for="hobby2" class="form-check-label" >독서</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby3" value="게임" <c:forEach items="${m.hobby}" var="item"><c:if test="${item=='게임'}">checked</c:if></c:forEach>>
				<label for="hobby3" class="form-check-label" >게임</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby4" value="여행" <c:forEach items="${m.hobby}" var="item"><c:if test="${item=='여행'}">checked</c:if></c:forEach>>
				<label for="hobby4" class="form-check-label" >여행</label>&nbsp;
			</div>
		<br />
		<input type="submit" class="btn btn-outline-success" value="수정" >&nbsp;
		<input type="reset" class="btn btn-outline-success" value="취소">
	</form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>