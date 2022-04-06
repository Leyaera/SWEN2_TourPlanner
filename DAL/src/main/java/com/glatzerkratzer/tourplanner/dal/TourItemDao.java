package com.glatzerkratzer.tourplanner.dal;

import com.glatzerkratzer.tourplanner.database.DatabaseService;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;

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

            // DEBUG
            if (resultSet == null) {
                System.out.println("resultSet is null");
            }

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
    public TourItem create() {
        var tour = new TourItem(nextId, "New Tour " + nextId, "", "", "", TransportType.HIKE, 0, 0, "");
        tourItems.add(tour);
        nextId++;
        return tour;
    }

    @Override
    public void update(TourItem tourItem, List<?> params) {
        System.out.println(params);
        tourItem.setName(Objects.requireNonNull(params.get(1), "Name cannot be null").toString());
        tourItem.setDescription(Objects.requireNonNull(params.get(2), "Description cannot be null").toString());
        tourItem.setStart(Objects.requireNonNull(params.get(3), "Start cannot be null").toString());
        tourItem.setDestination(Objects.requireNonNull(params.get(4), "Destination cannot be null").toString());
        tourItem.setTransportType(TransportType.valueOf(Objects.requireNonNull(params.get(5), "TransportType cannot be null").toString()));
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
