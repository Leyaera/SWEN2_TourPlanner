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

    public EditTourController(EditTourViewModel editTourViewModel, Locale locale) {
        this.locale = locale;
        this.editTourViewModel = editTourViewModel;
    }

    @FXML
    void initialize() {
        this.editTourTransportTypeChoiceBox.getItems().addAll(editTourViewModel.getChoiceBoxItems(locale));
    }

    void initData(TourItem tourItem) {
        this.editTourNameTextField.setText(tourItem.getName());
        this.editTourStartTextField.setText(tourItem.getStart());
        this.editTourDestinationTextField.setText(tourItem.getDestination());
        this.editTourTransportTypeChoiceBox.setValue(tourItem.getTransportType().toString());
        this.editTourDescriptionTextArea.setText(tourItem.getDescription());
    }

    public void onEditTourOKButton(ActionEvent event) {
        // check required fields
        if(!isInputValid()) {
            showRequiredWarning();
            return;
        }

        /*
        editTourViewModel.tourItem.setName(addTourNameTextField.getText());
        editTourViewModel.tourItem.setStart(addTourStartTextField.getText());
        editTourViewModel.tourItem.setDestination(addTourDestinationTextField.getText());
        editTourViewModel.tourItem.setDescription(addTourDescriptionTextArea.getText());
        editTourViewModel.tourItem.setTransportType(editTourViewModel.getTransportTypeByChoiceBoxValue(addTourTransportTypeChoiceBox.getValue()));

        // add to database
        editTourViewModel.addTour();
        // close window
        Stage secondaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        secondaryStage.close();

         */
    }

    public void onEditTourCancelButton(ActionEvent event) {
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
