package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;

public class TourDetailsViewModel {
    private TourItem tourItemModel;
    private volatile boolean isInitValue = false;

    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty distance = new SimpleDoubleProperty();
    private final StringProperty plannedTime = new SimpleStringProperty();

    public TourDetailsViewModel() {
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getDistance() {
        return distance.get();
    }

    public DoubleProperty distanceProperty() {
        return distance;
    }

    public String getPlannedTime() {
        return plannedTime.get();
    }

    public StringProperty plannedTimeProperty() {
        return plannedTime;
    }

    public void setTourModel(TourItem tourItemModel) {
        isInitValue = true;
        if( tourItemModel ==null ) {
            // select the first in the list
            name.set("");
            distance.set(0.0);
            plannedTime.set("");
            return;
        }
        System.out.println("setTourModel name=" + tourItemModel.getName() + ", distance=" + tourItemModel.getDuration() + ", plannedTime=" + tourItemModel.getInformation());
        this.tourItemModel = tourItemModel;
        name.setValue( tourItemModel.getName() );
        distance.set( tourItemModel.getDuration() );
        plannedTime.set( tourItemModel.getInformation() );
        isInitValue = false;
    }

    private void updateTourModel() {
        if( !isInitValue )
            DAL.getInstance().tourDao().update(tourItemModel, Arrays.asList(tourItemModel.getId(), name.get(), distance.get(), plannedTime.get()));
    }


}
