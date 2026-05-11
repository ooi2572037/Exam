<%-- 成績登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績登録</h2>
            
            <%-- 検索エリア --%>
            <form method="get" action="TestRegist.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">
                    <div class="col-5">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                        <div class="text-danger small">${errors.get("f1")}</div>
                    </div>
                    <div class="col-5">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary mt-4">検索</button>
                    </div>
                </div>
            </form>

            <%-- 登録エリア --%>
            <c:choose>
                <c:when test="${students.size() > 0}">
                    <form action="TestRegistExecute.action" method="post" class="px-4">
                        <div class="row mb-3 p-3 border bg-light rounded">
                            <div class="col-6">
                                <label class="form-label fw-bold">登録する科目</label>
                                <select class="form-select" name="subject_cd" required>
                                    <option value="">科目を選択してください</option>
                                    <%-- DBから取得した科目リストをループ --%>
                                    <c:forEach var="sub" items="${subjects}">
                                        <option value="${sub.subjectCd}">${sub.subjectName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-6">
                                <label class="form-label fw-bold">試験回数</label>
                                <select class="form-select" name="test_no" required>
                                    <c:forEach var="no" begin="1" end="10">
                                        <option value="${no}">${no}回目</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <table class="table table-hover mt-3">
                            <thead>
                                <tr>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th width="150px">点数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="st" items="${students}">
                                    <tr>
                                        <td>${st.studentNo}</td>
                                        <td>${st.studentName}</td>
                                        <td>
                                            <input type="number" name="point_${st.studentNo}" 
                                                   class="form-control" min="0" max="100" placeholder="0〜100">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="mt-4">
                            <button type="submit" class="btn btn-primary">登録して終了</button>
                            <a href="TestRegist.action" class="btn btn-outline-secondary ms-2">キャンセル</a>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="px-4 mt-3 text-muted">年度とクラスを選択して検索してください。</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>