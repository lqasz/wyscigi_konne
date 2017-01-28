package wyscigikonne.fxml.statystyka;

import java.io.IOException;
import javafx.fxml.FXML;
import wyscigikonne.WyscigiKonne;

public class WynikiJeżdżcaController {
    
    private WyscigiKonne pokaz;
    
    @FXML
    public void goDotychczasoweWyniki() throws IOException{  
        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    }
        
    @FXML
    public void goNewWindow() throws IOException{
        pokaz.showNewWindow("fxml/statystyka/TabelaJezdzcow.fxml");
    }
}
