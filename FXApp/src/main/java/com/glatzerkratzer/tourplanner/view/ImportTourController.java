package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.ImportTourViewModel;

import java.util.Locale;

public class ImportTourController {
    private final ImportTourViewModel importTourViewModel;
    private Locale locale;

    private TourItem tourItem;

    public ImportTourController(ImportTourViewModel importTourViewModel, Locale locale) {
        this.locale = locale;
        this.importTourViewModel = importTourViewModel;
    }
}
