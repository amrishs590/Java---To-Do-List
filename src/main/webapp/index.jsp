<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f7f7f7;
    padding: 20px;
}

h1 {
    color: #333;
}

form {
    margin-bottom: 20px;
}

input[type="text"] {
    padding: 8px;
    width: 250px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    padding: 8px 12px;
    background-color: #007BFF;
    border: none;
    border-radius: 4px;
    color: white;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

ul {
    list-style-type: none;
    padding-left: 0;
}

li {
    background-color: #fff;
    margin-bottom: 10px;
    padding: 10px 15px;
    border-radius: 5px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0px 2px 5px rgba(0,0,0,0.1);
}

li form {
    margin: 0;
}
</style>
</head>
<body>
	<h1>Todo List</h1>
	<form method="post" action="addTask">
		<input type="text" name="task" placeholder="Enter a task to add" required />
		<button type="submit">Add</button>
	</form>
	<h1>Todo Tasks</h1>
	<ul>
	<%
		java.util.List<com.app.model.Task> alltask = (java.util.List<com.app.model.Task>) request.getAttribute("alltasks");
		if(alltask!=null && !alltask.isEmpty())
		{
			int cnt = 1;
			for(com.app.model.Task i:alltask)
			{
	%>
			<li>
				<span><%= cnt++%> - <%= i.getTaskName() %></span>
				<form method="post" action="editTask" style="display:inline">
					<input type="hidden" name="id" value="<%=i.getId()%>"/>
					<input type="text" name="newTask"  placeholder="Enter Task to edit"/>
					<button type="submit">Edit</button>
				</form>
				<form method="post" action="deleteTask" style="display:inline">
					<input type="hidden" name="id" value="<%=i.getId()%>"/>
					<button type="submit">Delete</button>
				</form>
			</li>
	<%
			}
		}else {
			out.println("No Task Listed");
		}
	%>
	</ul>
</body>
</html>
