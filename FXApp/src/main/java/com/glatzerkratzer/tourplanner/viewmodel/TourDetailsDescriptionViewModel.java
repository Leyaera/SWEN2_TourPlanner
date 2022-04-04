package com.glatzerkratzer.tourplanner.viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourDetailsDescriptionViewModel {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty start = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();

    public TourDetailsDescriptionViewModel() {
        //name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.setValue(name + "\n"); }
    public StringProperty nameProperty() { return name; }

    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.setValue(description + "\n\n"); }
    public StringProperty descriptionProperty() { return description; }

    public String getStart() { return start.get(); }
    public void setStart(String start) { this.start.setValue(start + "\n"); }
    public StringProperty startProperty() { return start; }

    public String getDestination() { return destination.get(); }
    public void setDestination(String destination) { this.destination.setValue(destination + "\n"); }
    public StringProperty destinationProperty() { return destination; }

    public String getTransportType() { return transportType.get(); }
    public void setTransportType(String transportType) { this.transportType.setValue(transportType + "\n\n"); }
    public StringProperty transportTypeProperty() { return transportType; }

    public String getDistance() { return distance.get(); }
    public void setDistance(Double distance) { this.distance.setValue(distance.toString() + "\n"); }
    public StringProperty distanceProperty() { return distance; }

    public String getDuration() { return duration.get(); }
    public void setDuration(Double duration) { this.duration.setValue(duration.toString() + "\n\n"); }
    public StringProperty durationProperty() { return duration; }
}
