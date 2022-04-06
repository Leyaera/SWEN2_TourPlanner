package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.SearchBarViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Locale;

public class SearchBarController {
    @FXML
    public Button searchButton;
    @FXML
    public TextField searchTextField;

    private final SearchBarViewModel searchBarViewModel;
    private Locale locale;

    public SearchBarController(SearchBarViewModel searchBarViewModel, Locale locale) {
        this.searchBarViewModel = searchBarViewModel;
        this.locale = locale;
    }

    public SearchBarViewModel getSearchBarViewModel() {
        return searchBarViewModel;
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional( searchBarViewModel.searchStringProperty() );
        searchButton.disableProperty().bind( searchBarViewModel.searchDisabledBinding() );
    }

    public void onSearchButton(ActionEvent actionEvent) {
        searchBarViewModel.doSearch();
    }
}
