package wyscigi_konne.GUI.fxml.statystyka;

import java.io.IOException;
import javafx.fxml.FXML;
import wyscigi_konne.GUI.WyscigiKonne;


public class WynikiKoniaController {
        
    private WyscigiKonne pokaz;
    
    @FXML
    public void goDotychczasoweWyniki() throws IOException{  
        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    }
      
    @FXML
    public void goNewWindow() throws IOException{
        pokaz.showNewWindow("fxml/statystyka/TabelaKoni.fxml");
    }
}
