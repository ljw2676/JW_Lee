<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장소 목록</title>
</head>
<body>
<c:choose>
	<c:when test="${fn:length(list) > 0 }">
		<table border="1">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>주소</td>
						<td>인원</td>
						<td>비용</td>
						<td>기간</td>
						<td>신청</td>
						<td>취소</td>
					</tr>
			<c:forEach items="${list }" var="row">
					<tr>
						<td>${row.L_NO}번</td>
						<td><a href="#this" name="title">${row.L_SUBJECT}</a><input type="hidden" id="L_NO" value="${row.L_NO}"></td>
						<td>${row.L_ADDR}</td>
						<td>${row.L_ENABLE}명</td>
						<td>${row.L_PAYMENT}원</td>
						<td>${row.L_SDATE} ~ ${row.L_EDATE}</td>
						<!-- 장소 신청 버튼 -->
						<td><a href="#this" name="apply">신청</a><input type="hidden" id="L_NO" value="${row.L_NO}"></td>
						<td><a href="#this" name="cancel">취소</a><input type="hidden" id="L_NO" value="${row.L_NO}"></td>
					</tr>
			</c:forEach>
			
		</table>
	</c:when>
	<c:otherwise>
		등록된 장소가 없습니다.
	</c:otherwise>
</c:choose>

<br><br>

<a href="<c:url value='/admin/lendplace/Form'/>">글쓰기</a>

<form id="commonForm" name="commonForm"></form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/resources/js/common.js'/>" charset="utf-8"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("a[name='title']").on("click", function(e) { //제목 
				e.preventDefault();
				fn_selectLendplaceDetail($(this));
			});
			
			$("a[name='apply']").on("click", function(e) { //신청
				e.preventDefault();
				fn_applyLendplace($(this));
			});
			
			$("a[name='cancel']").on("click", function(e) { //신청
				e.preventDefault();
				fn_cancelLendplace($(this));
			});
		});
		
		
		function fn_selectLendplaceDetail(obj) {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/lendplace/detail' />");
			comSubmit.addParam("L_NO", obj.parent().find("#L_NO").val());
			comSubmit.submit();
		}
	
	      function fn_applyLendplace(obj){
	          var comSubmit = new ComSubmit();
	      	  comSubmit.setUrl("<c:url value='/admin/lendplace/Apply' />");
			  comSubmit.addParam("L_NO", obj.parent().find("#L_NO").val());
	          comSubmit.submit();
	      }
	      
	      function fn_cancelLendplace(obj){
	          var comSubmit = new ComSubmit();
	      	  comSubmit.setUrl("<c:url value='/admin/lendplace/Cancel' />");
			  comSubmit.addParam("L_NO", obj.parent().find("#L_NO").val());
	          comSubmit.submit();
	      }

</script>
</body>
</html>