<%-- test_delete.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/base.jsp" />

<section>
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

    <form action="TestDeleteExec.action" method="post">
        <%-- 削除に必要な主キーを隠しフィールドで送る --%>
        <input type="hidden" name="studentNo" value="${test.studentNo}">
        <input type="hidden" name="subjectCd" value="${test.subjectCd}">
        <input type="hidden" name="no" value="${test.no}">

        <div class="mt-3">
            <button type="submit" class="btn btn-danger">削除を実行する</button>
            <a href="TestList.action" class="btn btn-secondary">キャンセル</a>
        </div>
    </form>
</section>