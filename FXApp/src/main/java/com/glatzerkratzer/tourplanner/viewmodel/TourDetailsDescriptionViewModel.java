package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.model.TransportType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Setter;

import java.util.Locale;
import java.util.ResourceBundle;

public class TourDetailsDescriptionViewModel {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty start = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty tourDescriptionStartTag = new SimpleStringProperty();
    private final StringProperty tourDescriptionDestinationTag = new SimpleStringProperty();
    private final StringProperty tourDescriptionDescriptionTag = new SimpleStringProperty();

    public ResourceBundle bundle;
    public Locale locale;


    public TourDetailsDescriptionViewModel() {
    }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.setValue(name); }
    public StringProperty nameProperty() { return name; }

    public StringProperty TourDescriptionDescriptionTagProperty() { return tourDescriptionDescriptionTag; }
    public String getDescription() { return description.get(); }
    public void setDescription(String description) {
        String descriptionTag = "";
        if (!description.isBlank()) {
            description = "\n\n" + description;
            descriptionTag = bundle.getString("TourDescription_DescriptionTag");

        }
        this.description.setValue(description);
        this.tourDescriptionDescriptionTag.setValue(descriptionTag);
    }
    public StringProperty descriptionProperty() { return description; }

    public StringProperty TourDescriptionStartTagProperty() { return tourDescriptionStartTag; }

    public String getStart() { return start.get(); }
    public void setStart(String start) {
        String tourStartTag = "";
        if (!start.isBlank()) {
            start = ": " + start + "\n";
            tourStartTag = bundle.getString("TourDescription_StartTag");
        }
        this.start.setValue(start);
        this.tourDescriptionStartTag.setValue(tourStartTag);
    }
    public StringProperty startProperty() { return start; }

    public StringProperty TourDescriptionDestinationTagProperty() { return tourDescriptionDestinationTag; }
    public String getDestination() { return destination.get(); }
    public void setDestination(String destination) {
        String tourDestinationTag = "";
        if (!destination.isBlank()) {
            destination = ": " + destination + "\n\n\n";
            tourDestinationTag = bundle.getString("TourDescription_DestinationTag");
        }
        this.destination.setValue(destination);
        this.tourDescriptionDestinationTag.setValue(tourDestinationTag);
    }
    public StringProperty destinationProperty() { return destination; }

    public String getTransportType() { return transportType.get(); }
    public void setTransportType(String transportType) {
        String transportTypeSetValue = "";
        if (!transportType.isBlank()) {
            transportTypeSetValue = "\n" + bundle.getString(transportType) + " ";
        }
        this.transportType.setValue(transportTypeSetValue); }
    public StringProperty transportTypeProperty() { return transportType; }

    public String getDistance() { return distance.get(); }
    public void setDistance(Double distance) {
        String tourDistance = "";
        if (distance >= 0) {
            tourDistance = "| " + distance + " km ";
        }
        this.distance.setValue(tourDistance);
    }
    public StringProperty distanceProperty() { return distance; }

    public String getDuration() { return duration.get(); }
    public void setDuration(String duration) {
        String tourDuration = "";
        if (!duration.equals("-1.0")) {
            tourDuration = "| " + duration + "\n\n";
        }
        this.duration.setValue(tourDuration);
    }
    public StringProperty durationProperty() { return duration; }
}
