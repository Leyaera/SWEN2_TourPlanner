package com.glatzerkratzer.tourplanner.tests;

import com.glatzerkratzer.tourplanner.bl.MQL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;
import com.glatzerkratzer.tourplanner.mq.MapQuestService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MQLTest {

    @Mock TourItem tourItem;
    @Mock JSONObject mockJsonRoute;
    @Mock MQL mql;

    @BeforeEach
    void initMockTourItem() throws IOException, InterruptedException {
        mql = new MQL();

        tourItem = new TourItem();
        tourItem.setStart("Wien");
        tourItem.setDestination("Berlin");
        tourItem.setTransportType(TransportType.VACATION);

        mockJsonRoute = MapQuestService.getMapQuestService().getRoute(tourItem.getStart(), tourItem.getDestination(), tourItem.getTransportType().toString());;
    }

    @Test
    void testGetMissingValueDuration() {
        // act
        mql.getMissingValues(tourItem);

        // assert
        assertEquals("06:57:10", tourItem.getDuration());
    }

    @Test
    void testGetMissingValueDistance() {
        // act
        mql.getMissingValues(tourItem);

        // assert
        assertEquals(689.9709, tourItem.getDistance());
    }

    @Test
    void testGetMissingValueMapPathNotEmpty() {
        // act
        mql.getMissingValues(tourItem);

        // assert
        assertTrue(!tourItem.getMapPath().isEmpty());
    }

    /*
    @Test
    void testGetRunningDuration() {
        // arrange
        String hikeDuration = "02:30:10";

        // act
        String runningDuration = mql.getRunningDuration(hikeDuration);

        // assert
        assertEquals("01:15:05", runningDuration);
    }
    */
}