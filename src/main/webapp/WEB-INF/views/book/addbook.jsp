<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"  %>
<body>
<div class="container">
	<sec:authorize access="hasRole('ROLE_MEMBER') and isAuthenticated()">
	<sec:authentication var="memberName" property="principal.member.name" />
	<c:out value="${memberName}" />&nbsp;さん
	</sec:authorize> こんにちは！<br>
	<a href="${pageContext.request.contextPath}/logout/sessionInvalidate">ログアウト</a>
	<h3>書籍追加画面</h3>
	
	<form:form modelAttribute="registerBookForm" action="${pageContext.request.contextPath}/book/insert" enctype="multipart/form-data" method = "post">
	<div class="span8">
		<div class="row">
			<table class="table table-striped">
			  <tr>
			    <th>書籍名</th>
			    <td><form:input path="name" placeholder="書籍名"/>
			    <form:errors path="name" cssStyle="color:red"/></td>
			  </tr>
			  <tr>
			  	<th>著者</th>
			    <td><form:input path="author" placeholder="著者"/>
			    <form:errors path="author" cssStyle="color:red"/></td>
			  </tr>
			  <tr>
			    <th>出版社</th>
			    <td><form:input path="publisher" placeholder="出版社"/>
			    <form:errors path="publisher" cssStyle="color:red"/></td>
			  </tr>
			  <tr>
			  	<th>価格</th>
			    <td><form:input path="price" placeholder="価格"/>
			    <form:errors path="price" cssStyle="color:red"/></td>
			  </tr>
			  <tr>
			    <th>ISBNコード</th>
			    <td><form:input path="isbncode" placeholder="ISBNコード"/>
				<form:errors path="isbncode" cssStyle="color:red"/></td>
			  </tr>
			  <tr>
			    <th>発売日</th>
			    <td><form:input path="saledate" placeholder="発売日"/>
				<form:errors path="saledate" cssStyle="color:red"/></td>
			  </tr>
			  <tr>
			    <th>説明</th>
			    <td><form:input path="explanation" placeholder="説明"/>
			    <form:errors path="explanation" cssStyle="color:red"/></td>
			  </tr>
			  <tr>
			    <th>画像</th>
			    <td><input name="imageFile" type="file" accept="image/*" required/>
			  </tr>
			  <tr>
			  	<th>在庫数</th>
			    <td><form:input path="stock" placeholder="在庫"/>
			    <form:errors path="stock" cssStyle="color:red"/></td>
			  </tr>
			  <tr>
				<td></td>
				<td><input class="btn" type="submit" value="追加"></td>
			  </tr>
			</table>
			<form action="${pageContext.request.contextPath}/book/list">
				<input type="submit" value="一覧へ戻る"/>
			</form>		
		</div>
	</div>
	</form:form>

</div>
</body>
</html>