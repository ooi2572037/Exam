<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="container-fluid mt-3">
    <div class="bg-light p-3 mb-4 border-bottom">
        <h2 class="h5 mb-0 fw-bold text-dark">成績管理</h2>
    </div>

    <div class="px-3">
        <form action="TestList.action" method="get" class="row gx-3 gy-3 align-items-end">
            
            <div class="col-auto">
                <label class="form-label small fw-bold mb-1">入学年度</label>
                <select name="f1" class="form-select form-select-sm" style="width: 140px;">
                    <option value="">--------</option>
                    <c:forEach var="year" items="${entYearList}">
                        <option value="${year}" ${param.f1 == year ? 'selected' : ''}>${year}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-auto">
                <label class="form-label small fw-bold mb-1">クラス</label>
                <select name="f2" class="form-select form-select-sm" style="width: 120px;">
                    <option value="">--------</option>
                    <c:forEach var="cNum" items="${classList}">
                        <option value="${cNum}" ${param.f2 == cNum ? 'selected' : ''}>${cNum}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-auto">
                <label class="form-label small fw-bold mb-1">科目</label>
                <select name="f3" class="form-select form-select-sm" style="width: 300px;">
                    <option value="">--------------------------------</option>
                    <c:forEach var="sub" items="${subjectList}">
                        <option value="${sub.subjectCd}" ${param.f3 == sub.subjectCd ? 'selected' : ''}>
                            ${sub.subjectName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-auto">
                <label class="form-label small fw-bold mb-1">回数</label>
                <select name="f4" class="form-select form-select-sm" style="width: 100px;">
                    <option value="">--------</option>
                    <option value="1" ${param.f4 == '1' ? 'selected' : ''}>1</option>
                    <option value="2" ${param.f4 == '2' ? 'selected' : ''}>2</option>
                </select>
            </div>

            <div class="col-auto">
                <button type="submit" class="btn btn-secondary btn-sm px-4 shadow-sm">検索</button>
            </div>
        </form>
    </div>

    <hr class="mt-4 mx-3">
</div>