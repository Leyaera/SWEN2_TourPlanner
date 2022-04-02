package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TourDetailsController {
    @FXML
    public TextField nameTextField;

    private final TourDetailsViewModel tourDetailsViewModel;

    public TourDetailsController(TourDetailsViewModel mediaDetailsViewModel) {
        this.tourDetailsViewModel = mediaDetailsViewModel;
    }

    public TourDetailsViewModel getMediaDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
    }
}
