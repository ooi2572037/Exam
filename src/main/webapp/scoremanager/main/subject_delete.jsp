<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
	<c:param name="title">科目削除確認</c:param>
 
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4">科目削除</h2>
 
			<div class="mx-3 py-2">
 
				<p class="text-danger fw-bold">以下の科目を削除します。よろしいですか？</p>
 
				<table class="table table-bordered w-50">
					<tr>
						<th class="bg-light">科目コード</th>
						<td>${subject.subjectCd}</td>
					</tr>
					<tr>
						<th class="bg-light">科目名</th>
						<td>${subject.subjectName}</td>
					</tr>
				</table>
 
				<!-- 削除実行 -->
				<form action="SubjectDeleteExecute.action" method="post">
					<!-- 主キーを送る -->
					<input type="hidden" name="subject_cd" value="${subject.subjectCd}" />
 
					<button class="btn btn-danger" type="submit">削除する</button>
					<a href="SubjectList.action" class="btn btn-outline-secondary ms-2">キャンセル</a>
				</form>
 
			</div>
		</section>
	</c:param>
</c:import>