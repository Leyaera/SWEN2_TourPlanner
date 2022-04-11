package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.AddTourViewModel;
import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsViewModel;

import java.util.Locale;

public class AddTourController {
    private final AddTourViewModel addTourViewModel;
    private Locale locale;


    public AddTourController(AddTourViewModel addTourViewModel, Locale locale) {
        this.locale = locale;
        this.addTourViewModel = addTourViewModel;
    }
}
