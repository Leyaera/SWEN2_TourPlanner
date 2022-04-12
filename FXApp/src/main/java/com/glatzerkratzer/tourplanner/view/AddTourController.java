package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.model.TransportType;
import com.glatzerkratzer.tourplanner.viewmodel.AddTourViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Locale;

public class AddTourController {

    @FXML public TextField addTourNameTextField;
    @FXML public TextField addTourStartTextField;
    @FXML public TextField addTourDestinationTextField;
    @FXML public ChoiceBox<String> addTourTransportTypeChoiceBox;
    @FXML public TextArea addTourDescriptionTextArea;

    private final AddTourViewModel addTourViewModel;
    private Locale locale;

    public AddTourController(AddTourViewModel addTourViewModel, Locale locale) {
        this.locale = locale;
        this.addTourViewModel = addTourViewModel;
    }

    @FXML
    void initialize() {
        this.addTourTransportTypeChoiceBox.getItems().addAll(addTourViewModel.getChoiceBoxItems(locale));
    }

    public void onAddTourOKButton(ActionEvent event) {
        addTourViewModel.tourItem.setName(addTourNameTextField.getText());
        addTourViewModel.tourItem.setStart(addTourStartTextField.getText());
        addTourViewModel.tourItem.setDestination(addTourDestinationTextField.getText());
        addTourViewModel.tourItem.setDescription(addTourDescriptionTextArea.getText());
        addTourViewModel.tourItem.setTransportType(addTourViewModel.getTransportTypeByChoiceBoxValue(addTourTransportTypeChoiceBox.getValue()));

        // add to database
        addTourViewModel.addTour();

        // close window
        Stage secondaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        secondaryStage.close();
    }

    public void onAddTourCancelButton(ActionEvent event) {
        Stage secondaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        secondaryStage.close();
    }
}
