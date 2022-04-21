package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.dal.TourItemDao;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    public void selectTour(TourItem selectedTourItem) {
        tourDetailsViewModel.setTourModel(selectedTourItem);
    }

    private void searchTours(String searchString) {
        var tours = BL.getInstance().findMatchingTours( searchString );
        tourOverviewViewModel.setTours(tours);
    }

    public boolean tourExists(TourItem tourItem) {
        if (DAL.getInstance().tourDao().getTourItemIdByName(tourItem.getName()) > 0) {
            return true;
        }
        return false;
    }

    public List importFile(File file) {
        String filePath = file.getAbsolutePath();
        String delimiter = ";";
        String line = "";
        List<TourItem> tourItems = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                TourItem tourItem = new TourItem();
                String[] tourItemString = line.split(delimiter);
                tourItem.setName(tourItemString[0]);
                tourItem.setStart(tourItemString[1]);
                tourItem.setDestination(tourItemString[2]);
                tourItem.setTransportType(TransportType.valueOf(tourItemString[3]));
                tourItem.setDescription(tourItemString[4]);

                if (tourExists(tourItem)) {
                    tourItems.clear();
                    tourItems.add(tourItem);
                    return tourItems;
                }

                tourItems.add(tourItem);
            }
            for (var tour : tourItems) {
                DAL.getInstance().tourDao().add(tour);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
