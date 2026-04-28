<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- ここでヘッダーなどをインクルード（例：base.jspなど） --%>
<c:import url="../../common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma">科目情報変更</h2>
            <form action="SubjectUpdateExecute.action" method="post">
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <p>${subject.subjectCd}</p>
                    <%-- hiddenでActionにコードを渡す --%>
                    <input type="hidden" name="cd" value="${subject.subjectCd}">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="subject-name-input">科目名</label>
                    <input class="form-control" type="text" id="subject-name-input" 
                           name="name" value="${subject.subjectName}" placeholder="科目名を入力してください" required>
                </div>
                <button type="submit" class="btn btn-primary">変更</button>
            </form>
            <div class="mt-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>