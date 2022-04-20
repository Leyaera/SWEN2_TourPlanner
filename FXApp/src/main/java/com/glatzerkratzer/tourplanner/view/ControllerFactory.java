package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.*;

import java.util.Locale;

public class ControllerFactory {
    private final MainWindowViewModel mainWindowViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel;
    private final AddTourViewModel addTourViewModel;
    private final EditTourViewModel editTourViewModel;
    private final ImportTourViewModel importTourViewModel;

    public ControllerFactory() {
        searchBarViewModel = new SearchBarViewModel();
        tourOverviewViewModel = new TourOverviewViewModel();
        tourDetailsDescriptionViewModel = new TourDetailsDescriptionViewModel();
        tourDetailsViewModel = new TourDetailsViewModel(tourDetailsDescriptionViewModel);
        mainWindowViewModel = new MainWindowViewModel(searchBarViewModel, tourOverviewViewModel, tourDetailsViewModel);
        addTourViewModel = new AddTourViewModel();
        editTourViewModel = new EditTourViewModel();
        importTourViewModel = new ImportTourViewModel();
    }

    //
    // Factory-Method Pattern
    //
    public Object create(Class<?> controllerClass, Locale locale) {
        this.tourDetailsDescriptionViewModel.locale = locale;
        if (controllerClass == MainWindowController.class) {
            return new MainWindowController(mainWindowViewModel, locale);
        } else if (controllerClass == SearchBarController.class) {
            return new SearchBarController(searchBarViewModel, locale);
        } else if (controllerClass == TourDetailsController.class) {
            return new TourDetailsController(tourDetailsViewModel, locale);
        } else if (controllerClass == TourOverviewController.class) {
            return new TourOverviewController(tourOverviewViewModel, locale);
        } else if (controllerClass == TourDetailsDescriptionController.class) {
            return new TourDetailsDescriptionController(tourDetailsDescriptionViewModel, locale);
        } else if (controllerClass == AddTourController.class) {
            return new AddTourController(addTourViewModel, locale);
        } else if (controllerClass == EditTourController.class) {
            return new EditTourController(editTourViewModel, locale);
        } else if (controllerClass == ImportTourController.class) {
            return new ImportTourController(importTourViewModel, locale);
        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }


    //
    // Singleton-Pattern with early-binding
    //
    private static ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance() {
        return instance;
    }

}
