package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TourLog;
import com.glatzerkratzer.tourplanner.viewmodel.AddLogViewModel;
import com.glatzerkratzer.tourplanner.viewmodel.EditLogViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Locale;

public class EditLogController {

    @FXML private Label tourName;
    @FXML private TextField editLogDurationTextField;
    @FXML private ChoiceBox<Integer> editLogDifficultyChoiceBox;
    @FXML private ChoiceBox<Integer> editLogRatingChoiceBox;
    @FXML private TextArea editLogCommentTextArea;
    @FXML private Label editLogDifficultyRequired;
    @FXML private Label editLogRatingRequired;
    @FXML private Label inputRequired;
    @FXML private Label editLogDurationFormat;

    private final EditLogViewModel editLogViewModel;
    private Locale locale;
    private int currentLogId;
    private TourLog tourLog;

    public EditLogController(EditLogViewModel editLogViewModel, Locale locale) {
        this.locale = locale;
        this.editLogViewModel = editLogViewModel;
    }

    @FXML
    void initialize() {
        tourName.setText(ControllerFactory.getInstance().getTourOverviewController().tourItemList.getSelectionModel().getSelectedItem().getName());
        this.editLogDifficultyChoiceBox.getItems().addAll(1, 2, 3, 4, 5);
        this.editLogRatingChoiceBox.getItems().addAll(1, 2, 3, 4, 5);
    }

    void initData(TourLog tourItem) {
        this.tourLog = tourItem;
        this.editLogDurationTextField.setText(tourLog.getDuration());
        this.editLogCommentTextArea.setText(tourLog.getComment());
        this.editLogDifficultyChoiceBox.setValue(tourLog.getDifficulty());
        this.editLogRatingChoiceBox.setValue(tourLog.getRating());
        this.currentLogId = tourLog.getId();
    }

    public void onEditLogCancelButton(ActionEvent event) {
        Stage secondaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        secondaryStage.close();
    }

    public void onEditLogOKButton(ActionEvent event) {
        // check required fields
        if(!isInputValid()) {
            showRequiredWarning();
            return;
        }

        editLogViewModel.tourLog.setDuration(editLogDurationTextField.getText());
        editLogViewModel.tourLog.setDifficulty(editLogDifficultyChoiceBox.getValue());
        editLogViewModel.tourLog.setRating(editLogRatingChoiceBox.getValue());
        editLogViewModel.tourLog.setComment(editLogCommentTextArea.getText());

        // add to database
        editLogViewModel.updateLog(currentLogId);
        // close window
        Stage secondaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        secondaryStage.close();
    }

    public void showRequiredWarning() {
        String AlertBox_Title = "";
        String AlertBox_ContentText ="";
        String AlertBox_HeaderText = "";

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
        editLogDifficultyRequired.setTextFill(Color.BLACK);
        editLogRatingRequired.setTextFill(Color.BLACK);

        if (!editLogDurationTextField.getText().matches("^[0-9][0-9]:[0-5][0-9]:[0-5][0-9]$")) {
            editLogDurationFormat.setTextFill(Color.RED);
            isValid = false;
        }

        if (editLogDifficultyChoiceBox.getValue() == null) {
            editLogDifficultyRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }

        if (editLogRatingChoiceBox.getValue() == null) {
            editLogRatingRequired.setTextFill(Color.RED);
            inputRequired.setTextFill(Color.RED);
            isValid = false;
        }
        return isValid;
    }
}
