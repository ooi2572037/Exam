<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績削除確認</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="container mt-4" style="max-width: 700px;">
            <h2 class="text-danger">成績削除確認</h2>
            <p class="mb-4">以下の成績データを削除します。よろしいですか？</p>

            <table class="table">
                <tr>
                    <th>学生番号</th>
                    <td>${test.studentNo}</td>
                </tr>
                <tr>
                    <th>回数</th>
                    <td>${test.no}回目</td>
                </tr>
                <tr>
                    <th>点数</th>
                    <td>${test.point}点</td>
                </tr>
            </table>

            <form action="TestDeleteExecute.action" method="post">
                <input type="hidden" name="studentNo" value="${test.studentNo}">
                <input type="hidden" name="subjectCd" value="${test.subjectCd}">
                <input type="hidden" name="no" value="${test.no}">

                <div class="mt-3">
                    <button type="submit" class="btn btn-danger">削除を実行する</button>
                    <a href="TestList.action" class="btn btn-secondary">キャンセル</a>
                </div>
            </form>

        </section>

    </c:param>
</c:import>
