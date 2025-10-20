<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.app.model.Task" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/index.css">
</head>
<body>
	<h1>Todo List</h1>
	
	<form method="post" action="addTask">
	    <input type="text" name="task" placeholder="Enter a Task to Add" required />
		<input type="hidden" name="csrfToken" value="<%= session.getAttribute("CSRF_TOKEN") %>">
	    <button type="submit" class="Add-btn">Add</button>
	</form>
	
	<h1>Todo Tasks</h1>
	<ul> 
		<%
		    List<Task> alltask = (List<Task>) request.getAttribute("alltasks");
		    if(alltask != null && !alltask.isEmpty()) {
		        int cnt = 1;
		        for(com.app.model.Task i : alltask) {
		            boolean completed = i.isCompleted(); 
		%>
		    <li>
    <span class="task-name"><%= cnt++ %> - <%= i.getTaskName() %></span>
    
    <div class="task-actions">
        <form method="post" action="toggleStatus" style="display:inline">
            <input type="hidden" name="id" value="<%= i.getId() %>"/>
        	<input type="hidden" name="csrfToken" value="<%= session.getAttribute("CSRF_TOKEN") %>">
            <button type="submit" class="status-btn <%= completed ? "status-completed" : "status-notcompleted" %>">
                <%= completed ? "Completed" : "Not Completed" %>
            </button>
        </form>

        <form method="post" action="editTask" style="display:inline">
            <input type="hidden" name="id" value="<%= i.getId() %>"/>
        	<input type="hidden" name="csrfToken" value="<%= session.getAttribute("CSRF_TOKEN") %>">
            <input type="text" name="newTask" placeholder="Edit Task"/>
            <button type="submit" class="edit-btn">Edit</button>
        </form>

        <form method="post" action="deleteTask" style="display:inline">
            <input type="hidden" name="id" value="<%= i.getId() %>"/>
        	<input type="hidden" name="csrfToken" value="<%= session.getAttribute("CSRF_TOKEN") %>">        	
            <button type="submit" class="delete-btn">Delete</button>
        </form>
    </div>
</li>

		<%
		        }
		    } else {
		        out.println("No Task Listed");
		    }
		%>
	</ul>
</body>
</html>
