package com.glatzerkratzer.tourplanner.dal;

import com.glatzerkratzer.tourplanner.database.DatabaseService;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;

public class TourItemDao implements Dao<TourItem> {
    private List<TourItem> tourItems = new ArrayList<>();
    private int nextId = 1;

    public TourItemDao() {
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, description, start, destination, transportType FROM tours;");

            while(resultSet.next()) {
                tourItems.add(new TourItem(
                        resultSet.getInt(1),                                // id
                        resultSet.getString(2),                             // name
                        resultSet.getString(3),                             // description
                        resultSet.getString(4),                             // start
                        resultSet.getString(5),                             // destination
                        TransportType.valueOf(resultSet.getString(6)),      // transportType
                        0.0,                                                   // distance
                        0.0,                                                   // duration
                        "mapPath"                                              // mapPath
                ));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<TourItem> get(int id) {
        return Optional.ofNullable(tourItems.get(id));
    }

    @Override
    public List<TourItem> getAll() {
        return tourItems;
    }

    @Override
    public List<TourItem> getLatestEntries() {
        /* get id from last Entry in current tourItemlist
         * gets data base entry with id greater than said id
         * returns found tourItems as list.
         */

        List<TourItem> latestTourItems = new ArrayList<>();
        TourItem lastTourItemInCurrentList = tourItems.get(tourItems.size()-1);
        int lastTourId = lastTourItemInCurrentList.getId();
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, description, start, destination, transportType FROM tours WHERE id > ?");
            preparedStatement.setInt(1, lastTourId);
            //Statement statement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                TourItem newTourItem = new TourItem(
                        resultSet.getInt(1),                                // id
                        resultSet.getString(2),                             // name
                        resultSet.getString(3),                             // description
                        resultSet.getString(4),                             // start
                        resultSet.getString(5),                             // destination
                        TransportType.valueOf(resultSet.getString(6)),      // transportType
                        0.0,                                                   // distance
                        0.0,                                                   // duration
                        "mapPath"                                              // mapPath
                );
                tourItems.add(newTourItem);
                latestTourItems.add(newTourItem);
            }
            preparedStatement.close();
            connection.close();
            return latestTourItems;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getTourItemIdByName(String name) {
        int tourId = 0;
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM tours WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tourId = resultSet.getInt(1);
            }
            preparedStatement.close();
            connection.close();
            return tourId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public TourItem create() {
        var tour = new TourItem("New Tour " +  nextId);
        nextId++;
        tourItems.add(tour);
        return tour;
    }

    @Override
    public void add(TourItem tourItem) {
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tours(name, description, start, destination, transporttype) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setString(1, tourItem.getName());
            preparedStatement.setString(2, tourItem.getDescription());
            preparedStatement.setString(3, tourItem.getStart());
            preparedStatement.setString(4, tourItem.getDestination());
            preparedStatement.setString(5, tourItem.getTransportType().toString());

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
    public void update(int tourId, TourItem tourItem) {
        if (tourItem.getId() > 0) {
            try {
                Connection connection = DatabaseService.getDatabaseService().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tours SET (name, description, start, destination, transporttype) VALUES (?, ?, ?, ?, ?) WHERE id = ?");
                preparedStatement.setString(1, tourItem.getName());
                preparedStatement.setString(2, tourItem.getDescription());
                preparedStatement.setString(3, tourItem.getStart());
                preparedStatement.setString(4, tourItem.getDestination());
                preparedStatement.setString(5, tourItem.getTransportType().toString());
                preparedStatement.setInt(6, tourId);

                int affectedRows = preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

                if (affectedRows == 0) {
                    System.out.println("affected Rows in update = 0");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }

        // If Tour(ID) does not already exist --> add new Tour
        add(tourItem);
    }

    @Override
    public void delete(TourItem tourItem) {
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tours WHERE id = ?;");
            preparedStatement.setInt(1, tourItem.getId());
            int affectedRows = preparedStatement.executeUpdate();
            connection.close();

            if (affectedRows == 0) {
                System.out.println("affected Rows in delete = 0");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tourItems.remove(tourItem);
    }
}
