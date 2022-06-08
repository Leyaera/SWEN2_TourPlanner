package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.model.TourLog;
import com.glatzerkratzer.tourplanner.view.ControllerFactory;
import com.glatzerkratzer.tourplanner.view.TourOverviewController;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.List;

public class TourDetailsLogsViewModel {

    public TourDetailsLogsViewModel(TourOverviewViewModel tourOverviewViewModel) {
        List<TourLog> tourLogs = BL.getInstance().getLogBL().findMatchingLogs("", 0);
        setLogs( tourLogs );
    }

    public void SetSelectedLog(int tourId) {
        List<TourLog> tourLogs = BL.getInstance().getLogBL().findMatchingLogs("", tourId);
        setLogs( tourLogs );
    }


    public interface SelectionChangedListener {
        void changeSelection(TourLog tourLog);
    }

    private List<TourDetailsLogsViewModel.SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<TourLog> observableTourLogs = FXCollections.observableArrayList();


    public ObservableList<TourLog> getObservableTourLogs() {
        return observableTourLogs;
    }

    public ChangeListener<TourLog> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    public void addSelectionChangedListener(TourDetailsLogsViewModel.SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void removeSelectionChangedListener(TourDetailsLogsViewModel.SelectionChangedListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(TourLog newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setLogs(List<TourLog> tourLogs) {
        observableTourLogs.clear();
        observableTourLogs.addAll(tourLogs);
    }

    public void refreshLogsList() {
        List<TourLog> tourLogs = BL.getInstance().getLogBL().getAllLogs(ControllerFactory.getInstance().getTourOverviewController().tourItemList.getSelectionModel().getSelectedItem().getId());
        setLogs( tourLogs );
    }

    public void deleteLog(TourLog tourLog) {
        BL.getInstance().getLogBL().deleteLog(tourLog);
        observableTourLogs.remove(tourLog);
    }

    public boolean addLog(TourLog tourLog) {
        BL.getInstance().getLogBL().addNewLog(tourLog);
        return true;
    }

}
