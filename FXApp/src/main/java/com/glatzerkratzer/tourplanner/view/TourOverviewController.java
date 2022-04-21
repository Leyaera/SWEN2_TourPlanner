package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.FXMLDependencyInjection;
import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.TourOverviewViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    @FXML private SearchBarController searchBarController;    // injected controller of SearchBar.fxml

    @FXML public ListView<TourItem> tourItemList;

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

    public void onButtonAdd(ActionEvent actionEvent) throws IOException { startSecondaryStage("AddTour.fxml"); }

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

    public void onButtonEdit(ActionEvent actionEvent) throws IOException {
        if (tourItemList.getSelectionModel().getSelectedItem() == null) {
            String AlertBox_Title = "";
            String AlertBox_ContentText ="";
            String AlertBox_HeaderText = "";

            if (locale.toString().equals("en")) {
                AlertBox_Title = "Edit Tour";
                AlertBox_ContentText = "No tour has been selected to edit.";
                AlertBox_HeaderText = "Warning";
            }

            if (locale.toString().equals("de")) {
                AlertBox_Title = "Tour bearbeiten";
                AlertBox_ContentText = "Zum Bearbeiten bitte erst Tour auswählen.";
                AlertBox_HeaderText = "Warnung";
            }

            Alert confirmationBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
            confirmationBox.setHeaderText(AlertBox_HeaderText);
            confirmationBox.setTitle(AlertBox_Title);
            confirmationBox.showAndWait();
            return;
        }
        startSecondaryStage("EditTour.fxml");
    }

    public void onButtonDuplicate(ActionEvent actionEvent) {
        TourItem selectedTour = tourItemList.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            if(tourOverviewViewModel.duplicateExists(selectedTour)) {
                showTourExistsWarning(selectedTour.getName());
                return;
            }
        }
        tourOverviewViewModel.addDuplicate(selectedTour);
        tourOverviewViewModel.refreshToursList();
    }

    public void onButtonRefresh(ActionEvent actionEvent) {
        tourOverviewViewModel.refreshToursList();
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
        FXMLDependencyInjection fxmlDependencyInjection = new FXMLDependencyInjection();
        Parent root = fxmlDependencyInjection.load(location, locale );  // Locale.GERMAN, Locale.ENGLISH

        TourItem selectedTour = null;
        if (location.equals("EditTour.fxml")) {
            EditTourController editTourController = fxmlDependencyInjection.getController();
            selectedTour = tourItemList.getSelectionModel().getSelectedItem();
            if (selectedTour != null) { editTourController.initData(selectedTour); }
        }

        Scene scene = new Scene(root);
        secondaryStage.setScene(scene);
        secondaryStage.setTitle(secondaryStageTitle);
        secondaryStage.showAndWait();

        tourOverviewViewModel.refreshToursList();
        tourItemList.setItems(tourOverviewViewModel.getObservableTours());
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
        tourItemList.getSelectionModel().selectLast();
    }

    public void showTourExistsWarning(String tourName) {
        tourName = tourName.replace("_copy", "");
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";
        String AlertBox_HeaderText = "";

        if (locale.toString().equals("en")) {
            AlertBox_Title = "Copy tour failed";
            AlertBox_HeaderText = "Warning";
            AlertBox_ContentText = "Copying tour failed. A Copy of " + tourName + " already exists.";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Tour kopieren Fehler";
            AlertBox_HeaderText = "Warnung";
            AlertBox_ContentText = "Es konnte keine Kopie angelegt werden. Eine Tour " + tourName + " existiert bereits.";
        }
        Alert warningBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
        warningBox.setTitle(AlertBox_Title);
        warningBox.setHeaderText(AlertBox_HeaderText);
        warningBox.showAndWait();
    }
}
