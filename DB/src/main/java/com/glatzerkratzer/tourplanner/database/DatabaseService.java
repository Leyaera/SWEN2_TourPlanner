package com.glatzerkratzer.tourplanner.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseService implements DatabaseServiceInterface {
    private static DatabaseService databaseService;

    private static String DRIVER = null;
    private static String DB_URL = null;
    private static String USER = null;
    private static String PASS = null;

    private DatabaseService() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("D:\\FH\\04_Semester_SS2022\\SWEN2\\SWEN2_TourPlanner\\config.properties");
            properties.load(fileInputStream);
            this.DRIVER = properties.getProperty("jdbc.driverClassName");
            this.DB_URL = properties.getProperty("jdbc.url");
            this.USER = properties.getProperty("jdbc.user");
            this.PASS = properties.getProperty("jdbc.pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseService getDatabaseService() {
        if (databaseService == null) {
            databaseService = new DatabaseService();
        }
        return databaseService;
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
