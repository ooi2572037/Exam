<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2 class="h4 mb-3">科目登録</h2>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>


<form action="SubjectRegistExecute.action" method="post" class="mt-4">

    <!-- 学校選択 -->
    <div class="mb-3">
        <label class="form-label">学校</label>
        <select name="schoolCd" class="form-select">
    <option value="">選択してください</option>
    <c:forEach var="s" items="${schoolList}">
        <option value="${s.schoolCd}">${s.schoolName}</option>
    </c:forEach>
</select>
        
    </div>

    <!-- 科目コード -->
    <div class="mb-3">
        <label class="form-label">科目コード</label>
        <input type="text" name="subjectCd" class="form-control" required maxlength="10">
    </div>

    <!-- 科目名 -->
    <div class="mb-3">
        <label class="form-label">科目名</label>
        <input type="text" name="subjectName" class="form-control" required maxlength="50">
    </div>

    <button type="submit" class="btn btn-primary">登録</button>
</form>
	<div class="mt-4">
		<a href="SubjectList.action" class="btn btn-secondary">メニューに戻る</a>
	</div>
