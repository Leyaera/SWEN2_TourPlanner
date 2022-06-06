package com.glatzerkratzer.tourplanner.tests;

import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsDescriptionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourDetailsDescriptionViewModelTest {
    TourDetailsDescriptionViewModel testDescriptionViewModel;

    @BeforeEach
    void initTourDetailsDescriptionViewModel()
    {
        testDescriptionViewModel = new TourDetailsDescriptionViewModel();
    }

    @Test
    void getterSetterName()
    {
        testDescriptionViewModel.setName("testName1");
        testDescriptionViewModel.setName("testName2");
        assertEquals(testDescriptionViewModel.getName(), "testName2");
    }

    @Test
    void nameProperty()
    {
        testDescriptionViewModel.setName("testName1");
        assertEquals(testDescriptionViewModel.nameProperty().getValue(), "testName1");
    }

    @Test
    void getterSetterDistance()
    {
        testDescriptionViewModel.setDistance(1.12);
        testDescriptionViewModel.setDistance(1.13);
        assertEquals(testDescriptionViewModel.getDistance(), "| 1.13 km ");
    }

    @Test
    void getterSetterDuration()
    {
        testDescriptionViewModel.setDuration("11");
        testDescriptionViewModel.setDuration("10");
        assertEquals(testDescriptionViewModel.getDuration(), "| 10\n\n");
    }

}