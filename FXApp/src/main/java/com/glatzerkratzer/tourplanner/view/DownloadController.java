package com.glatzerkratzer.tourplanner.view;

import com.glatzerkratzer.tourplanner.model.TourItem;
import com.glatzerkratzer.tourplanner.viewmodel.DownloadViewModel;
import com.glatzerkratzer.tourplanner.viewmodel.TourDetailsMapViewModel;
import com.glatzerkratzer.tourplanner.viewmodel.TourOverviewViewModel;
import com.lowagie.text.DocumentException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Locale;

public class DownloadController
{
    private final DownloadViewModel downloadViewModel;
    private Locale locale;
    @FXML
    TextField searchPath;
    @FXML
    VBox downloadArea;
    private TourOverviewController tourOverviewController = ControllerFactory.getInstance().getTourOverviewController();
    private TourDetailsMapViewModel tourDetailsMapViewModel = ControllerFactory.getInstance().getTourDetailsMapController().getTourDetailsMapViewModel();
    private String absolutePath;

    public DownloadController(DownloadViewModel downloadViewModel, Locale locale) {
        this.downloadViewModel = downloadViewModel;
        this.locale = locale;
    }
    @FXML
    void initialize() {
        searchPath.setText(System.getProperty("user.home"));
    }

    public boolean checkSelection(){
        if(tourOverviewController.tourItemList.getSelectionModel().getSelectedItem() != null)
        {
            return true;
        }
        return false;
    }

    public void onSearchPathButton()
    {
        if(checkSelection())
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            absolutePath = fileChooser.showSaveDialog(null).getAbsolutePath();
            if(absolutePath == null)
            {
                absolutePath = searchPath.getText();
            }
            searchPath.setText(absolutePath);
        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Information");
            alert.setContentText("Please select a tour first.");
            alert.show();
        }
    }

    public void onCancelButton()
    {
        searchPath.setText(System.getProperty("user.home"));
    }

    public void onSafeButton() throws DocumentException, IOException
    {

        if (!checkSelection())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Information");
            alert.setContentText("Please select a tour first.");
            alert.show();
            return;
        } else
        {
            absolutePath = searchPath.getText();

            File newFile = new File(absolutePath);

            if (newFile.isDirectory())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Information");
                alert.setContentText("Please add a filename.");
                alert.show();
                return;
            }

            if (newFile.exists() && !newFile.isDirectory())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Information");
                alert.setContentText("Please choose another filename, this one already exists");
                alert.show();
                return;
            }

            createPDF();
        }
    }

    public void createPDF () throws IOException, DocumentException
    {
        String tourName = tourOverviewController.tourItemList.getSelectionModel().getSelectedItem().getName();
        String tourStart = tourOverviewController.tourItemList.getSelectionModel().getSelectedItem().getStart();
        String tourDestination = tourOverviewController.tourItemList.getSelectionModel().getSelectedItem().getDestination();
        String tourType = tourOverviewController.tourItemList.getSelectionModel().getSelectedItem().getTransportType().toString();
        String tourDuration = tourOverviewController.tourItemList.getSelectionModel().getSelectedItem().getDuration();
        String tourDistance = Double.toString(tourOverviewController.tourItemList.getSelectionModel().getSelectedItem().getDistance());
        Image tourImage = tourDetailsMapViewModel.getImage();

        String imageUrl = tourImage.getUrl();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("tourName", tourName);
        context.setVariable("tourStart", tourStart);
        context.setVariable("tourDestination", tourDestination);
        context.setVariable("tourType", tourType);
        context.setVariable("tourDistance", tourDistance);
        context.setVariable("tourDuration", tourDuration);
        context.setVariable("tourMapImage", imageUrl);

        String htmlString = templateEngine.process("thymeleaf_template", context);

        absolutePath += ".pdf";

        OutputStream outputStream = new FileOutputStream(absolutePath);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlString);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Information");
        alert.setContentText("Download completed.");
        alert.show();
    }
}