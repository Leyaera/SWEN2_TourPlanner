package com.glatzerkratzer.tourplanner.bl;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.model.TransportType;
import com.glatzerkratzer.tourplanner.mq.MapQuestService;
import lombok.Getter;
import org.json.JSONObject;

import java.io.IOException;


@Getter
public class BL {

    //
    // TOUR BL
    //
    DALL Dall = new DALL();


    //
    // MAP QUEST BL
    //
    MQL Mql = new MQL();

    //
    // TOUR LOGS BL
    //
    // TLL Tll = new TLL();



    //
    // Singleton-Pattern for BL with early-binding
    //
    private static BL instance = new BL();

    public static BL getInstance() { return instance; }
}
