package com.glatzerkratzer.tourplanner.bl;

import lombok.Getter;


@Getter
public class BL {

    //
    // TOUR BL
    //
    TourBL tourBL = new TourBL();

    //
    // LOG BL
    //
    LogBL logBL = new LogBL();


    //
    // MAP QUEST BL
    //
    MQL Mql = new MQL();


    //
    // Singleton-Pattern for BL with early-binding
    //
    private static BL instance = new BL();

    public static BL getInstance() { return instance; }
}
