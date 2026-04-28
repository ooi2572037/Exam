<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2 class="h4 mb-3">科目登録完了</h2>

<p>以下の科目を登録しました。</p>

<ul>
    <li>学校：${subject.school.schoolName}</li>
    <li>科目コード：${subject.subjectCd}</li>
    <li>科目名：${subject.subjectName}</li>
</ul>

<a href="SubjectList.action" class="btn btn-secondary mt-3">科目一覧へ戻る</a>
