<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">学生別成績一覧</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <div class="container mt-4" style="max-width: 800px;">

            <h2 class="h4 fw-bold mb-4">学生別成績一覧</h2>

            <p class="mb-3">
                学生番号：<strong>${studentNo}</strong>
            </p>

           <table class="table table-striped table-hover">
    <thead>
        <tr>
            <th>科目コード</th>
            <th>回数</th>
            <th>点数</th>
            <th>学生名</th>
        </tr>
    </thead>

    <tbody>
        <c:forEach var="t" items="${testList}">
            <tr>
                <td>${t.subjectCd}</td>
                <td>${t.no}回</td>
                <td>${t.point}点</td>
                <td>${t.studentName}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
           

            <div class="mt-4">
                <a href="TestSearch.action" class="btn btn-secondary">検索画面に戻る</a>
            </div>

        </div>

    </c:param>
</c:import>
