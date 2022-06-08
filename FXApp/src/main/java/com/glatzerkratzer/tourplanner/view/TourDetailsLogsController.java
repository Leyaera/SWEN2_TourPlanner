package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.FXMLDependencyInjection;
import com.glatzerkratzer.tourplanner.model.TourLog;
import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsLogsViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.*;

public class TourDetailsLogsController {
    @FXML private LogSearchBarController logSearchBarController;

    @FXML public TableView<TourLog> tourLogTableView;

    @Getter
    private final TourDetailsLogsViewModel tourDetailsLogsViewModel;
    private Locale locale;

    public TourDetailsLogsController(TourDetailsLogsViewModel tourDetailsLogsViewModel, Locale locale) {
        this.tourDetailsLogsViewModel = tourDetailsLogsViewModel;
        this.locale = locale;
    }

    @FXML
    void initialize() {
        tourLogTableView.setItems(tourDetailsLogsViewModel.getObservableTourLogs());
        tourLogTableView.getSelectionModel().selectedItemProperty().addListener(tourDetailsLogsViewModel.getChangeListener());
    }

    public void onButtonAdd(ActionEvent actionEvent) throws IOException { startSecondaryStage("AddLog.fxml"); }

    public void onButtonRemove(ActionEvent actionEvent) {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";
        String AlertBox_HeaderText = "";

        if (locale.toString().equals("en")) {
            AlertBox_Title = "Delete log";
            AlertBox_ContentText = "Delete selected log?";
            AlertBox_HeaderText = "Confirmation";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Log löschen";
            AlertBox_ContentText = "Log wirklich löschen?";
            AlertBox_HeaderText = "Bestätigung";
        }

        Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION, AlertBox_ContentText);
        confirmationBox.setHeaderText(AlertBox_HeaderText);
        confirmationBox.setTitle(AlertBox_Title);

        Optional<ButtonType> response = confirmationBox.showAndWait();
        if (response.get() == ButtonType.OK) {
            tourDetailsLogsViewModel.deleteLog(tourLogTableView.getSelectionModel().getSelectedItem());
        }
    }

    public void onButtonEdit(ActionEvent actionEvent) throws IOException {
        if (tourLogTableView.getSelectionModel().getSelectedItem() == null) {
            String AlertBox_Title = "";
            String AlertBox_ContentText ="";
            String AlertBox_HeaderText = "";

            if (locale.toString().equals("en")) {
                AlertBox_Title = "Edit log";
                AlertBox_ContentText = "No log has been selected to edit.";
                AlertBox_HeaderText = "Warning";
            }

            if (locale.toString().equals("de")) {
                AlertBox_Title = "Log bearbeiten";
                AlertBox_ContentText = "Zum Bearbeiten bitte erst Log auswählen.";
                AlertBox_HeaderText = "Warnung";
            }

            Alert confirmationBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
            confirmationBox.setHeaderText(AlertBox_HeaderText);
            confirmationBox.setTitle(AlertBox_Title);
            confirmationBox.showAndWait();
            return;
        }
        startSecondaryStage("EditLog.fxml");
    }


    public void onButtonRefresh(ActionEvent actionEvent) {
        tourDetailsLogsViewModel.refreshLogsList();
    }

    public void startSecondaryStage(String location) throws IOException {
        String secondaryStageTitle = "";
        if (location.equals("AddLog.fxml")) {
            if (locale.toString().equals("en")) { secondaryStageTitle = "Add log"; }
            if (locale.toString().equals("de")) { secondaryStageTitle = "Tour hinzufügen"; }
        }
        if (location.equals("EditLog.fxml")) {
            if (locale.toString().equals("en")) { secondaryStageTitle = "Edit log"; }
            if (locale.toString().equals("de")) { secondaryStageTitle = "Log bearbeiten"; }
        }

        Stage secondaryStage = new Stage();
        FXMLDependencyInjection fxmlDependencyInjection = new FXMLDependencyInjection();
        Parent root = fxmlDependencyInjection.load(location, locale );  // Locale.GERMAN, Locale.ENGLISH

        TourLog selectedLog;

        if (location.equals("EditLog.fxml")) {
            EditLogController editLogController = ControllerFactory.getInstance().getEditLogController();
            selectedLog = tourLogTableView.getSelectionModel().getSelectedItem();
            if (selectedLog != null) { editLogController.initData(selectedLog); }
        }

        Scene scene = new Scene(root);
        secondaryStage.setScene(scene);
        secondaryStage.setTitle(secondaryStageTitle);
        secondaryStage.showAndWait();

        tourDetailsLogsViewModel.refreshLogsList();
        tourLogTableView.setItems(tourDetailsLogsViewModel.getObservableTourLogs());
        tourLogTableView.getSelectionModel().selectedItemProperty().addListener(tourDetailsLogsViewModel.getChangeListener());
        tourLogTableView.getSelectionModel().selectLast();
    }
}
