package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.model.TransportType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
        //name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.setValue(name); }
    public StringProperty nameProperty() { return name; }

    public String getTourDescriptionDescriptionTag() { return tourDescriptionDescriptionTag.get(); }
    public void setTourDescriptionDescriptionTag() { this.tourDescriptionDescriptionTag.setValue(bundle.getString("TourDescription_DescriptionTag")); }
    public StringProperty TourDescriptionDescriptionTagProperty() { return tourDescriptionDescriptionTag; }

    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.setValue("\n\n" + description); }
    public StringProperty descriptionProperty() { return description; }

    public String getTourDescriptionStartTag() { return tourDescriptionStartTag.get(); }
    public void setTourDescriptionStartTag() { this.tourDescriptionStartTag.setValue(bundle.getString("TourDescription_StartTag")); }
    public StringProperty TourDescriptionStartTagProperty() { return tourDescriptionStartTag; }

    public String getStart() { return start.get(); }
    public void setStart(String start) { this.start.setValue(": " + start + "\n"); }
    public StringProperty startProperty() { return start; }

    public String getTourDescriptionDestinationTag() { return tourDescriptionDestinationTag.get(); }
    public void setTourDescriptionDestinationTag() { this.tourDescriptionDestinationTag.setValue(bundle.getString("TourDescription_DestinationTag")); }
    public StringProperty TourDescriptionDestinationTagProperty() { return tourDescriptionDestinationTag; }

    public String getDestination() { return destination.get(); }
    public void setDestination(String destination) { this.destination.setValue(": " + destination + "\n\n\n"); }
    public StringProperty destinationProperty() { return destination; }

    public String getTransportType() { return transportType.get(); }
    public void setTransportType(String transportType) { this.transportType.setValue("\n" + bundle.getString(transportType) + " "); }
    public StringProperty transportTypeProperty() { return transportType; }

    public String getDistance() { return distance.get(); }
    public void setDistance(Double distance) { this.distance.setValue("| " + distance.toString() + " km"); }
    public StringProperty distanceProperty() { return distance; }

    public String getDuration() { return duration.get(); }
    public void setDuration(Double duration) { this.duration.setValue("| " + duration.toString() + " min\n\n"); }
    public StringProperty durationProperty() { return duration; }
}
