package com.glatzerkratzer.tourplanner.dal;

import com.glatzerkratzer.tourplanner.database.DatabaseService;
import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TourLog;

public class DAL {

    private Dao<TourItem> tourDao;
    private Dao<TourLog> tourLogDao;

    private DAL() {

        tourDao = new TourItemDao();
        tourLogDao = new TourLogDao();
    }

    //
    // Tours:
    //
    public Dao<TourItem> tourDao() {
        return tourDao;
    }

    //
    // Logs:
    //
    public Dao<TourLog> tourLogDao() { return tourLogDao; }


    //
    // Singleton-Pattern for DAL with early-binding
    //
    private static DAL instance = new DAL();

    public static DAL getInstance() {
        return instance;
    }

}
