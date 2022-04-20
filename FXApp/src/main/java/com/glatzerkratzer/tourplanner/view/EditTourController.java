package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.AddTourViewModel;
import com.glatzerkratzer.tourplanner.viewmodel.EditTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Locale;

public class EditTourController {

    @FXML public TextField editTourNameTextField;
    @FXML public TextField editTourStartTextField;
    @FXML public TextField editTourDestinationTextField;
    @FXML public ChoiceBox<String> editTourTransportTypeChoiceBox;
    @FXML public TextArea editTourDescriptionTextArea;
    @FXML public Label editTourNameRequired;
    @FXML public Label editTourStartRequired;
    @FXML public Label editTourDestinationRequired;
    @FXML public Label editTourTransportTypeRequired;
    @FXML public Label inputRequired;

    private final EditTourViewModel editTourViewModel;
    private Locale locale;
    private String currentTourName;

    private TourItem tourItem;

    public EditTourController(EditTourViewModel editTourViewModel, Locale locale) {
        this.locale = locale;
        this.editTourViewModel = editTourViewModel;
    }

    @FXML
    void initialize() {
        this.editTourTransportTypeChoiceBox.getItems().addAll(editTourViewModel.getChoiceBoxItems(locale));
    }

    void initData(TourItem tourItem) {
        this.tourItem = tourItem;
        this.editTourNameTextField.setText(tourItem.getName());
        this.editTourStartTextField.setText(tourItem.getStart());
        this.editTourDestinationTextField.setText(tourItem.getDestination());
        this.editTourTransportTypeChoiceBox.setValue(editTourViewModel.getChoiceBoxValueByTransportType(tourItem.getTransportType(), locale));
        this.editTourDescriptionTextArea.setText(tourItem.getDescription());
        this.currentTourName = tourItem.getName();
    }

    public void onEditTourOKButton(ActionEvent event) {
        // check required fields
        if(!isInputValid()) {
            showRequiredWarning();
            return;
        }

        editTourViewModel.tourItem = this.tourItem;
        editTourViewModel.tourItem.setName(editTourNameTextField.getText());
        editTourViewModel.tourItem.setStart(editTourStartTextField.getText());
        editTourViewModel.tourItem.setDestination(editTourDestinationTextField.getText());
        editTourViewModel.tourItem.setDescription(editTourDescriptionTextArea.getText());
        editTourViewModel.tourItem.setTransportType(editTourViewModel.getTransportTypeByChoiceBoxValue(editTourTransportTypeChoiceBox.getValue()));

        if (!editTourViewModel.tourItem.getName().equals(currentTourName) && editTourViewModel.tourExists()) {
            showTourExistsWarning();
            return;
        }

        // add to database
        editTourViewModel.updateTour(currentTourName);
        // close window
        Stage secondaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        secondaryStage.close();

    }

    public void onEditTourCancelButton(ActionEvent event) {
        Stage secondaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        secondaryStage.close();
    }

    public void showRequiredWarning() {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";

        if (locale.toString().equals("en")) {
            AlertBox_Title = "Input required";
            AlertBox_ContentText = "Please fill in all required fields!";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Input erforderlich";
            AlertBox_ContentText = "Bitte alle erforderlichen Felder ausfüllen!";
        }
        Alert aboutBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
        aboutBox.setTitle(AlertBox_Title);
        aboutBox.showAndWait();
    }

    public void showTourExistsWarning() {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";
        String AlertBox_HeaderText = "";

        if (locale.toString().equals("en")) {
            AlertBox_Title = "Add tour failed";
            AlertBox_HeaderText = "Warning";
            AlertBox_ContentText = "Adding tour failed. Tour name already exists.";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Tour hinzufügen Fehler";
            AlertBox_HeaderText = "Warnung";
            AlertBox_ContentText = "Tour wurde nicht hinzugefügt. Tour name existiert bereits.";
        }
        Alert warningBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
        warningBox.setTitle(AlertBox_Title);
        warningBox.setHeaderText(AlertBox_HeaderText);
        warningBox.showAndWait();
    }

    public boolean isInputValid() {
        boolean isValid = true;
        editTourNameRequired.setTextFill(Color.BLACK);
        editTourStartRequired.setTextFill(Color.BLACK);
        editTourDestinationRequired.setTextFill(Color.BLACK);
        editTourTransportTypeRequired.setTextFill(Color.BLACK);

        if (editTourNameTextField.getText().isBlank()) {
            editTourNameRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }

        if (editTourStartTextField.getText().isBlank()) {
            editTourStartRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }

        if (editTourDestinationTextField.getText().isBlank()) {
            editTourDestinationRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }

        if (editTourTransportTypeChoiceBox.getValue() == null) {
            editTourTransportTypeRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }
        return isValid;
    }
}
