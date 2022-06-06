package com.glatzerkratzer.tourplanner.tests;

import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.dal.Dao;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TourLog;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class DALTest {
    @Mock private DAL dal;
    @Mock private Dao<TourItem> tourDao;
    @Mock private Dao<TourLog> tourLogDao;

    @Test
    void testTourDaoIsNotNull() {
        // arrange & act
        tourDao = DAL.getInstance().tourDao();

        // assert
        assertNotNull(tourDao);
    }

    @Test
    void testTourLogDaoIsNotNull() {
        // arrange & act
        tourLogDao = DAL.getInstance().tourLogDao();

        // assert
        assertNotNull(tourLogDao);
    }

    @Test
    void testGetInstanceIsNotNull() {
        // arrange & act
        dal = DAL.getInstance();

        // assert
        assertNotNull(dal);
    }
}