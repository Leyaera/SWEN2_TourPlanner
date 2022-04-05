package com.glatzerkratzer.tourplanner.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseService implements DatabaseServiceInterface {
    private static DatabaseService databaseService;

    private static String DB_URL = null;
    private static String USER = null;
    private static String PASS = null;

    private DatabaseService() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("DB/src/main/java/com/glatzerkratzer/tourplanner/database/DBconfig.properties");
            properties.load(fileInputStream);

            this.DB_URL = properties.getProperty("jdbc.url");
            this.USER = properties.getProperty("jdbc.user");
            this.PASS = properties.getProperty("jdbc.pass");

            System.out.println("DB connection successful!");
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
