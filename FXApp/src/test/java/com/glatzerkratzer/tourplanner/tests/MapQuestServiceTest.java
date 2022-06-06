package com.glatzerkratzer.tourplanner.tests;

import com.glatzerkratzer.tourplanner.model.TransportType;
import com.glatzerkratzer.tourplanner.mq.MapQuestService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapQuestServiceTest {

    @Mock private MapQuestService mockMapQuestService;
    @Mock private JSONObject mockJsonRoute;

    @Test
    void testGetMapQuestServiceNotNull() {
        // arrange & act
        mockMapQuestService = MapQuestService.getMapQuestService();

        // assert
        assertNotNull(mockMapQuestService);
    }

    @Test
    void testGetRouteJsonIsNotNull() throws IOException, InterruptedException {
        // arrange & act
        mockJsonRoute = MapQuestService.getMapQuestService().getRoute("Wien", "Berlin", "VACATION");

        // assert
        assertNotNull(mockJsonRoute);
    }

    @Test
    void testGetTransportTypeBikeIsBicycle() {
        // arrange & act
        String transportType = MapQuestService.getMapQuestService().getTransportType(TransportType.BIKE.toString());

        // assert
        assertEquals("bicycle", transportType);
    }

    @Test
    void testGetTransportTypeHikeIsPedestrian() {
        // arrange & act
        String transportType = MapQuestService.getMapQuestService().getTransportType(TransportType.HIKE.toString());

        // assert
        assertEquals("pedestrian", transportType);
    }

    @Test
    void testGetTransportTypeVacationIsFastest() {
        // arrange & act
        String transportType = MapQuestService.getMapQuestService().getTransportType(TransportType.VACATION.toString());

        // assert
        assertEquals("fastest", transportType);
    }

    @Test
    void testGetTransportTypeRunningIsPedestrian() {
        // arrange & act
        String transportType = MapQuestService.getMapQuestService().getTransportType(TransportType.RUNNING.toString());

        // assert
        assertEquals("pedestrian", transportType);
    }

    @Test
    void testGetDurationOfRouteWienBerlin() throws IOException, InterruptedException {
        // arrange
        mockJsonRoute = MapQuestService.getMapQuestService().getRoute("Wien", "Berlin", TransportType.VACATION.toString());

        // act
        String formattetTime = mockJsonRoute.getJSONObject("route").getString("formattedTime");

        // assert
        assertEquals("06:57:10", formattetTime);
    }

    @Test
    void testGetDistanceOfRouteWienBerlin() throws IOException, InterruptedException {
        // arrange
        mockJsonRoute = MapQuestService.getMapQuestService().getRoute("Wien", "Berlin", TransportType.VACATION.toString());

        // act
        double distance = mockJsonRoute.getJSONObject("route").getDouble("distance");

        // assert
        assertEquals(689.9709, distance);
    }
}