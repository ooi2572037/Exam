<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績変更</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="container mt-4" style="max-width: 700px;">
            <h2 class="h4 fw-bold mb-4">成績変更</h2>

            <form action="TestUpdateExecute.action" method="post">

                <input type="hidden" name="studentNo" value="${test.studentNo}">
                <input type="hidden" name="subjectCd" value="${test.subjectCd}">
                <input type="hidden" name="no" value="${test.no}">

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
                        <td>
                            <input type="number" name="point" value="${test.point}"
                                   min="0" max="100" required class="form-control">
                        </td>
                    </tr>
                </table>

                <div class="mt-3">
                    <button type="submit" class="btn btn-primary">変更を保存する</button>
                    <a href="TestList.action" class="btn btn-secondary">キャンセル</a>
                </div>

            </form>
        </section>

    </c:param>
</c:import>
