package wyscigi_konne.GUI.fxml;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import wyscigi_konne.GUI.WyscigiKonne;

public class MainItemsController {
    
    @FXML public BorderPane tlo;
    
    @FXML public Button symulacja;
    @FXML public Button wyniki;
    @FXML public Button dodaj;
    
    @FXML public Label opisS;
    @FXML public Label opisW;
    @FXML public Label opisD;
    
    private WyscigiKonne pokaz;
    
    @FXML
    public void goSymulacjaGalop() throws IOException{
        pokaz.showView("fxml/przewidywanie/Symulacja.fxml");
    }
    
    @FXML
    public void goDotychczasoweWyniki() throws IOException{ 
        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    } 
    
    @FXML
    public void goDodajZawody() throws IOException{
        pokaz.showNewWindow("fxml/dodaj/Dane.fxml");
    }
}