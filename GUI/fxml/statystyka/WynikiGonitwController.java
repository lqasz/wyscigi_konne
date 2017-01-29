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
    private DaneHistoryczne dane;
    
    @FXML ComboBox WyborDaty;
    @FXML ComboBox WyborGodziny;
    
    ObservableList<String> daty = FXCollections.observableArrayList();
    ObservableList<String> godziny = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        

    }
    
    /*
for(HashMap<String, String> obiekt: daneGonitwKonia.values()) {
                for(String wartosc: obiekt.keySet()) {
                    System.out.println(wartosc);
                    System.out.println(obiekt.get(wartosc));
                }
            }
*/
   
    
    @FXML
    public void goDotychczasoweWyniki() throws IOException{  

        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    }
   
    @FXML
    public void goNewWindow() throws IOException{
        
        pokaz.showNewWindow("fxml/statystyka/TabelaGonitw.fxml");
    }
}
