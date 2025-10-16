package com.app.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.app.util.DBConnection;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoEditServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String newTask = req.getParameter("newTask");
		if(newTask!=null && !newTask.trim().isEmpty())
		{
			try(Connection conn = DBConnection.getConnection())
			{
				System.out.println("Task Updated : "+newTask);
				PreparedStatement ps = conn.prepareStatement("Update tasks set task=? where id=?");
				ps.setString(1,newTask);
				ps.setInt(2, id);
				ps.executeUpdate();			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		res.sendRedirect("viewTasks");
	}
}
