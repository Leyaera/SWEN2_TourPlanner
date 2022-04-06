package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsDescriptionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.Locale;

public class TourDetailsDescriptionController {
    @FXML public Text tourDescriptionName;
    @FXML public Text tourDescriptionTransportType;
    @FXML public Text tourDescriptionStart;
    @FXML public Text tourDescriptionDestination;
    @FXML public Text tourDescriptionDistance;
    @FXML public Text tourDescriptionDuration;
    @FXML public Text tourDescriptionDescription;

    private final TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel;
    private Locale locale;

    public TourDetailsDescriptionController(TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel, Locale locale) {
        this.tourDetailsDescriptionViewModel = tourDetailsDescriptionViewModel;
        this.locale = locale;
    }

    public TourDetailsDescriptionViewModel getTourDetailsDescriptionViewModel() {
        return tourDetailsDescriptionViewModel;
    }

    @FXML
    void initialize() {
        tourDescriptionName.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.nameProperty());
        tourDescriptionTransportType.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.transportTypeProperty());
        tourDescriptionStart.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.startProperty());
        tourDescriptionDestination.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.destinationProperty());
        tourDescriptionDistance.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.distanceProperty());
        tourDescriptionDuration.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.durationProperty());
        tourDescriptionDescription.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.descriptionProperty());
    }

}
