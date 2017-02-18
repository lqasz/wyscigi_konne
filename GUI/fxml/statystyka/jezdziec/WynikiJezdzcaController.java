package wyscigi_konne.GUI.fxml.statystyka.jezdziec;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import wyscigi_konne.GUI.WyscigiKonne;
import wyscigi_konne.GUI.fxml.AllertBoxController;
import wyscigi_konne.Predykcja.DaneHistoryczne;

public class WynikiJezdzcaController implements Initializable{
    
    private WyscigiKonne pokaz; 
    private DaneHistoryczne daneHistoryczne = new DaneHistoryczne();
    
    @FXML BorderPane tlo;
    
    @FXML Label opisJ;
    @FXML Label opisS;
    
    @FXML Button wyniki;
    @FXML Button powrot;
         
    @FXML public ComboBox WyborJezdzca;
    @FXML public ComboBox WyborSezonu;
 
    ObservableList<String> jezdzcy = FXCollections.observableArrayList();
    ObservableList<String> starty = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            jezdzcy = daneHistoryczne.polaDoComboBox("dzokeje","jezdziec");
        } catch (SQLException ex) {
            Logger.getLogger(WynikiJezdzcaController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        WyborJezdzca.setItems(jezdzcy);
    }
    
    public void dodajBiegi() throws SQLException{
         
        starty = daneHistoryczne.polaDoComboBox("gonitwa", "YEAR(`data gonitwy`)");
        
        WyborSezonu.setItems(starty);
    }
    
    @FXML
    public void goDotychczasoweWyniki() throws IOException{  
        
        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    }
        
    @FXML
    public void goNewWindow() throws IOException{
        if(WyborJezdzca.getSelectionModel().getSelectedItem() == null){
            
            AllertBoxController.getTekst("Wybierz jeźdzca");
            pokaz.showAlertBox();
            
        }else if(WyborSezonu.getSelectionModel().getSelectedItem() == null){
            
            AllertBoxController.getTekst("Wybierz sezon");
            pokaz.showAlertBox();
            
        }else{
            TabelaJezdzcowController.getJezdziec(WyborJezdzca.getSelectionModel().getSelectedItem().toString());
            TabelaJezdzcowController.getStart(WyborSezonu.getSelectionModel().getSelectedItem().toString());
            pokaz.showNewWindow("fxml/statystyka/jezdziec/TabelaJezdzcow.fxml");
        }   
    }
}
