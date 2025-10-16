package com.app.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.model.Task;
import com.app.util.DBConnection;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoViewServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		List<Task> alltasks = new ArrayList<>();
		try(Connection conn = DBConnection.getConnection())
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from tasks");
			while(rs.next())
			{
				int id = rs.getInt("id");
				String n = rs.getString("task");
				alltasks.add(new Task(id,n));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try {			
			req.setAttribute("alltasks", alltasks);
			req.getRequestDispatcher("index.jsp").forward(req,res);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}