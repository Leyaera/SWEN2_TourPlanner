package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.MainWindowViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.Locale;

public class MainWindowController {
    // Controllers of included fxml-files are injected here
    // fx:id Attribute of <fx:include> tag + "Controller"
    // tutorial see https://riptutorial.com/javafx/example/7285/nested-controllers
    @FXML private SearchBarController searchBarController;    // injected controller of SearchBar.fxml
    @FXML private TourOverviewController mediaOverviewController;    // injected controller of TourOverview.fxml
    @FXML private TourDetailsController mediaDetailsController;    // injected controller of TourDetails.fxml

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

        System.out.println("locale = " + locale);
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
        aboutBox.showAndWait();
    }
}
