package wyscigi_konne.GUI.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class AllertBoxController implements Initializable {
    
    @FXML BorderPane scena;
    
    @FXML Label info;

    private static String tekst;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        info.setText(tekst);
    }

    public static void getTekst(String tekst){
        
       AllertBoxController.tekst = tekst; 
    }
}

