package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsDescriptionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class TourDetailsDescriptionController {
    @FXML public Text tourDescriptionName;
    @FXML public Text tourDescriptionTransportType;
    @FXML public Text tourDescriptionStart;
    @FXML public Text tourDescriptionDestination;
    @FXML public Text tourDescriptionDistance;
    @FXML public Text tourDescriptionDuration;
    @FXML public Text tourDescriptionDescription;

    private final TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel;

    public TourDetailsDescriptionController(TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel) {
        this.tourDetailsDescriptionViewModel = tourDetailsDescriptionViewModel;
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
