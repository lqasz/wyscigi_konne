package wyscigi_konne.GUI.fxml.statystyka;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import wyscigi_konne.GUI.WyscigiKonne;

public class DotychczasoweWynikiController {
        
    @FXML BorderPane tlo;
    
    @FXML Button kon;
    @FXML Button jezdziec;
    @FXML Button gonitwa;
    @FXML Button powrot;
    
    @FXML public Label tytul;
    @FXML public Label wynikK;
    @FXML public Label wynikJ;
    @FXML public Label wynikG;
    
    
    private WyscigiKonne pokaz;
    
    @FXML
    public void goMainItems() throws IOException{
       pokaz.showView("fxml/MainItems.fxml");
    }  
    
    @FXML
    public void goWynikiGonitw() throws IOException{
        pokaz.showView("fxml/statystyka/gonitwa/WynikiGonitw.fxml");
    }  
    
    @FXML
    public void goWynikiJeźdźca() throws IOException{
        pokaz.showView("fxml/statystyka/jezdziec/WynikiJezdzca.fxml");
    }  
    
    @FXML
    public void goWynikiKonia() throws IOException{
        pokaz.showView("fxml/statystyka/kon/WynikiKonia.fxml");
    }  
}
