package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
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

    private DownloadViewModel downloadViewModel;

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<TourItem> observableTourItems = FXCollections.observableArrayList();

    public TourOverviewViewModel(DownloadViewModel downloadViewModel)
    {
        List<TourItem> tourItems = BL.getInstance().getDall().getAllTours();
        setTours( tourItems );
        this.downloadViewModel = downloadViewModel;
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
        int newId = 0;
        if (currentListSize > 0) {
            newId = observableTourItems.get(currentListSize - 1).getId();
        }
        var tours = BL.getInstance().getDall().getLatestTourEntries(newId);
        observableTourItems.addAll(tours);
    }

    public void deleteTour(TourItem tourItem) {
        BL.getInstance().getDall().deleteTour(tourItem);
        observableTourItems.remove(tourItem);
    }

    public boolean addTour(TourItem tourItem) {
        BL.getInstance().getDall().addNewTour(tourItem);
        return true;
    }

    public void addDuplicate(TourItem tourItem) {
        TourItem duplicateTourItem = new TourItem();
        duplicateTourItem.setName(tourItem.getName() + "_copy");
        duplicateTourItem.setDescription(tourItem.getDescription());
        duplicateTourItem.setStart(tourItem.getStart());
        duplicateTourItem.setDestination(tourItem.getDestination());
        duplicateTourItem.setTransportType(tourItem.getTransportType());
        addTour(duplicateTourItem);
    }

    public boolean duplicateExists(TourItem tourItem) {
        if (tourItem.getName().contains("_copy")) { return true; }
        if (BL.getInstance().getDall().getTourIdByName(tourItem.getName() + "_copy") > 0) { return true; };
        return false;
    }
}
