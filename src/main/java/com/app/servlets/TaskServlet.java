package com.app.servlets;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.print.DocFlavor.STRING;

import com.app.model.Task;
import com.app.util.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class TaskServlet extends HttpServlet {

   protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
   {
	   List<Task> t = new ArrayList<>();
	   try(Connection conn = DBConnection.getConnection())
	   {
		   Statement st = conn.createStatement();
		   ResultSet rs = st.executeQuery("select * from tasks");
		   while(rs.next())
		   {
			   int id = rs.getInt("id");
			   String ta = rs.getString("task");
			   boolean sta = rs.getBoolean("status");
			   t.add(new Task(id,ta,sta));			   
		   }
		   
	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   req.setAttribute("alltasks", t);
       req.getRequestDispatcher("index.jsp").forward(req, res);	
   	}

   protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
    {
    	String method = req.getParameter("_method");
    	if(method==null)
    		addTask(req);
    	else if(method.equalsIgnoreCase("PUT"))
    		editTask(req);
    	else if(method.equalsIgnoreCase("DELETE"))
    		deleteTask(req);
    	else if(method.equalsIgnoreCase("PATCH"))
    		toggleTask(req);    		
    	doGet(req, res);
    }
    
    protected void addTask(HttpServletRequest req)
    {
    	String task = req.getParameter("task");
    	if(task!=null && !task.trim().isEmpty())
    	{
    		try(Connection connection = DBConnection.getConnection())
    		{
    			PreparedStatement ps = connection.prepareStatement("insert into tasks (task) values (?)");
    			ps.setString(1, task);
    			ps.executeUpdate();
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		System.out.println("Task Added : "+task);
    	}
    }
    
    protected void editTask(HttpServletRequest req)
    {
    	int id = Integer.parseInt(req.getParameter("id"));
    	String name = req.getParameter("newTask");
    	if(name!=null && !name.trim().isEmpty())
    	{
    		try(Connection conn = DBConnection.getConnection())
    		{
    			PreparedStatement ps = conn.prepareStatement("update tasks set task = (?) where id=(?)");
    			ps.setString(1, name);
    			ps.setInt(2, id);
    			ps.executeUpdate();
    			
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    }
    
    protected void deleteTask(HttpServletRequest req)
    {
    	int id = Integer.parseInt(req.getParameter("id"));
    	try(Connection conn = DBConnection.getConnection()) {
    		PreparedStatement preparedStatement = conn.prepareStatement("delete from tasks where id=(?)");
    		preparedStatement.setInt(1, id);
    		preparedStatement.executeUpdate();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
  
    protected void toggleTask(HttpServletRequest req)
    {
    	int id = Integer.parseInt(req.getParameter("id"));	
    	try(Connection connection = DBConnection.getConnection())
    	{
    		PreparedStatement ps = connection.prepareStatement("select status from tasks where id=(?)");
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		boolean curr = false;
    		if(rs.next())
    		{
    			curr = rs.getBoolean("status");
    		}
    		PreparedStatement ps2 = connection.prepareStatement("update tasks set status=(?) where id=(?)");
    		ps2.setBoolean(1, !curr);
    		ps2.setInt(2, id);
    		ps2.executeUpdate();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}
