package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsMapViewModel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lombok.Getter;

import java.util.Locale;
import java.util.ResourceBundle;

public class TourDetailsMapController {
    @FXML public Text tourMapName;
    @FXML public Text tourMapTransportType;
    @FXML public Text tourMapStart;
    @FXML public Text tourMapDestination;
    @FXML public Text tourMapDuration;
    @FXML public Text tourMapDistance;
    @FXML public Text tourMapStartTag;
    @FXML public Text tourMapDestinationTag;



    @FXML public ImageView tourMapImageView;

    @Getter
    private final TourDetailsMapViewModel tourDetailsMapViewModel;

    public TourDetailsMapController(TourDetailsMapViewModel tourDetailsMapViewModel, Locale locale) {
        this.tourDetailsMapViewModel = tourDetailsMapViewModel;
        this.tourDetailsMapViewModel.locale = locale;
        this.tourDetailsMapViewModel.bundle = ResourceBundle.getBundle("com.glatzerkratzer.tourplanner.view." + "gui_strings", locale);
    }

    public TourDetailsMapViewModel getTourDetailsDescriptionViewModel() {
        return tourDetailsMapViewModel;
    }

    @FXML
    void initialize() {
        tourMapStartTag.textProperty().bindBidirectional(tourDetailsMapViewModel.TourMapStartTagProperty());
        tourMapDestinationTag.textProperty().bindBidirectional(tourDetailsMapViewModel.TourMapDestinationTagProperty());
        tourMapName.textProperty().bindBidirectional(tourDetailsMapViewModel.nameProperty());
        tourMapTransportType.textProperty().bindBidirectional(tourDetailsMapViewModel.transportTypeProperty());
        tourMapStart.textProperty().bindBidirectional(tourDetailsMapViewModel.startProperty());
        tourMapDestination.textProperty().bindBidirectional(tourDetailsMapViewModel.destinationProperty());
        tourMapDuration.textProperty().bindBidirectional(tourDetailsMapViewModel.durationProperty());
        tourMapDistance.textProperty().bindBidirectional(tourDetailsMapViewModel.distanceProperty());
        tourMapImageView.imageProperty().bindBidirectional(tourDetailsMapViewModel.imageProperty());
    }
}
