package wyscigi_konne.GUI.fxml.statystyka;

import java.io.IOException;
import javafx.fxml.FXML;
import wyscigi_konne.GUI.WyscigiKonne;

public class DotychczasoweWynikiController {
        
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
        pokaz.showView("fxml/statystyka/jezdziec/WynikiJeźdźca.fxml");
    }  
    
    @FXML
    public void goWynikiKonia() throws IOException{
        pokaz.showView("fxml/statystyka/kon/WynikiKonia.fxml");
    }  
}
