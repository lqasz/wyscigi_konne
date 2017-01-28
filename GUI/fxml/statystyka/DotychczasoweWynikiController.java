package wyscigikonne.fxml.statystyka;

import java.io.IOException;
import javafx.fxml.FXML;
import wyscigikonne.WyscigiKonne;

public class DotychczasoweWynikiController {
        
    private WyscigiKonne pokaz;
    
    @FXML
    public void goMainItems() throws IOException{
       pokaz.showView("fxml/MainItems.fxml");
    }  
    
    @FXML
    public void goWynikiGonitw() throws IOException{
        pokaz.showView("fxml/statystyka/WynikiGonitw.fxml");
    }  
    
    @FXML
    public void goWynikiJeźdźca() throws IOException{
        pokaz.showView("fxml/statystyka/WynikiJeźdźca.fxml");
    }  
    
    @FXML
    public void goWynikiKonia() throws IOException{
        pokaz.showView("fxml/statystyka/WynikiKonia.fxml");
    }  
}
