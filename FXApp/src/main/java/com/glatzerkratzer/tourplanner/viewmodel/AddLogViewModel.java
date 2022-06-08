package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.model.TourLog;

public class AddLogViewModel {

    public TourLog tourLog;

    public AddLogViewModel() {
        this.tourLog = new TourLog();
    }

    public void addLog() {
        BL.getInstance().getLogBL().addNewLog(tourLog);
    }

}
