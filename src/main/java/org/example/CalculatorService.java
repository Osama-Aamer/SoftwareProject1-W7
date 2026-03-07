package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalculatorService {

    private static final String DB_NAME = "calculator_db";
    private static final String USER = "root";
    private static final String PASS = "password";

    // Load MariaDB driver once when class is loaded
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static String getDatabaseHost() {
        String host = System.getenv("DB_HOST");
        if (host == null || host.isEmpty()) host = "localhost";
        return host;
    }

    private static String getDatabaseUrl() {
        return "jdbc:mariadb://" + getDatabaseHost() + ":3306/" + DB_NAME
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    }


    public void saveResult(double num1, double num2, String operation, double result) {
        String dbUrl = getDatabaseUrl();
        String sql = "INSERT INTO calculations (num1, num2, operation, result) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(true);

            stmt.setDouble(1, num1);
            stmt.setDouble(2, num2);
            stmt.setString(3, operation);
            stmt.setDouble(4, result);

            stmt.executeUpdate();
            System.out.println("Saved to database!");

        } catch (SQLException e) {
            System.err.println(" Failed to save result to DB: " + dbUrl);
            e.printStackTrace();
        }
    }
}


