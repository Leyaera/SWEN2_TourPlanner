package com.glatzerkratzer.tourplanner.bl;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;
import com.glatzerkratzer.tourplanner.mq.MapQuestService;
import org.json.JSONObject;

import java.io.IOException;

public class MQL {

    public MQL() { }

    public void getMissingValues(TourItem tourItem) {
        JSONObject tourJson = null;
        String mapPath = "";

        try {
            tourJson = MapQuestService.getMapQuestService().getRoute(tourItem.getStart(), tourItem.getDestination(), tourItem.getTransportType().toString());
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
        if(tourItem.getTransportType().equals(TransportType.RUNNING)) tourItem.setDuration(getRunningDuration(tourItem.getDuration()));
        tourItem.setMapPath(mapPath);
    }

    public String getRunningDuration(String duration) {
        String[] durationSplit = duration.split(":");
        var hours = Integer.parseInt(durationSplit[0]);
        var minutes = Integer.parseInt(durationSplit[1]);
        var seconds = Integer.parseInt(durationSplit[2]);

        // running = half the time of hiking
        var durationInSeconds = (hours * 60 * 60 + minutes * 60 + seconds) / 2;

        // hours, minutes, seconds in seconds
        seconds = durationInSeconds % 60;
        minutes = (durationInSeconds - seconds) % (60 * 60);
        hours = (durationInSeconds - minutes - seconds);

        // convert hours, minutes
        minutes = minutes / 60;
        hours = hours / (60 * 60);

        // create new duration string
        duration = hours + ":" + minutes + ":" + seconds;

        return duration;
    }
}
