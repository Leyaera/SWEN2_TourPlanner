package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.mq.MapQuestService;
import org.json.JSONObject;

import java.io.IOException;

public class TourDetailsViewModel {
    private TourItem tourItemModel;
    private TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel;
    private TourDetailsMapViewModel tourDetailsMapViewModel;
    private volatile boolean isInitValue = false;


    public TourDetailsViewModel(TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel, TourDetailsMapViewModel tourDetailsMapViewModel) {
        this.tourDetailsDescriptionViewModel = tourDetailsDescriptionViewModel;
        this.tourDetailsMapViewModel = tourDetailsMapViewModel;
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

    private void updateTourModel() {
        //if( !isInitValue )
            //DAL.getInstance().tourDao().update(tourItemModel, Arrays.asList(tourItemModel.getId(), this.tourDetailsDescriptionViewModel.getName(), this.tourDetailsDescriptionViewModel.getDescription(), this.tourDetailsDescriptionViewModel.getStart(), this.tourDetailsDescriptionViewModel.getDestination(), this.tourDetailsDescriptionViewModel.getTransportType()));
    }

    public void getMissingValues(TourItem tourItem) {
        JSONObject tourJson = null;
        String mapPath = "";

        try {
            tourJson = MapQuestService.getMapQuestService().getRoute(tourItem.getStart(), tourItem.getDestination());
            mapPath = MapQuestService.getMapQuestService().getImageURLOf(tourJson);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (tourJson == null || mapPath.isBlank()) {
            System.out.println("tourJson or mapPath in getMissingValues in TourDetailsViewModel is NULL");
            return;
        }

        tourItem.setDistance(MapQuestService.getMapQuestService().getDistanceOf(tourJson));
        tourItem.setDuration(MapQuestService.getMapQuestService().getDurationOf(tourJson));
        tourItem.setMapPath(mapPath);
    }

}
