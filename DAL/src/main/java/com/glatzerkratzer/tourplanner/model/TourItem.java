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

    public TourItem(String name) {
        this.id = 0;
        this.name = name;
        this.description = "";
        this.start = "";
        this.destination = "";
        this.transportType = TransportType.NOT_SET;
        this.distance = 0.0;
        this.duration = 0.0;
        this.mapPath = "";
    }

    @Override
    public String toString() {
       return name;
    }
}
