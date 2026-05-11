<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績一覧</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                成績一覧
            </h2>

            <!-- 検索条件の表示 -->
            <div class="mb-3">
                <p>科目：${subject.subjectName}</p>
                <p>クラス：${classroom.className}</p>
                <p>年度：${year}</p>
            </div>

            <!-- 成績一覧テーブル -->
            <table class="table table-hover mt-3">
                <thead>
                    <tr>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>点数</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="score" items="${scoreList}">
                        <tr>
                            <td>${score.student.studentNo}</td>
                            <td>${score.student.studentName}</td>
                            <td>${score.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="mt-4">
                <a href="TestList.action" class="btn btn-secondary">検索画面に戻る</a>
            </div>

        </section>
    </c:param>
</c:import>
