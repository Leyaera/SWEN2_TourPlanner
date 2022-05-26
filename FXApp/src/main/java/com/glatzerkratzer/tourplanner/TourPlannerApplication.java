package com.glatzerkratzer.tourplanner;

import com.glatzerkratzer.tourplanner.logger.ILoggerWrapper;
import com.glatzerkratzer.tourplanner.logger.Log4J2Wrapper;
import com.glatzerkratzer.tourplanner.logger.LoggerFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class TourPlannerApplication extends Application {

    //private static final Logger logger = Logger.getLogger(TourPlannerApplication.class.toString());

    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

    public static void main(String[] args) {
        launch(args);

        //TourPlannerApplication object = new TourPlannerApplication();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        runMe();

        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", Locale.ENGLISH );  // Locale.GERMAN, Locale.ENGLISH

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SWEN2 Tour Planner | GlatzerKratzer");
        primaryStage.show();
    }

    public void runMe()
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("This is a debug message.");
        }
        /*if(logger.isInfoEnabled())
        {
            logger.info("This is an info message.");
        }*/
        if(logger.isWarnEnabled())
        {
            logger.warn("This is an warn message.");
        }
        if(logger.isFatalEnabled())
        {
            logger.fatal("This is an fatal message.");
        }

        logger.error("This is an error message.");
    }

}
