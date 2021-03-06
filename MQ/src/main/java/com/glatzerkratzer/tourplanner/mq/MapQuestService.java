package com.glatzerkratzer.tourplanner.mq;

import org.json.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class MapQuestService {

    private static MapQuestService mapQuestService;
    private static String KEY;

    private MapQuestService() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("D:\\FH\\04_Semester_SS2022\\SWEN2\\SWEN2_TourPlanner\\config.properties");
            properties.load(fileInputStream);
            this.KEY = properties.getProperty("mapquest.key");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MapQuestService getMapQuestService() {
        if (mapQuestService == null) {
            mapQuestService = new MapQuestService();
        }
        return mapQuestService;
    }

    public JSONObject getRoute(String start, String destination, String transportType) throws IOException, InterruptedException {
        start = start.replace(" ", "%20");
        destination = destination.replace(" ", "%20");
        transportType = getTransportType(transportType);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.mapquestapi.com/directions/v2/route?key=" + KEY + "&from=" + start + "&to=" + destination + "&unit=k" + "&routeType=" + transportType))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray("[" + response.body() + "]");
        JSONObject jsonObject = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
        }
        return jsonObject;
    }

    public String getTransportType(String transportType) {
        if (transportType.equals("VACATION")) transportType = "fastest";
        if (transportType.equals("BIKE")) transportType = "bicycle";
        if (transportType.equals("HIKE")) transportType = "pedestrian";
        if (transportType.equals("RUNNING")) transportType = "pedestrian";

        return transportType;
    }

    public String getDurationOf(JSONObject route) {
        return route.getJSONObject("route").getString("formattedTime");
    }

    public double getDistanceOf(JSONObject route) {
        return route.getJSONObject("route").getDouble("distance");
    }

    public String getImageURLOf(JSONObject route) throws IOException, InterruptedException {
        double ulLat = route.getJSONObject("route").getJSONObject("boundingBox").getJSONObject("ul").getDouble("lat");
        double ulLng = route.getJSONObject("route").getJSONObject("boundingBox").getJSONObject("ul").getDouble("lng");
        double lrLat = route.getJSONObject("route").getJSONObject("boundingBox").getJSONObject("lr").getDouble("lat");
        double lrLng = route.getJSONObject("route").getJSONObject("boundingBox").getJSONObject("lr").getDouble("lng");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.mapquestapi.com/staticmap/v5/map?key="+ KEY + "&size=640%2C480&defaultMarker=none&zoom=11&rand=737758036&session=" + route.getJSONObject("route").getString("sessionId") + "&boundingBox=" + ulLat + "," + ulLng + "," + lrLat + "," +lrLng))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.uri().toString();
    }
}
