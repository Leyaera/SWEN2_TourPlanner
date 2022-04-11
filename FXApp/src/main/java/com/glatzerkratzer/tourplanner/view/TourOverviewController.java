package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.TourOverviewViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.util.Locale;
import java.util.Optional;

public class TourOverviewController {
    @FXML
    public ListView<TourItem> tourItemList;

    private final TourOverviewViewModel tourOverviewViewModel;
    private Locale locale;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel, Locale locale) {
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.locale = locale;
    }

    public TourOverviewViewModel getTourOverviewViewModel() {
        return tourOverviewViewModel;
    }


    @FXML
    void initialize() {
        tourItemList.setItems(tourOverviewViewModel.getObservableTours());
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
    }

    public void onButtonAdd(ActionEvent actionEvent) {
        tourOverviewViewModel.addNewTour();
        tourItemList.getSelectionModel().selectLast();
    }

    public void onButtonRemove(ActionEvent actionEvent) {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";

        System.out.println("locale = " + locale);
        if (locale.toString().equals("en")) {
            AlertBox_Title = "Delete Tour";
            AlertBox_ContentText = "Are you sure you want to delete selected Tour?";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Tour löschen";
            AlertBox_ContentText = "Tour wirklich löschen?";
        }

        Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION, AlertBox_ContentText);
        confirmationBox.setTitle(AlertBox_Title);

        Optional<ButtonType> response = confirmationBox.showAndWait();
        if (response.get() == ButtonType.OK) {
            tourOverviewViewModel.deleteTour(tourItemList.getSelectionModel().getSelectedItem());
        }
    }

    public void onButtonEdit(ActionEvent actionEvent) {
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
    }
}
