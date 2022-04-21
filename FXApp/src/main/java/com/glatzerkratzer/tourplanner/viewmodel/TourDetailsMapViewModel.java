package com.glatzerkratzer.tourplanner.viewmodel;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;
import java.util.ResourceBundle;

public class TourDetailsMapViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty start = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty tourMapStartTag = new SimpleStringProperty();
    private final StringProperty tourMapDestinationTag = new SimpleStringProperty();
    private final ObjectProperty<Image> mapImage= new SimpleObjectProperty();


    public ResourceBundle bundle;
    public Locale locale;


    public String getName() { return name.get(); }
    public void setName(String name) { this.name.setValue(name); }
    public StringProperty nameProperty() { return name; }

    public StringProperty TourMapStartTagProperty() { return tourMapStartTag; }

    public String getStart() { return start.get(); }
    public void setStart(String start) {
        String tourStartTag = "";
        if (!start.isBlank()) {
            start = ": " + start + "\n";
            tourStartTag = bundle.getString("TourDescription_StartTag");
        }
        this.start.setValue(start);
        this.tourMapStartTag.setValue(tourStartTag);
    }
    public StringProperty startProperty() { return start; }

    public StringProperty TourMapDestinationTagProperty() { return tourMapDestinationTag; }
    public String getDestination() { return destination.get(); }
    public void setDestination(String destination) {
        String tourDestinationTag = "";
        if (!destination.isBlank()) {
            destination = ": " + destination + "\n\n\n";
            tourDestinationTag = bundle.getString("TourDescription_DestinationTag");
        }
        this.destination.setValue(destination);
        this.tourMapDestinationTag.setValue(tourDestinationTag);
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
            tourDuration = "| " + duration + " min\n\n";
        }
        this.duration.setValue(tourDuration);
    }
    public StringProperty durationProperty() { return duration; }

    public final Image getImage() { return mapImage.get(); }
    public final void setMapImage(String mapPath) {
        Image image = new Image(mapPath);
        this.mapImage.set(image);
    }
    public final ObjectProperty<Image> imageProperty() { return mapImage; }

    /*
    public String getMapPath() { return mapPath.get(); }
    public void setMapPath(String mapPath) { this.mapPath.setValue(mapPath); }
    public StringProperty mapPathProperty() { return mapPath; }
     */


}
