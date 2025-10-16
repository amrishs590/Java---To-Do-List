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
    width: 200px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    padding: 6px 12px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    opacity: 0.9;
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

.task-info {
    display: flex;
    align-items: center;
    gap: 15px;
}

.status-btn {
    padding: 4px 10px;
    border-radius: 4px;
    color: white;
    cursor: pointer;
    font-size: 14px;
}

.status-notcompleted {
    background-color: red;
}

.status-completed {
    background-color: green;
}

.delete-btn {
    background-color: red;
    color: white;
}

.edit-btn,.Add-btn {
    background-color: #007BFF;
    color: white;
}
</style>
</head>
<body>
	<h1>Todo List</h1>
	
	<form method="post" action="addTask">
	    <input type="text" name="task" placeholder="Enter a task to add" required />
	    <button type="submit" class="Add-btn">Add</button>
	</form>
	
	<h1>Todo Tasks</h1>
	<ul>
		<%
		    java.util.List<com.app.model.Task> alltask = (java.util.List<com.app.model.Task>) request.getAttribute("alltasks");
		    if(alltask != null && !alltask.isEmpty()) {
		        int cnt = 1;
		        for(com.app.model.Task i : alltask) {
		            boolean completed = i.isCompleted(); 
		%>
		    <li>
		        <div class="task-info">
		            <span><%= cnt++ %> - <%= i.getTaskName() %></span>
		
		            <form method="post" action="toggleStatus" style="display:inline">
		                <input type="hidden" name="id" value="<%= i.getId() %>"/>
		                <button type="submit" class="status-btn <%= completed ? "status-completed" : "status-notcompleted" %>">
		                    <%= completed ? "Completed" : "Not Completed" %>
		                </button>
		            </form>
		
		            <form method="post" action="editTask" style="display:inline">
		                <input type="hidden" name="id" value="<%= i.getId() %>"/>
		                <input type="text" name="newTask" placeholder="Enter Task to edit"/>
		                <button type="submit" class="edit-btn">Edit</button>
		            </form>
		        </div>
		
		        <form method="post" action="deleteTask" style="display:inline">
		            <input type="hidden" name="id" value="<%= i.getId() %>"/>
		            <button type="submit" class="delete-btn">Delete</button>
		        </form>
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
