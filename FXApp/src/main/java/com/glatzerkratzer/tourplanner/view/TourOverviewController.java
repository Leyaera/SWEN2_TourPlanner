package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.TourOverviewViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.util.Optional;

public class TourOverviewController {
    @FXML
    public ListView<TourItem> tourItemList;

    private final TourOverviewViewModel tourOverviewViewModel;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel) {
        this.tourOverviewViewModel = tourOverviewViewModel;
    }

    public TourOverviewViewModel getTourOverviewViewModel() {
        return tourOverviewViewModel;
    }

    @FXML
    void initialize() {
        //DEBUG
        /*
        for (var tour : tourOverviewViewModel.getObservableTours()) {
            System.out.println(tour.getName() + "\n");
        }
        */


        tourItemList.setItems(tourOverviewViewModel.getObservableTours());
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
    }

    public void onButtonAdd(ActionEvent actionEvent) {
        tourOverviewViewModel.addNewTour();
        tourItemList.getSelectionModel().selectLast();
    }

    public void onButtonRemove(ActionEvent actionEvent) {
        Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete selected Tour?");
        confirmationBox.setTitle("Delete Tour");

        Optional<ButtonType> response = confirmationBox.showAndWait();
        if (response.get() == ButtonType.OK) {
            tourOverviewViewModel.deleteTour(tourItemList.getSelectionModel().getSelectedItem());
        }
    }
}
