<%-- test_update.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- ヘッダーなどをインクルードしている場合はここに入れる --%>
<jsp:include page="/base.jsp" /> 

<section>
    <h2>成績変更</h2>
    
    <form action="TestUpdateExec.action" method="post">
        <%-- 更新を特定するための主キー（ユーザーには見せない） --%>
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
                    <%-- ここだけ入力可能にする --%>
                    <input type="number" name="point" value="${test.point}" min="0" max="100" required class="form-control">
                </td>
            </tr>
        </table>

        <div class="mt-3">
            <button type="submit" class="btn btn-primary">変更を保存する</button>
            <a href="TestList.action" class="btn btn-secondary">キャンセル</a>
        </div>
    </form>
</section>

<%-- フッターなどをインクルードしている場合はここに入れる --%>