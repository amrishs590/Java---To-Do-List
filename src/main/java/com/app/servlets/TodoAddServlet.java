package com.app.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.util.DBConnection;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoAddServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String t = req.getParameter("task");
		if(t!=null && !t.trim().isEmpty())
		{
			try(Connection conn = DBConnection.getConnection())
			{
				PreparedStatement ps = conn.prepareStatement("insert into tasks(task) values (?)");
				ps.setString(1,t);
				ps.executeUpdate();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Task Added : "+t);
		}	
		res.sendRedirect("viewTasks");
	}
}

