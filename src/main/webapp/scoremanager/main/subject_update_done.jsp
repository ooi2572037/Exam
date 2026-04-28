<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">科目変更完了</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma">変更完了</h2>
            <p>科目の情報を変更しました。</p>
            <a href="SubjectList.action" class="btn btn-secondary">科目一覧へ戻る</a>
        </section>
    </c:param>
</c:import>
