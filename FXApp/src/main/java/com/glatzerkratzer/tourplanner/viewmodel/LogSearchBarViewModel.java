package com.glatzerkratzer.tourplanner.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class LogSearchBarViewModel {
    public interface LogSearchListener {
        void search(String searchString);
    }

    private List<LogSearchListener> listeners = new ArrayList<>();

    private final StringProperty searchString = new SimpleStringProperty("");
    private final BooleanBinding isSearchDisabledBinding = Bindings.createBooleanBinding( ()->searchString.get().isEmpty() );

    public LogSearchBarViewModel() {
        searchString.addListener( (arg, oldVal, newVal)->isSearchDisabledBinding.invalidate() );
    }

    public StringProperty searchStringProperty() {
        return searchString;
    }

    public BooleanBinding searchDisabledBinding() {
        return isSearchDisabledBinding;
    }

    public void addSearchListener(LogSearchListener listener) {
        listeners.add(listener);
    }

    public void removeSearchListener(LogSearchListener listener) {
        listeners.remove(listener);
    }

    public void doSearch() {
        for (var listener : listeners ) {
            listener.search(searchString.get());
        }
    }
}
