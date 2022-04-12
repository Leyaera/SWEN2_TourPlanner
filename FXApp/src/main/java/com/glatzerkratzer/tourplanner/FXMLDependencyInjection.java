package com.glatzerkratzer.tourplanner;

import com.glatzerkratzer.tourplanner.view.ControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * FXMLLoader with Dependency-Injection
 * based on https://edencoding.com/dependency-injection/
 */
public class FXMLDependencyInjection {
    private static FXMLLoader loader;

    public static Parent load(String location, Locale locale) throws IOException {
        loader = getLoader(location, locale);
        return loader.load();
    }

    public static FXMLLoader getLoader(String location, Locale locale) {
        return new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/com/glatzerkratzer/tourplanner/view/fxml/" + location),
                ResourceBundle.getBundle("com.glatzerkratzer.tourplanner.view." + "gui_strings", locale),
                new JavaFXBuilderFactory(),
                controllerClass-> ControllerFactory.getInstance().create(controllerClass, locale)
                );
    }

    // for passing data to secondary stages
    public <T> T getController() { return this.loader.getController(); }
}
