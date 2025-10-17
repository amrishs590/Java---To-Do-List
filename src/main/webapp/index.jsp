<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.app.model.Task" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
<style>
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f2f2f2;
    padding: 30px;
}

h1 {
    color: #333;
    margin-bottom: 20px;
}

form {
    margin-bottom: 20px;
}

input[type="text"] {
    padding: 8px 12px;
    width: 220px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 14px;
}

button {
    padding: 6px 14px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: 14px;
    font-weight: 500;
    transition: all 0.2s ease;
}

button:hover {
    opacity: 0.85;
    transform: scale(1.05);
}

ul {
    list-style-type: none;
    padding-left: 0;
    margin: 0;
}

li {
    background-color: #fff;
    margin-bottom: 12px;
    padding: 12px 18px;
    border-radius: 8px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0 3px 8px rgba(0,0,0,0.1);
}

.task-info {
    display: flex;
    align-items: center;
    gap: 12px;
}

.status-btn {
    padding: 5px 12px;
    border-radius: 6px;
    color: #fff;
    font-weight: 500;
    font-size: 13px;
    cursor: pointer;
    transition: all 0.2s ease;
}

.status-notcompleted {
    background-color: #e74c3c;
}

.status-completed {
    background-color: #27ae60;
}

.delete-btn {
    background-color: #c0392b;
    color: #fff;
    font-weight: 500;
    border-radius: 6px;
    padding: 5px 12px;
}

.edit-btn, .Add-btn {
    background-color: #2980b9;
    color: #fff;
    font-weight: 500;
    border-radius: 6px;
    padding: 5px 12px;
}

span {
    font-size: 15px;
    font-weight: 500;
    color: #333;
}

li {
    background-color: #fff;
    margin-bottom: 12px;
    padding: 12px 18px;
    border-radius: 8px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0 3px 8px rgba(0,0,0,0.1);
}

.task-name {
    font-size: 15px;
    font-weight: 500;
    color: #333;
}

.task-actions form {
    display: inline-block;
    margin-left: 8px;
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
            <button type="submit" class="status-btn <%= completed ? "status-completed" : "status-notcompleted" %>">
                <%= completed ? "Completed" : "Not Completed" %>
            </button>
        </form>

        <form method="post" action="editTask" style="display:inline">
            <input type="hidden" name="id" value="<%= i.getId() %>"/>
            <input type="text" name="newTask" placeholder="Edit Task"/>
            <button type="submit" class="edit-btn">Edit</button>
        </form>

        <form method="post" action="deleteTask" style="display:inline">
            <input type="hidden" name="id" value="<%= i.getId() %>"/>
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
