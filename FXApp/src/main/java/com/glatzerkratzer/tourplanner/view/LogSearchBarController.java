package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.LogSearchBarViewModel;
import com.glatzerkratzer.tourplanner.viewmodel.SearchBarViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.Locale;

public class LogSearchBarController {

    @FXML
    public TextField searchTextField;

    private final LogSearchBarViewModel logSearchBarViewModel;
    private Locale locale;

    public LogSearchBarController(LogSearchBarViewModel logSearchBarViewModel, Locale locale) {
        this.logSearchBarViewModel = logSearchBarViewModel;
        this.locale = locale;
    }

    public LogSearchBarViewModel getSearchBarViewModel() {
        return logSearchBarViewModel;
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional( logSearchBarViewModel.searchStringProperty() );
    }

    public void onKeyTypedSearch(KeyEvent keyEvent) { logSearchBarViewModel.doSearch(); }
}
