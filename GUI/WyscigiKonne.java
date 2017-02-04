package wyscigi_konne.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wyscigi_konne.GUI.fxml.AllertBoxController;


public class WyscigiKonne extends Application{
    
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        this.primaryStage = primaryStage;   
        this.primaryStage.setTitle("Wyścigi Konne");
        
        showMainView("fxml/MainView.fxml");
        showView("fxml/MainItems.fxml");
    }
    
    private void showMainView(String adres) throws IOException{
      
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(WyscigiKonne.class.getResource(adres));
        mainLayout = (BorderPane)loader.load();
        
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showView(String adres) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(WyscigiKonne.class.getResource(adres));
        BorderPane mainItems = (BorderPane)loader.load();
        mainLayout.setCenter(mainItems);
    }
    
    public static void showNewWindow(String adres) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(WyscigiKonne.class.getResource(adres));
        BorderPane statsWindow = (BorderPane)loader.load();
        
        Stage noweOkno = new Stage();
        noweOkno.setTitle("Statystyki");
        noweOkno.initModality(Modality.WINDOW_MODAL);
        noweOkno.initOwner(primaryStage);
        
        Scene scene = new Scene(statsWindow);
        noweOkno.setScene(scene);
        noweOkno.showAndWait();
    }
    
    public static void showAlertBox(String rodzajBledu, String komunikat) throws IOException{

        AllertBoxController przekaznik;
        przekaznik = new AllertBoxController(komunikat);
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(WyscigiKonne.class.getResource("/fxml/AlertBox.fxml"));
        BorderPane statsWindow = (BorderPane)loader.load();
        loader.setController(przekaznik);
        
        Stage noweOkno = new Stage();
        noweOkno.setTitle(rodzajBledu);
        noweOkno.initModality(Modality.WINDOW_MODAL);
        noweOkno.initOwner(primaryStage);
        
        Scene scene = new Scene(statsWindow);
        noweOkno.setScene(scene);
        noweOkno.showAndWait();
    }
   
    public static void main(String[] args) {
        launch(args);
    }

    public void showNewView(String fxmlprzewidywanieSymulacjafxml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}