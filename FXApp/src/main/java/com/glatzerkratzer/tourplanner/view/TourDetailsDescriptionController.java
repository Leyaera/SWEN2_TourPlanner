package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsDescriptionViewModel;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.Locale;
import java.util.ResourceBundle;

public class TourDetailsDescriptionController {
    @FXML public Text tourDescriptionName;
    @FXML public Text tourDescriptionTransportType;
    @FXML public Text tourDescriptionStart;
    @FXML public Text tourDescriptionDestination;
    @FXML public Text tourDescriptionDistance;
    @FXML public Text tourDescriptionDuration;
    @FXML public Text tourDescriptionDescription;
    @FXML public Text tourDescriptionStartTag;
    @FXML public Text tourDescriptionDestinationTag;
    @FXML public Text tourDescriptionDescriptionTag;

    private final TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel;

    public TourDetailsDescriptionController(TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel, Locale locale) {
        this.tourDetailsDescriptionViewModel = tourDetailsDescriptionViewModel;
        this.tourDetailsDescriptionViewModel.locale = locale;
        this.tourDetailsDescriptionViewModel.bundle = ResourceBundle.getBundle("com.glatzerkratzer.tourplanner.view." + "gui_strings", locale);

    }

    public TourDetailsDescriptionViewModel getTourDetailsDescriptionViewModel() {
        return tourDetailsDescriptionViewModel;
    }

    @FXML
    void initialize() {
        tourDescriptionStartTag.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.TourDescriptionStartTagProperty());
        tourDescriptionDestinationTag.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.TourDescriptionDestinationTagProperty());
        tourDescriptionDescriptionTag.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.TourDescriptionDescriptionTagProperty());
        tourDescriptionName.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.nameProperty());
        tourDescriptionTransportType.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.transportTypeProperty());
        tourDescriptionStart.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.startProperty());
        tourDescriptionDestination.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.destinationProperty());
        tourDescriptionDistance.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.distanceProperty());
        tourDescriptionDuration.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.durationProperty());
        tourDescriptionDescription.textProperty().bindBidirectional(tourDetailsDescriptionViewModel.descriptionProperty());
    }

}
