<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="UTF-8" />
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Demo결과페이지" name="pageTitle"/>
</jsp:include>

	<table class="table" id="tbl-dev">
		<tr>
			<th scope="col">이름</th>
			<td>${dev.devName }</td>
		</tr>
		<tr>
			<th scope="col">나이</th>
			<td>${dev.devAge }</td>
		</tr>
		<tr>
			<th scope="col">이메일</th>
			<td>${dev.devEmail }</td>
		</tr>
		<tr>
			<th scope="col">성별</th>
			<td>${dev.devGender=="M"?"남":"여" }</td>
		</tr>
		<tr>
			<th scope="col">개발언어</th>
			<td>
				<c:forEach items="${dev.devLang }" var="lang" varStatus="vs">
					${lang}${!vs.last?",":""}
				</c:forEach>
			</td>
		</tr>
	</table>
	
	<style>
	table#tbl-dev{
	margin:0 auto;
	width:50%
	}
	</style>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>