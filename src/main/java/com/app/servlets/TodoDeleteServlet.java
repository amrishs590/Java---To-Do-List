package com.app.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.app.util.DBConnection;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoDeleteServlet extends HttpServlet 
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("Id number : " + id);
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement("delete from tasks where id=?");
			ps.setInt(1,id);
			ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		res.sendRedirect("viewTasks");		
	}
}