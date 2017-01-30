package wyscigi_konne.GUI.fxml.statystyka;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import wyscigi_konne.GUI.WyscigiKonne;
import wyscigi_konne.Predykcja.DaneHistoryczne;


public class WynikiGonitwController implements Initializable{
    
    private WyscigiKonne pokaz;

    @FXML public ComboBox WyborDaty;
    @FXML public ComboBox WyborBiegu;
 
    ObservableList<String> daty = FXCollections.observableArrayList();
    ObservableList<String> biegi = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        daty.add("22/12/2015");
        daty.add("15/12/2015");
        daty.add("8/12/2015");
        daty.add("1/12/2015");
        daty.add("25/11/2015");
        
        biegi.add("1");
        biegi.add("2");
        biegi.add("3");
        biegi.add("4");
        biegi.add("5");
        
        WyborDaty.setItems(daty);
        WyborBiegu.setItems(biegi);
    }

    @FXML
    public void goDotychczasoweWyniki() throws IOException{  
        
        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    }
   
    @FXML
    public void goNewWindow() throws IOException{
    
        pokaz.showNewWindow("fxml/statystyka/TabelaGonitw.fxml");
    }
}
