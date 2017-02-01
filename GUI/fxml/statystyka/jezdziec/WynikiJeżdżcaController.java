package wyscigi_konne.GUI.fxml.statystyka.jezdziec;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import wyscigi_konne.GUI.WyscigiKonne;

public class WynikiJeżdżcaController implements Initializable{
    
    private WyscigiKonne pokaz;
    
        
    @FXML public ComboBox WyborJezdzca;
    @FXML public ComboBox WyborSezonu;
 
    ObservableList<String> konie = FXCollections.observableArrayList();
    ObservableList<String> starty = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        konie.add("Rafał");
        konie.add("Bartek");
        konie.add("Paweł");
        konie.add("Artur");
        konie.add("Kucyk");
        
        starty.add("2014");
        starty.add("2015");
        starty.add("2016");
        starty.add("2017");
        
        WyborJezdzca.setItems(konie);
        WyborSezonu.setItems(starty);
    }
    
    @FXML
    public void goDotychczasoweWyniki() throws IOException{  
        
        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    }
        
    @FXML
    public void goNewWindow() throws IOException{
        
        pokaz.showNewWindow("fxml/statystyka/jezdziec/TabelaJezdzcow.fxml");
    }
}
