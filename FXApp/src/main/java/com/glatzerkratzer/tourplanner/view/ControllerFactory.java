package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.viewmodel.*;
import lombok.Getter;

import java.util.Locale;

public class ControllerFactory {
    private final MainWindowViewModel mainWindowViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final TourDetailsDescriptionViewModel tourDetailsDescriptionViewModel;
    private final TourDetailsMapViewModel tourDetailsMapViewModel;
    private final AddTourViewModel addTourViewModel;
    private final EditTourViewModel editTourViewModel;
    private final DownloadViewModel downloadViewModel;
    private final TourDetailsLogsViewModel tourDetailsLogsViewModel;

    @Getter private DownloadController downloadController;
    @Getter private AddTourController addTourController;
    @Getter private EditTourController editTourController;
    @Getter private MainWindowController mainWindowController;
    @Getter private SearchBarController searchBarController;
    @Getter private TourDetailsController tourDetailsController;
    @Getter private TourDetailsDescriptionController tourDetailsDescriptionController;
    @Getter private TourDetailsMapController tourDetailsMapController;
    @Getter private TourOverviewController tourOverviewController;
    @Getter private TourDetailsLogsController tourDetailsLogsController;

    public ControllerFactory() {
        searchBarViewModel = new SearchBarViewModel();
        downloadViewModel = new DownloadViewModel();
        tourOverviewViewModel = new TourOverviewViewModel(downloadViewModel);
        tourDetailsDescriptionViewModel = new TourDetailsDescriptionViewModel();
        tourDetailsMapViewModel = new TourDetailsMapViewModel();
        tourDetailsLogsViewModel = new TourDetailsLogsViewModel();
        tourDetailsViewModel = new TourDetailsViewModel(tourDetailsDescriptionViewModel, tourDetailsMapViewModel, tourDetailsLogsViewModel);
        mainWindowViewModel = new MainWindowViewModel(searchBarViewModel, tourOverviewViewModel, tourDetailsViewModel);
        addTourViewModel = new AddTourViewModel();
        editTourViewModel = new EditTourViewModel();
    }

    //
    // Factory-Method Pattern
    //
    public Object create(Class<?> controllerClass, Locale locale) {
        tourDetailsDescriptionViewModel.locale = locale;
        if (controllerClass == MainWindowController.class) {
            mainWindowController = new MainWindowController(mainWindowViewModel, locale);
            return mainWindowController;
        } else if (controllerClass == SearchBarController.class) {
            searchBarController = new SearchBarController(searchBarViewModel, locale);
            return searchBarController;
        } else if (controllerClass == TourDetailsController.class) {
            tourDetailsController = new TourDetailsController(tourDetailsViewModel, locale);
            return tourDetailsController;
        } else if (controllerClass == TourOverviewController.class) {
            tourOverviewController = new TourOverviewController(tourOverviewViewModel, locale);
            return tourOverviewController;
        } else if (controllerClass == TourDetailsDescriptionController.class) {
            tourDetailsDescriptionController = new TourDetailsDescriptionController(tourDetailsDescriptionViewModel, locale);
            return tourDetailsDescriptionController;
        } else if (controllerClass == TourDetailsMapController.class) {
            tourDetailsMapController = new TourDetailsMapController(tourDetailsMapViewModel, locale);
            return tourDetailsMapController;
        } else if (controllerClass == TourDetailsLogsController.class) {
            tourDetailsLogsController = new TourDetailsLogsController(tourDetailsLogsViewModel, locale);
            return tourDetailsLogsController;
        }
        else if (controllerClass == AddTourController.class) {
            addTourController = new AddTourController(addTourViewModel, locale);
            return addTourController;
        } else if (controllerClass == EditTourController.class) {
            editTourController = new EditTourController(editTourViewModel, locale);
            return editTourController;
        } else if (controllerClass == DownloadController.class){
            downloadController = new DownloadController(downloadViewModel, locale);
            return downloadController;
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
