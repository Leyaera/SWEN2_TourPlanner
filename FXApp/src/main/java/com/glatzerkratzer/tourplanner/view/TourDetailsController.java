package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Locale;

public class TourDetailsController {

    @FXML private TourDetailsDescriptionController tourDetailsDescriptionController;
    @FXML private TourDetailsMapController tourDetailsMapController;
    @FXML private DownloadController downloadController;
    @FXML private TourDetailsLogsController tourDetailsLogsController;
    @FXML private MainWindowController mainWindowController;

    @FXML public TextArea tourDescriptionText;

    private final TourDetailsViewModel tourDetailsViewModel;
    private Locale locale;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel, Locale locale) {
        this.tourDetailsViewModel = tourDetailsViewModel;
        this.locale = locale;
    }

    public TourDetailsViewModel getTourDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
    }

}
