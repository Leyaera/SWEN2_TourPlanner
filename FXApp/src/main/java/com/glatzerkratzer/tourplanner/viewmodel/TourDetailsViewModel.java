package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;
import org.json.JSONObject;

import java.io.IOException;

public class TourDetailsViewModel {
    private TourItem tourItemModel;
    private TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel;
    private TourDetailsMapViewModel tourDetailsMapViewModel;
    private TourDetailsLogsViewModel tourDetailsLogsViewModel;
    private volatile boolean isInitValue = false;


    public TourDetailsViewModel(TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel, TourDetailsMapViewModel tourDetailsMapViewModel, TourDetailsLogsViewModel tourDetailsLogsViewModel) {
        this.tourDetailsDescriptionViewModel = tourDetailsDescriptionViewModel;
        this.tourDetailsMapViewModel = tourDetailsMapViewModel;
        this.tourDetailsLogsViewModel = tourDetailsLogsViewModel;
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
            this.tourDetailsDescriptionViewModel.setDistance(-1.0);
            this.tourDetailsDescriptionViewModel.setDuration("-1.0");
            //this.tourDetailsMapViewModel.setMapPath("");
            return;
        }

        getMissingValues(tourItemModel);

        this.tourItemModel = tourItemModel;
        this.tourDetailsDescriptionViewModel.setName( tourItemModel.getName() );
        this.tourDetailsDescriptionViewModel.setDescription( tourItemModel.getDescription() );
        this.tourDetailsDescriptionViewModel.setStart( tourItemModel.getStart() );
        this.tourDetailsDescriptionViewModel.setDestination( tourItemModel.getDestination() );
        this.tourDetailsDescriptionViewModel.setTransportType( tourItemModel.getTransportType().toString() );
        this.tourDetailsDescriptionViewModel.setDistance( tourItemModel.getDistance() );
        this.tourDetailsDescriptionViewModel.setDuration( tourItemModel.getDuration() );

        this.tourDetailsMapViewModel.setName( tourItemModel.getName() );
        this.tourDetailsMapViewModel.setStart( tourItemModel.getStart() );
        this.tourDetailsMapViewModel.setDestination( tourItemModel.getDestination() );
        this.tourDetailsMapViewModel.setTransportType( tourItemModel.getTransportType().toString() );
        this.tourDetailsMapViewModel.setDistance( tourItemModel.getDistance() );
        this.tourDetailsMapViewModel.setDuration( tourItemModel.getDuration() );
        this.tourDetailsMapViewModel.setMapImage( tourItemModel.getMapPath() );

        isInitValue = false;
    }

    public void getMissingValues(TourItem tourItem) {
        BL.getInstance().getMql().getMissingValues(tourItem);
    }
}
