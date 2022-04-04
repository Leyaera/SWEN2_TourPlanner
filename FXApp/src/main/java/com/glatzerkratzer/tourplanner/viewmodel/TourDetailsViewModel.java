package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;

public class TourDetailsViewModel {
    private TourItem tourItemModel;
    private TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel;
    private volatile boolean isInitValue = false;

    private final StringProperty mapPath = new SimpleStringProperty();

    public TourDetailsViewModel(TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel) {
        this.tourDetailsDescriptionViewModel = tourDetailsDescriptionViewModel;
        //name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }

    public String getMapPath() {
        return mapPath.get();
    }
    public StringProperty mapPathProperty() {
        return mapPath;
    }

    public void setTourModel(TourItem tourItemModel) {
        isInitValue = true;
        if( tourItemModel == null ) {
            // select the first in the list
            this.tourDetailsDescriptionViewModel.setName("");
            this.tourDetailsDescriptionViewModel.setDescription("");
            this.tourDetailsDescriptionViewModel.setStart("");
            this.tourDetailsDescriptionViewModel.setDestination("");
            this.tourDetailsDescriptionViewModel.setTransportType("");
            this.tourDetailsDescriptionViewModel.setDistance(0.0);
            this.tourDetailsDescriptionViewModel.setDuration(0.0);
            mapPath.set("");
            return;
        }
        System.out.println("setTourModel name=" + tourItemModel.getName() + ", description=" + tourItemModel.getDescription() + ", distance=" + tourItemModel.getDistance() + ", duration=" + tourItemModel.getDuration());
        this.tourItemModel = tourItemModel;
        this.tourDetailsDescriptionViewModel.setName( tourItemModel.getName() );
        this.tourDetailsDescriptionViewModel.setDescription( tourItemModel.getDescription() );
        this.tourDetailsDescriptionViewModel.setStart( tourItemModel.getStart() );
        this.tourDetailsDescriptionViewModel.setDestination( tourItemModel.getDestination() );
        this.tourDetailsDescriptionViewModel.setTransportType( tourItemModel.getTransportType().toString() );
        this.tourDetailsDescriptionViewModel.setDistance( tourItemModel.getDistance() );
        this.tourDetailsDescriptionViewModel.setDuration( tourItemModel.getDuration() );
        mapPath.setValue( tourItemModel.getMapPath() );
        isInitValue = false;
    }

    private void updateTourModel() {
        if( !isInitValue )
            DAL.getInstance().tourDao().update(tourItemModel, Arrays.asList(tourItemModel.getId(), this.tourDetailsDescriptionViewModel.getName(), this.tourDetailsDescriptionViewModel.getDescription(), this.tourDetailsDescriptionViewModel.getStart(), this.tourDetailsDescriptionViewModel.getDestination(), this.tourDetailsDescriptionViewModel.getTransportType(), this.tourDetailsDescriptionViewModel.getDistance(), this.tourDetailsDescriptionViewModel.getDuration(), mapPath.get()));
    }


}
