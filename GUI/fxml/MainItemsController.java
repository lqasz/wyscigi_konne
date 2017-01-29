package wyscigi_konne.GUI.fxml;

import java.io.IOException;
import javafx.fxml.FXML;
import wyscigi_konne.GUI.WyscigiKonne;

public class MainItemsController {
    
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
        pokaz.showNewView("");
    }
    
}