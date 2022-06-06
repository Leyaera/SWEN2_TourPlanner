package com.glatzerkratzer.tourplanner.bl;

import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.model.TourItem;

import java.util.List;
import java.util.stream.Collectors;

public class DALL {

    public DALL() {

    }
    public List<TourItem> findMatchingTours(String searchText) {
        var tours = DAL.getInstance().tourDao().getAll();
        if (searchText==null || searchText.isEmpty()) {
            return tours;
        }
        return tours.stream()
                .filter(t->t.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<TourItem> getAllTours() { return DAL.getInstance().tourDao().getAll(); }

    public void addNewTour(TourItem tourItem) { DAL.getInstance().tourDao().add(tourItem); }

    public void deleteTour(TourItem tourItem) { DAL.getInstance().tourDao().delete(tourItem); }

    public int getTourIdByName(String tourItemName) { return DAL.getInstance().tourDao().getIdByName(tourItemName); }

    public List<TourItem> getLatestTourEntries(int newId) { return DAL.getInstance().tourDao().getLatestEntries(newId);}

    public boolean tourExists(TourItem tourItem) {
        if (getTourIdByName(tourItem.getName()) > 0) return true;
        return false;
    }

    public void updateTour(String currentName, TourItem tourItem) { DAL.getInstance().tourDao().updateById(DAL.getInstance().tourDao().getIdByName(currentName), tourItem);}

}
