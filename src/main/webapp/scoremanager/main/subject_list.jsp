<%-- 科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		科目管理 - 得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
			
			<div class="my-3 text-end">
				<a href="SubjectCreate.action" class="btn btn-primary">新規登録</a>
			</div>

			<table class="table table-hover mt-3">
				<thead>
					<tr>
						<th>科目コード</th>
						<th>科目名</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="subject" items="${subjects}">
						<tr>
							<%-- Beanのプロパティ名に合わせて修正済み --%>
							<td>${subject.subjectCd}</td>
							<td>${subject.subjectName}</td>
							
							<td class="text-center">
								<a href="SubjectUpdate.action?cd=${subject.subjectCd}" class="btn btn-outline-success btn-sm">変更</a>
							</td>
							<td class="text-center">
								<a href="SubjectDelete.action?cd=${subject.subjectCd}" class="btn btn-outline-danger btn-sm">削除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="mt-4">
				<a href="Menu.action" class="btn btn-secondary">メニューに戻る</a>
			</div>
		</section>
	</c:param>
</c:import>