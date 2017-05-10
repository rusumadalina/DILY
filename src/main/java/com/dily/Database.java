package com.dily;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
    private static final String USER = "dily";
    private static final String PASSWORD = "dily";
    private static Connection connection = null;
    private Database() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }
    }
    public static Connection getConnection() {
        if (connection == null) {
            try{
                createConnection();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    //Implement the method createConnection()
    static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    //Implement the method closeConnection()
    static void closeConnection() throws SQLException {
        if(connection!=null)
            connection.close();
    }
    //Implement the method commit()
    static void commit() {
        try {
            connection.commit();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Implement the method rollback()
    static void rollback() {
        try {
            connection.rollback();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
