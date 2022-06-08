package com.glatzerkratzer.tourplanner.dal;

import com.glatzerkratzer.tourplanner.database.DatabaseService;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TourLog;
import com.glatzerkratzer.tourplanner.model.TransportType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourLogDao implements Dao<TourLog>{
    public TourLogDao() {
        try{
            Connection conn = DatabaseService.getDatabaseService().getConnection();
            Statement s = conn.createStatement();

            String createLogs = "CREATE TABLE IF NOT EXISTS logs(id SERIAL UNIQUE, tourId INTEGER NOT NULL, comment VARCHAR, difficulty INTEGER NOT NULL, duration VARCHAR, rating INTEGER NOT NULL, date_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY(id))";

            s.addBatch(createLogs);

            s.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TourLog> getAll(int tourId) {
        List<TourLog> tourLogs = new ArrayList<>();
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, tourid, comment, difficulty, duration, rating, date_time FROM logs WHERE tourid = ? ORDER BY id DESC;");
            preparedStatement.setInt(1, tourId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                tourLogs.add(new TourLog(
                        resultSet.getInt(1),                                // id
                        resultSet.getInt(2),                                // tourid
                        resultSet.getString(3),                             // comment
                        resultSet.getInt(4),                                // difficulty
                        resultSet.getString(5),                             // duration
                        resultSet.getInt(6),                                // rating
                        resultSet.getTimestamp(7)                           // date_time
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tourLogs;
    }

    public void getAllByTourId(int tourId) {

    }

    @Override
    public List<TourLog> getLatestEntries(int lastLogIdInCurrentList) {
        /* get id from last Entry in current tourLoglist
         * gets data base entry with id greater than said id
         * returns found tourLogs as list.
         */

        try {

            Connection connection = DatabaseService.getDatabaseService().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, tourid, comment, difficulty, duration, rating, date_trunc('second', date_time)  FROM logs WHERE id > ?");
            preparedStatement.setInt(1, lastLogIdInCurrentList);
            //Statement statement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery();

            List<TourLog> latestTourLogs = new ArrayList<>();
            while(resultSet.next()) {
                TourLog newTourLog = new TourLog(
                        resultSet.getInt(1),                                // id
                        resultSet.getInt(2),                                // tourid
                        resultSet.getString(3),                             // comment
                        resultSet.getInt(4),                                // difficulty
                        resultSet.getString(5),                             // duration
                        resultSet.getInt(6),                                // rating
                        resultSet.getTimestamp(7)                // date_time
                );
                latestTourLogs.add(newTourLog);
            }
            preparedStatement.close();
            connection.close();
            return latestTourLogs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getIdByName(String name) {
        return 0;
    }

    @Override
    public void add(TourLog tourLog) {
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO logs(tourid, comment, difficulty, duration, rating) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, tourLog.getTourId());
            preparedStatement.setString(2, tourLog.getComment());
            preparedStatement.setInt(3, tourLog.getDifficulty());
            preparedStatement.setString(4, tourLog.getDuration());
            preparedStatement.setInt(5, tourLog.getRating());

            int affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            if (affectedRows == 0) {
                System.out.println("affected Rows in update = 0");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(int tourLogId, TourLog tourLog) {
        if (tourLog.getId() > 0) {
            try {
                Connection connection = DatabaseService.getDatabaseService().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE logs SET comment = ?, difficulty = ?, duration = ?, rating = ? WHERE id = ?");
                preparedStatement.setString(1, tourLog.getComment());
                preparedStatement.setInt(2, tourLog.getDifficulty());
                preparedStatement.setString(3, tourLog.getDuration());
                preparedStatement.setInt(4, tourLog.getRating());
                preparedStatement.setInt(5, tourLogId);

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    @Override
    public void delete(TourLog tourLog) {
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM logs WHERE id = ?;");
            preparedStatement.setInt(1, tourLog.getId());
            int affectedRows = preparedStatement.executeUpdate();
            connection.close();

            if (affectedRows == 0) {
                System.out.println("affected Rows in delete = 0");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
