<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
	<c:param name="title">科目削除完了</c:param>
 
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-success bg-opacity-10 py-2 px-4">削除完了</h2>
 
			<div class="mx-3 py-2">
				<p class="fw-bold text-success">科目の削除が完了しました。</p>
 
				<table class="table table-bordered w-50">
					<tr>
						<th class="bg-light">削除した科目コード</th>
						<td>${subject_cd}</td>
					</tr>
				</table>
 
				<div class="mt-3">
					<a href="SubjectList.action" class="btn btn-primary">科目一覧へ戻る</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>