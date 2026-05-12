<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">科目別成績一覧</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                科目別成績一覧
            </h2>

            <!-- 科目情報 -->
            <div class="mb-3">
                <p>科目コード：${subject.subjectCd}</p>
                <p>科目名：${subject.subjectName}</p>
            </div>

            <!-- 成績一覧テーブル -->
            <table class="table table-hover mt-3">
                <thead>
                    <tr>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th>回数</th>
                        <th>点数</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="t" items="${testList}">
                        <tr>
                            <td>${t.studentNo}</td>
                            <td>${t.studentName}</td>
                            <td>${t.classNum}</td>
                            <td>${t.no}</td>
                            <td>${t.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="mt-4">
                <a href="TestSearch.action" class="btn btn-secondary">検索画面に戻る</a>
            </div>

        </section>
    </c:param>
</c:import>
