package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Locale;

public class TourDetailsController {


    @FXML
    public TextArea tourDescriptionText;

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
        //tourDescriptionText.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
        //tourDescriptionTextDescription.textProperty().bindBidirectional(tourDetailsViewModel.descriptionProperty());
    }
}
