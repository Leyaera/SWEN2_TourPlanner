package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsLogsViewModel;
import javafx.fxml.FXML;

import java.util.Locale;

public class TourDetailsLogsController {

    private final TourDetailsLogsViewModel tourDetailsLogsViewModel;
    private Locale locale;

    private TourItem tourItem;

    public TourDetailsLogsController(TourDetailsLogsViewModel tourDetailsLogsViewModel, Locale locale) {
        this.locale = locale;
        this.tourDetailsLogsViewModel = tourDetailsLogsViewModel;
    }

    @FXML
    void initialize() {

    }
}
