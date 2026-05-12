<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績検索</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <div class="container mt-5" style="max-width: 700px;">

            <h2 class="h4 fw-bold mb-4">成績検索</h2>

            <div class="card shadow-sm">
                <div class="card-body">

                    <!-- 検索フォーム -->
					<form action="TestListStudent.action" method="get" class="mb-4">
					
					    <h5 class="fw-bold mb-3">学生別検索</h5>
					
					    <div class="mb-3">
					        <label class="form-label">学生番号</label>
					        <input type="text" name="student_no" class="form-control" required placeholder="例：001">
					    </div>
					
					    <button type="submit" class="btn btn-primary w-100">
					        学生の成績一覧を表示
					    </button>
					</form>


                    <hr>

                    <!-- 科目別検索 -->
                    <form action="TestListSubject.action" method="get">

                        <h5 class="fw-bold mb-3">科目別検索</h5>

                        <div class="mb-3">
                            <label class="form-label">科目</label>
                            <select name="subject_cd" class="form-select" required>
                                <option value="">選択してください</option>
                                <c:forEach var="s" items="${subjectList}">
                                    <option value="${s.subjectCd}">${s.subjectName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-secondary w-100">
                            科目別成績一覧を表示
                        </button>
                    </form>

                </div>
            </div>

        </div>

    </c:param>
</c:import>
