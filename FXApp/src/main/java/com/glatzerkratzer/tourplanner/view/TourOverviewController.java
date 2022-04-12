package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.FXMLDependencyInjection;
import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.TourOverviewViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
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


    public void onButtonAdd(ActionEvent actionEvent) throws IOException {
        startSecondaryStage("AddTour.fxml");

        /*
        TourItem tourItem = new TourItem();
        tourOverviewViewModel.addNewTour(tourItem);
        tourItemList.getSelectionModel().selectLast();
         */
    }

    public void onButtonRemove(ActionEvent actionEvent) {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";
        String AlertBox_HeaderText = "";

        if (locale.toString().equals("en")) {
            AlertBox_Title = "Delete Tour";
            AlertBox_ContentText = "Delete selected Tour?";
            AlertBox_HeaderText = "Confirmation";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Tour löschen";
            AlertBox_ContentText = "Tour wirklich löschen?";
            AlertBox_HeaderText = "Bestätigung";
        }

        Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION, AlertBox_ContentText);
        confirmationBox.setHeaderText(AlertBox_HeaderText);
        confirmationBox.setTitle(AlertBox_Title);

        Optional<ButtonType> response = confirmationBox.showAndWait();
        if (response.get() == ButtonType.OK) {
            tourOverviewViewModel.deleteTour(tourItemList.getSelectionModel().getSelectedItem());
        }
    }

    public void onButtonEdit(ActionEvent actionEvent) {
        //tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
    }


    public void startSecondaryStage(String location) throws IOException {
        String secondaryStageTitle = "";
        if (location.equals("AddTour.fxml")) {
            if (locale.toString().equals("en")) { secondaryStageTitle = "Add Tour"; }
            if (locale.toString().equals("de")) { secondaryStageTitle = "Tour hinzufügen"; }
        }
        if (location.equals("EditTour.fxml")) {
            if (locale.toString().equals("en")) { secondaryStageTitle = "Edit Tour"; }
            if (locale.toString().equals("de")) { secondaryStageTitle = "Tour bearbeiten"; }
        }

        Stage secondaryStage = new Stage();
        Parent root = FXMLDependencyInjection.load(location, locale );  // Locale.GERMAN, Locale.ENGLISH
        Scene scene = new Scene(root);
        secondaryStage.setScene(scene);
        secondaryStage.setTitle(secondaryStageTitle);
        secondaryStage.showAndWait();

        tourOverviewViewModel.addNewTour();
        tourItemList.setItems(tourOverviewViewModel.getObservableTours());
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
    }

}
