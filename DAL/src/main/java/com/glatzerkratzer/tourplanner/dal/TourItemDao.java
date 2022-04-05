package com.glatzerkratzer.tourplanner.dal;

import com.glatzerkratzer.tourplanner.database.DatabaseService;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

public class TourItemDao implements Dao<TourItem> {
    private List<TourItem> tourItems = new ArrayList<>();
    private int nextId = 1;

    public TourItemDao() {
        // some test data

        tourItems.add(new TourItem(nextId++, "Schneeberg Plateauwanderung", "bis zum Gipfel des Schneebergs auf die Fischerhuette und wieder retour zum Bergbahnhof", "Bergbahnhof", "Bergbahnhof", TransportType.HIKE, 7.34, 120.00, "http://pathtoimageofroute"));
        tourItems.add(new TourItem(nextId++, "Schneeberg Paradies der Blicke", "leichter Gehweg", "Bergbahnhof", "Bergbahnhof", TransportType.HIKE, 3.14, 60.00, "http://pathtoimageofroute"));
        tourItems.add(new TourItem(nextId++, "Schneeberg Bahnwanderung", "entlang der der Schneebergbahn", "Bergbahnhof", "Puchberg", TransportType.HIKE, 10.2, 180.00, "http://pathtoimageofroute"));

        /*
        try {
            Connection connection = DatabaseService.getDatabaseService().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, description, start, destination, transportType, distance, duration, mapPath FROM tours;");

            while(resultSet.next()) {
                tourItems.add(new TourItem(
                        resultSet.getInt(1),                                // id
                        resultSet.getString(2),                             // name
                        resultSet.getString(3),                             // description
                        resultSet.getString(4),                             // start
                        resultSet.getString(5),                             // destination
                        TransportType.valueOf(resultSet.getString(6)),      // transportType
                        resultSet.getDouble(7),                             // distance
                        resultSet.getDouble(8),                             // duration
                        resultSet.getString(9)                              // mapPath

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

         */

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
        tourItem.setDistance(Double.parseDouble(params.get(6).toString()));
        tourItem.setDuration(Double.parseDouble(params.get(7).toString()));
        tourItem.setMapPath((params.get(3)==null)?"":params.get(8).toString());
    }

    @Override
    public void delete(TourItem tourItem) {
        tourItems.remove(tourItem);
    }
}
