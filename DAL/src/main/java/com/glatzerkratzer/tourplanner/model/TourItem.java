package com.glatzerkratzer.tourplanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourItem implements Serializable {
    private int id;
    private String name;
    private String description;
    private String start;
    private String destination;
    private TransportType transportType;
    private double distance;
    private double duration; // estimated duration of route
    private String mapPath; // path to route image with the tour map

    @Override
    public String toString() {
       return name;
    }
}
