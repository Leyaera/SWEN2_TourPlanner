package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.dal.TourItemDao;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;

import java.io.*;
import java.util.Scanner;

public class MainWindowViewModel {
    private SearchBarViewModel searchBarViewModel;
    private TourOverviewViewModel tourOverviewViewModel;
    private TourDetailsViewModel tourDetailsViewModel;

    public MainWindowViewModel(SearchBarViewModel searchBarViewModel, TourOverviewViewModel tourOverviewViewModel, TourDetailsViewModel tourDetailsViewModel) {
        this.searchBarViewModel = searchBarViewModel;
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;

        this.searchBarViewModel.addSearchListener(searchString->searchTours(searchString));
        // instead of the lambda-expression from above, you also can use the following "classical" event-handler implementation with anonymous inner classes
//        this.searchBarViewModel.addSearchListener(new SearchBarViewModel.SearchListener() {
//            @Override
//            public void search(String searchString) {
//                var tours = BL.getInstance().findMatchingTours( searchString );
//                toursOverviewViewModel.setTours(tours);
//            }
//        });

        this.tourOverviewViewModel.addSelectionChangedListener(selectedTour->selectTour(selectedTour));
    }

    private void selectTour(TourItem selectedTourItem) {
        tourDetailsViewModel.setTourModel(selectedTourItem);
    }

    private void searchTours(String searchString) {
        var tours = BL.getInstance().findMatchingTours( searchString );
        tourOverviewViewModel.setTours(tours);
    }

    public void importFile(File file) {
        TourItem tourItem = new TourItem();
        String filePath = file.getAbsolutePath();
        String delimiter = ";";
        String line = "";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                String[] tourItemString = line.split(delimiter);
                tourItem.setName(tourItemString[0]);
                tourItem.setStart(tourItemString[1]);
                tourItem.setDestination(tourItemString[2]);
                tourItem.setTransportType(TransportType.valueOf(tourItemString[3]));
                tourItem.setDescription(tourItemString[4]);
                DAL.getInstance().tourDao().add(tourItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
