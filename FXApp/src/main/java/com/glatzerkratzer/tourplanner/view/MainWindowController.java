package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.FXMLDependencyInjection;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.MainWindowViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainWindowController {
    // Controllers of included fxml-files are injected here
    // fx:id Attribute of <fx:include> tag + "Controller"
    // tutorial see https://riptutorial.com/javafx/example/7285/nested-controllers
    @FXML private SearchBarController searchBarController;    // injected controller of SearchBar.fxml
    @FXML private TourOverviewController tourOverviewController;    // injected controller of TourOverview.fxml
    @FXML private TourDetailsController tourDetailsController;    // injected controller of TourDetails.fxml

    private final MainWindowViewModel mainViewModel;
    private Locale locale;

    public MainWindowController(MainWindowViewModel mainViewModel, Locale locale) {
        this.mainViewModel = mainViewModel;
        this.locale = locale;
    }

    public MainWindowViewModel getMainViewModel() {
        return mainViewModel;
    }

    @FXML void initialize() {
    }

    public void onMenuFileQuitClicked(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onMenuHelpAboutClicked(ActionEvent actionEvent) {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";
        String AlertBox_HeaderText = "Info";

        if (locale.toString().equals("en")) {
            AlertBox_Title = "About TourPlanner";
            AlertBox_ContentText = "Semester project BIF4-SWEN2\nby Stefanie Glatzer & Carmen Kratzer\nSS 2022";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Über TourPlanner";
            AlertBox_ContentText = "Semesterprojekt BIF4-SWEN2\nvon Stefanie Glatzer & Carmen Kratzer\nSS 2022";
        }
        Alert aboutBox = new Alert(Alert.AlertType.INFORMATION, AlertBox_ContentText);
        aboutBox.setTitle(AlertBox_Title);
        aboutBox.setHeaderText(AlertBox_HeaderText);
        aboutBox.showAndWait();
    }

    public void onImportClicked(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        String fileChooserTitle = "";
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);

        if (locale.toString().equals("en")) { fileChooserTitle = "Select file"; }
        if (locale.toString().equals("de")) { fileChooserTitle = "Datei auswählen"; }
        fileChooser.setTitle(fileChooserTitle);
        File file =  fileChooser.showOpenDialog(null);

        if (file == null) {
            String AlertBox_Title = "";
            String AlertBox_ContentText ="";
            String AlertBox_HeaderText = "";

            if (locale.toString().equals("en")) {
                AlertBox_Title = "No file was selected";
                AlertBox_HeaderText = "Warning";
                AlertBox_ContentText = "No file was selected to import.";
            }

            if (locale.toString().equals("de")) {
                AlertBox_Title = "Keine Datei ausgewählt";
                AlertBox_HeaderText = "Warning";
                AlertBox_ContentText = "Es wurde keine Datei zum Import ausgewählt.";
            }
            Alert aboutBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
            aboutBox.setTitle(AlertBox_Title);
            aboutBox.setHeaderText(AlertBox_HeaderText);
            aboutBox.showAndWait();
            return;
        }

        List<TourItem> existingTourItems = mainViewModel.importFile(file);
        if (existingTourItems != null) {
            showTourExistsWarning(existingTourItems.get(0).getName());
        }
        tourOverviewController.getTourOverviewViewModel().refreshToursList();
    }

    public void showTourExistsWarning(String existingTourItemName) {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";
        String AlertBox_HeaderText = "";

        if (locale.toString().equals("en")) {
            AlertBox_Title = "Add tour failed";
            AlertBox_HeaderText = "Warning";
            AlertBox_ContentText = "Adding tour failed. Tour name \"" + existingTourItemName + "\" already exists.";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Tour hinzufügen Fehler";
            AlertBox_HeaderText = "Warnung";
            AlertBox_ContentText = "Tour wurde nicht hinzugefügt. Tour name \"" + existingTourItemName + "\" existiert bereits.";
        }
        Alert warningBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
        warningBox.setTitle(AlertBox_Title);
        warningBox.setHeaderText(AlertBox_HeaderText);
        warningBox.showAndWait();
    }
}
