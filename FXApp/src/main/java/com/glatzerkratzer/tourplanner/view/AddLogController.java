package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.model.TransportType;
import com.glatzerkratzer.tourplanner.viewmodel.AddLogViewModel;
import com.glatzerkratzer.tourplanner.viewmodel.AddTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Locale;

public class AddLogController {

    @FXML private Label tourName;
    @FXML private TextField addLogDurationTextField;
    @FXML private ChoiceBox<Integer> addLogDifficultyChoiceBox;
    @FXML private ChoiceBox<Integer> addLogRatingChoiceBox;
    @FXML private TextArea addLogCommentTextArea;
    @FXML private Label addLogDifficultyRequired;
    @FXML private Label addLogRatingRequired;
    @FXML private Label inputRequired;
    @FXML private Label addLogDurationFormat;

    private final AddLogViewModel addLogViewModel;
    private Locale locale;

    public AddLogController(AddLogViewModel addLogViewModel, Locale locale) {
        this.locale = locale;
        this.addLogViewModel = addLogViewModel;
    }

    @FXML
    void initialize() {
        tourName.setText(ControllerFactory.getInstance().getTourOverviewController().tourItemList.getSelectionModel().getSelectedItem().getName());
        this.addLogDifficultyChoiceBox.getItems().addAll(1, 2, 3, 4, 5);
        this.addLogRatingChoiceBox.getItems().addAll(1, 2, 3, 4, 5);
    }

    public void onAddLogCancelButton(ActionEvent event) {
        Stage secondaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        secondaryStage.close();
    }

    public void onAddLogOKButton(ActionEvent event) {
        // check required fields
        if(!isInputValid()) {
            showRequiredWarning();
            return;
        }

        addLogViewModel.tourLog.setDuration(addLogDurationTextField.getText());
        addLogViewModel.tourLog.setDifficulty(addLogDifficultyChoiceBox.getValue());
        addLogViewModel.tourLog.setRating(addLogRatingChoiceBox.getValue());
        addLogViewModel.tourLog.setComment(addLogCommentTextArea.getText());
        addLogViewModel.tourLog.setTourId(ControllerFactory.getInstance().getTourOverviewController().tourItemList.getSelectionModel().getSelectedItem().getId());

        // add to database
        addLogViewModel.addLog();
        // close window
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
            AlertBox_ContentText = "Bitte alle erforderlichen Felder ausfüllen!";
        }
        Alert warningBox = new Alert(Alert.AlertType.WARNING, AlertBox_ContentText);
        warningBox.setTitle(AlertBox_Title);
        warningBox.setHeaderText(AlertBox_HeaderText);
        warningBox.showAndWait();
    }

    public boolean isInputValid() {
        boolean isValid = true;
        addLogDifficultyRequired.setTextFill(Color.BLACK);
        addLogRatingRequired.setTextFill(Color.BLACK);

        if (!addLogDurationTextField.getText().matches("^[0-9][0-9]:[0-5][0-9]:[0-5][0-9]$")) {
            addLogDurationFormat.setTextFill(Color.RED);
            isValid = false;
        }

        if (addLogDifficultyChoiceBox.getValue() == null) {
            addLogDifficultyRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }

        if (addLogRatingChoiceBox.getValue() == null) {
            addLogRatingRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }
        return isValid;
    }
}
