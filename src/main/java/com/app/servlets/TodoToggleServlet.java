package com.app.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.app.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoToggleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps1 = conn.prepareStatement("SELECT status FROM tasks WHERE id=?");
            ps1.setInt(1, id);
            ResultSet rs = ps1.executeQuery();
            boolean currentStatus = false;
            if (rs.next()) {
                currentStatus = rs.getBoolean("status");
            }
            boolean newStatus = !currentStatus;
            PreparedStatement ps2 = conn.prepareStatement("UPDATE tasks SET status=? WHERE id=?");
            ps2.setBoolean(1, newStatus);
            ps2.setInt(2, id);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        res.sendRedirect("viewTasks");
    }
}
