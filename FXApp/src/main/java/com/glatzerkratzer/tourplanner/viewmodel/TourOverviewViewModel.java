package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TourOverviewViewModel {
    public interface SelectionChangedListener {
        void changeSelection(TourItem tourItem);
    }


    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<TourItem> observableTourItems = FXCollections.observableArrayList();

    public TourOverviewViewModel()
    {
        List<TourItem> tourItems = DAL.getInstance().tourDao().getAll();
        setTours( tourItems );
    }

    public ObservableList<TourItem> getObservableTours() {
        return observableTourItems;
    }

    public ChangeListener<TourItem> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void removeSelectionChangedListener(SelectionChangedListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(TourItem newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setTours(List<TourItem> tourItems) {
        observableTourItems.clear();
        observableTourItems.addAll(tourItems);
    }

    public void refreshToursList() {
        int currentListSize = observableTourItems.size();
        if (currentListSize > 0) { currentListSize -= 1; }
        var tours = DAL.getInstance().tourDao().getLatestEntries(observableTourItems.get(currentListSize).getId());
        observableTourItems.addAll(tours);
    }

    public void deleteTour(TourItem tourItem) {
        DAL.getInstance().tourDao().delete(tourItem);
        observableTourItems.remove(tourItem);
    }
}
