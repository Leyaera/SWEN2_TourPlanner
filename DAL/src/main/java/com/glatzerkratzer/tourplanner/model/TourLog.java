package com.glatzerkratzer.tourplanner.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TourLog {
    private int id;
    private int tourid;
    private String comment;
    private int difficulty;
    private String duration;
    private int rating;
    private String date_time;

    public TourLog() {
        this.id = 0;
        this.tourid = 0;
        this.comment = "";
        this.difficulty = 0;
        this.duration = "";
        this.rating = 0;
        this.date_time = "";
    }

    @Override
    public String toString() {
        return date_time.toString();
    }
}
