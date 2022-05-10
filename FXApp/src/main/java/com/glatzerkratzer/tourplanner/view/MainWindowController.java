package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.FXMLDependencyInjection;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.mq.MapQuestService;
import com.glatzerkratzer.tourplanner.viewmodel.MainWindowViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainWindowController {
    // Controllers of included fxml-files are injected here
    // fx:id Attribute of <fx:include> tag + "Controller"
    // tutorial see https://riptutorial.com/javafx/example/7285/nested-controllers
    @FXML private TourOverviewController tourOverviewController;    // injected controller of TourOverview.fxml
    @FXML private TourDetailsController tourDetailsController;    // injected controller of TourDetails.fxml

    @FXML private MenuBar menu;

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

    public TourOverviewController getTourOverviewController()
    {
        return tourOverviewController;
    }

    public void onMenuFileAddClicked(ActionEvent actionEvent) throws IOException {
        tourOverviewController.onButtonAdd(actionEvent);
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

    public void onMenuFileQuitClicked(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onMenuEditEditClicked(ActionEvent actionEvent) throws IOException {
        tourOverviewController.onButtonEdit(actionEvent);
    }

    public void onMenuEditDuplicateClicked(ActionEvent actionEvent) {
        tourOverviewController.onButtonDuplicate(actionEvent);
    }

    public void onMenuEditDeleteClicked(ActionEvent actionEvent) throws IOException {
        tourOverviewController.onButtonRemove(actionEvent);
    }

    public void onMenuEditRefreshClicked(ActionEvent actionEvent) throws IOException {
        tourOverviewController.onButtonRefresh(actionEvent);
    }
    public void onMenuOptionsSettingsLanguageClicked(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) menu.getScene().getWindow();
        Locale switchedLocale = null;

        if (locale.toString().equals("de")) {
            switchedLocale = Locale.ENGLISH;
        }
        if (locale.toString().equals("en")) {
            switchedLocale = Locale.GERMAN;
        }

        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", switchedLocale );  // Locale.GERMAN, Locale.ENGLISH
        primaryStage.getScene().setRoot(root);
        mainViewModel.selectTour(tourOverviewController.tourItemList.getSelectionModel().getSelectedItem());
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
