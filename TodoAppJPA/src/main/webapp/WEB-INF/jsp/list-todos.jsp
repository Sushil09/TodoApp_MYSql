<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	<fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}" />

	<div class="container">
	<h1><caption>Todo List</caption></h1>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
			<th>Description</th>
			<th>DueDate</th>
			<th>Is Finished?</th>
			<th>Update</th>
			<th> Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
			<tr>
			<td>${todo.description}</td>
			<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
			<td>${todo.done}</td>
			<td><a href="/updateTodo?id=${todo.id}" type="button" class="btn btn-success">Update</a></td>
			<td><a href="/delete-todos?id=${todo.id}" type="button" class="btn btn-warning">Delete</a></td>
			</tr>
			</c:forEach>	
		</tbody>
	</table>
	<a href="getTodo">Add ToDo</a>
	<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	</div>
<%@ include file="common/footer.jspf" %>	