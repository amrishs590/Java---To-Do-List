package com.app.util;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                System.out.println("✅ Database connection successful!");
            } else {
                System.out.println("❌ Database connection failed!");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
