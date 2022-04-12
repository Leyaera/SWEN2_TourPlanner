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

        System.out.println("locale = " + locale);
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
