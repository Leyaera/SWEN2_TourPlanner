package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;

public class AddTourViewModel {
    public TourItem tourItem;

    public AddTourViewModel() {
        this.tourItem = new TourItem();
    }

    public void addTour() {
        BL.getInstance().getDall().addNewTour(tourItem);
    }

    public boolean tourExists() {
        return BL.getInstance().getDall().tourExists(tourItem);
    }

    public ObservableList<String> getChoiceBoxItems(Locale locale) {
        String hike = "";
        String bike = "";
        String running = "";
        String vacation = "";

        if (locale.toString().equals("en")) {
            hike = "Hike";
            bike = "Bike";
            running = "Running";
            vacation = "Vacation";
        }

        if (locale.toString().equals("de")) {
            hike = "Wanderweg";
            bike = "Radweg";
            running = "Laufweg";
            vacation = "Urlaubstour";
        }

        ObservableList<String> observableTransportTypeList = FXCollections.observableArrayList();
        observableTransportTypeList.addAll(hike, bike, running, vacation);

        return observableTransportTypeList;
    }

    public TransportType getTransportTypeByChoiceBoxValue(String choiceBoxValue) {
        // Get transport Type by choiceBox Value
        TransportType transportType = TransportType.NOT_SET;
        if (choiceBoxValue.equals("Hike") || choiceBoxValue.equals("Wanderweg")) {
            transportType = TransportType.HIKE;
        }

        if (choiceBoxValue.equals("Bike") || choiceBoxValue.equals("Radweg")) {
            transportType = TransportType.BIKE;
        }

        if (choiceBoxValue.equals("Running") || choiceBoxValue.equals("Laufweg")) {
            transportType = TransportType.RUNNING;
        }

        if (choiceBoxValue.equals("Vacation") || choiceBoxValue.equals("Urlaubstour")) {
            transportType = TransportType.VACATION;
        }

        return transportType;
    }
}
