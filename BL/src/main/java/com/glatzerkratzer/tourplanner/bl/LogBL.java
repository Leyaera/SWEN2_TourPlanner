package com.glatzerkratzer.tourplanner.bl;

import com.glatzerkratzer.tourplanner.dal.DAL;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TourLog;

import java.util.List;
import java.util.stream.Collectors;

public class LogBL {

    public LogBL() { }

    public List<TourLog> findMatchingLogs(String searchText, int tourId) {
        var logs = DAL.getInstance().tourLogDao().getAll(tourId);
        if (searchText==null || searchText.isEmpty()) {
            return logs;
        }
        return logs.stream()
                .filter(t->t.getDate_time().toLowerCase().contains(searchText.toLowerCase()) || t.getComment().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<TourLog> getAllLogs(int tourId) { return DAL.getInstance().tourLogDao().getAll(tourId); }

    public void addNewLog(TourLog tourLog) { DAL.getInstance().tourLogDao().add(tourLog); }

    public void deleteLog(TourLog tourLog) { DAL.getInstance().tourLogDao().delete(tourLog); }


    public List<TourLog> getLatestTourEntries(int newId) { return DAL.getInstance().tourLogDao().getLatestEntries(newId);}

    public void updateLog(int logId, TourLog tourLog) { DAL.getInstance().tourLogDao().updateById(logId, tourLog);}

}
