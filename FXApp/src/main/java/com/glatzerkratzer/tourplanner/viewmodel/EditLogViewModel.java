package com.glatzerkratzer.tourplanner.viewmodel;

import com.glatzerkratzer.tourplanner.bl.BL;
import com.glatzerkratzer.tourplanner.model.TourLog;

public class EditLogViewModel {

    public TourLog tourLog;

    public EditLogViewModel() {
        this.tourLog = new TourLog();
    }

    public void updateLog(int currentId) {
        BL.getInstance().getLogBL().updateLog(currentId, tourLog);
    }
}
