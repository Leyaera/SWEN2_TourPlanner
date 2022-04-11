package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.model.TourItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddTourViewModel {
    private TourItem tourItemModel;
    private volatile boolean isInitValue = false;
    private TourDetailsViewModel tourDetailsViewModel;

    public AddTourViewModel(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
        //name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }

    public AddTourViewModel() {
    }
}
