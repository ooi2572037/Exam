<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="container mt-5" style="max-width: 1000px;">
    
    <div class="d-flex justify-content-between align-items-end mb-3">
        <div>
            <h2 class="h4 mb-0 fw-bold">成績管理システム</h2>
            <small class="text-muted">全学生の成績一覧を表示しています</small>
        </div>
        <div>
            <a href="TestStudentList.action" class="btn btn-sm btn-outline-secondary">学生別参照</a>
            <a href="TestSubjectList.action" class="btn btn-sm btn-outline-secondary">科目別参照</a>
        </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-body p-0">
            
            <table class="table table-striped table-hover mb-0">
                <thead class="table-light">
                    <tr>
                        <th class="ps-4">学生番号</th>
                        <th>科目コード</th>
                        <th class="text-center">回数</th>
                        <th class="text-end">点数</th>
                        <th class="text-center">クラス</th>
                        <th class="text-center">アクション</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="t" items="${testList}">
                        <tr class="align-middle">
                            <td class="ps-4 fw-bold">${t.studentNo}</td>
                            <td><span class="badge bg-secondary-subtle text-dark border">${t.subjectCd}</span></td>
                            <td class="text-center">${t.no}回</td>
                            <td class="text-end">
                                <span class="h6 mb-0 fw-bold ${t.point < 60 ? 'text-danger' : ''}">${t.point}</span>
                                <small class="text-muted">点</small>
                            </td>
                            <td class="text-center">${t.classNum}</td>
                            <td class="text-center">
                               
                                <a href="TestUpdate.action?studentNo=${t.studentNo}&subjectCd=${t.subjectCd}&no=${t.no}" 
                                   class="text-decoration-none me-2">変更</a>
                                <a href="TestDelete.action?studentNo=${t.studentNo}&subjectCd=${t.subjectCd}&no=${t.no}" 
                                   class="text-danger text-decoration-none"
                                   onclick="return confirm('削除しますか？')">削除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>