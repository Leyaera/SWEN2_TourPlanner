package com.glatzerkratzer.tourplanner.model;


import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class TourLog implements Serializable {
    private final SimpleStringProperty id = new SimpleStringProperty();
    private final SimpleStringProperty tourId = new SimpleStringProperty();
    private final SimpleStringProperty comment = new SimpleStringProperty();
    private final SimpleStringProperty difficulty = new SimpleStringProperty();
    private final SimpleStringProperty duration = new SimpleStringProperty();
    private final SimpleStringProperty rating = new SimpleStringProperty();
    private final SimpleStringProperty date_time = new SimpleStringProperty();

    public TourLog() {
        setId(0);
        setTourId(0);
        setComment("");
        setDifficulty(0);
        setDuration("");
        setRating(0);
        setDate_time("");
    }

    public TourLog(int id, int tourId, String comment, int difficulty, String duration, int rating, Timestamp date_time) {
        setId(id);
        setTourId(tourId);
        setComment(comment);
        setDifficulty(difficulty);
        setDuration(duration);
        setRating(rating);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setDate_time(sdf.format(date_time));
    }

    @Override
    public String toString() {
        return date_time.toString();
    }

    public int getId() {
        return Integer.parseInt(id.get());
    }

    public void setId(int id) {
        this.id.set(String.valueOf(id));
    }

    public int getTourId() {
        return Integer.parseInt(tourId.get());
    }

    public void setTourId(int tourId) {
        this.tourId.set(String.valueOf(tourId));
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public String getComment() {
        return this.comment.get();
    }

    public int getDifficulty() {
        return Integer.parseInt(difficulty.get());
    }

    public void setDifficulty(int difficulty) {
        this.difficulty.set(String.valueOf(difficulty));
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public String getDuration() {
        return this.duration.get();
    }

    public int getRating() {
        return Integer.parseInt(rating.get());
    }

    public void setRating(int rating) {
        this.rating.set(String.valueOf(rating));
    }

    public void setDate_time(String date_time) {
        this.date_time.set(date_time);
    }

    public String getDate_time() {
        return this.date_time.get();
    }
}
