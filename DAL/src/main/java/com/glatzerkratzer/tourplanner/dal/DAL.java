package com.glatzerkratzer.tourplanner.dal;

import com.glatzerkratzer.tourplanner.model.TourItem;

public class DAL {

    private Dao<TourItem> tourDao;

    private DAL() {
        tourDao = new TourItemDao();
    }

    //
    // Tours:
    //
    public Dao<TourItem> tourDao() {
        return tourDao;
    }


    //
    // Singleton-Pattern for DAL with early-binding
    //
    private static DAL instance = new DAL();

    public static DAL getInstance() {
        return instance;
    }

}
