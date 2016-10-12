package com.infosupport.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Raymond Phua on 10-10-2016.
 */
public class Database {

    protected static Connection conn;
    private static String jdbcURL = "jdbc:oracle:thin:@localhost:1521/XE";
    private static String user = "phua";
    private static String password = "admin";

    protected Mapper mapper = new Mapper();

    protected static void getConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            conn = DriverManager.getConnection(jdbcURL, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
