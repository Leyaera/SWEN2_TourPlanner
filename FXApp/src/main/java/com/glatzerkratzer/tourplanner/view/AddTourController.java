package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.AddTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Locale;

public class AddTourController {

    @FXML public TextField addTourNameTextField;
    @FXML public TextField addTourStartTextField;
    @FXML public TextField addTourDestinationTextField;
    @FXML public ChoiceBox<String> addTourTransportTypeChoiceBox;
    @FXML public TextArea addTourDescriptionTextArea;
    @FXML public Label addTourNameRequired;
    @FXML public Label addTourStartRequired;
    @FXML public Label addTourDestinationRequired;
    @FXML public Label addTourTransportTypeRequired;
    @FXML public Label inputRequired;

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
        // check required fields
        if(!isInputValid()) {
            showRequiredWarning();
            return;
        }
        addTourViewModel.tourItem.setName(addTourNameTextField.getText());
        addTourViewModel.tourItem.setStart(addTourStartTextField.getText());
        addTourViewModel.tourItem.setDestination(addTourDestinationTextField.getText());
        addTourViewModel.tourItem.setDescription(addTourDescriptionTextArea.getText());
        addTourViewModel.tourItem.setTransportType(addTourViewModel.getTransportTypeByChoiceBoxValue(addTourTransportTypeChoiceBox.getValue()));

        if (addTourViewModel.tourExists()) {
            showTourExistsWarning();
            return;
        }

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

    public void showRequiredWarning() {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";
        String AlertBox_HeaderText = "";

        System.out.println("locale = " + locale);
        if (locale.toString().equals("en")) {
            AlertBox_Title = "Input required";
            AlertBox_HeaderText = "Warning";
            AlertBox_ContentText = "Please fill in all required fields!";
        }

        if (locale.toString().equals("de")) {
            AlertBox_Title = "Input erforderlich";
            AlertBox_HeaderText = "Warnung";
            AlertBox_ContentText = "Bitte alle erforderlichen Felder ausf??llen!";
        }
        Alert warningBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
        warningBox.setTitle(AlertBox_Title);
        warningBox.setHeaderText(AlertBox_HeaderText);
        warningBox.showAndWait();
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
            AlertBox_Title = "Tour hinzuf??gen Fehler";
            AlertBox_HeaderText = "Warnung";
            AlertBox_ContentText = "Tour wurde nicht hinzugef??gt. Tour name existiert bereits.";
        }
        Alert warningBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
        warningBox.setTitle(AlertBox_Title);
        warningBox.setHeaderText(AlertBox_HeaderText);
        warningBox.showAndWait();
    }

    public boolean isInputValid() {
        boolean isValid = true;
        addTourNameRequired.setTextFill(Color.BLACK);
        addTourStartRequired.setTextFill(Color.BLACK);
        addTourDestinationRequired.setTextFill(Color.BLACK);
        addTourTransportTypeRequired.setTextFill(Color.BLACK);

        if (addTourNameTextField.getText().isBlank()) {
            addTourNameRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }

        if (addTourStartTextField.getText().isBlank()) {
            addTourStartRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }

        if (addTourDestinationTextField.getText().isBlank()) {
            addTourDestinationRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }

        if (addTourTransportTypeChoiceBox.getValue() == null) {
            addTourTransportTypeRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }
        return isValid;
    }
}
