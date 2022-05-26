package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;


public class EditTourViewModel {
    public TourItem tourItem;

    public EditTourViewModel() {
        this.tourItem = new TourItem();
    }

    public void updateTour(String currentName) {
        DAL.getInstance().tourDao().updateById(DAL.getInstance().tourDao().getTourItemIdByName(currentName), tourItem);
    }

    public boolean tourExists() {
        if (DAL.getInstance().tourDao().getTourItemIdByName(tourItem.getName()) > 0) {
            return true;
        }
        return false;
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

    public String getChoiceBoxValueByTransportType(TransportType transportType, Locale locale) {
        if (transportType.toString().equals("HIKE")) {
            if (locale.toString().equals("de")) { return "Wanderweg"; }
            if (locale.toString().equals("en")) { return "Hike"; }
        }

        if (transportType.toString().equals("BIKE")) {
            if (locale.toString().equals("de")) { return "Radweg"; }
            if (locale.toString().equals("en")) { return "Bike"; }
        }

        if (transportType.toString().equals("RUNNING")) {
            if (locale.toString().equals("de")) { return "Laufweg"; }
            if (locale.toString().equals("en")) { return "Running"; }
        }

        if (transportType.toString().equals("VACATION")) {
            if (locale.toString().equals("de")) { return "Urlaubstour"; }
            if (locale.toString().equals("en")) { return "Vacation"; }
        }
        return null;
    }
}
