package wyscigi_konne.GUI.fxml.statystyka.kon;

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

public class WynikiKoniaController implements Initializable{
        
    private WyscigiKonne pokaz;
    private DaneHistoryczne daneHistoryczne = new DaneHistoryczne();
        
    @FXML BorderPane tlo;
    
    @FXML Label opisK;
    @FXML Label opisS;
    
    @FXML Button wyniki;
    @FXML Button powrot;
    
    @FXML public ComboBox WyborKonia;
    @FXML public ComboBox WyborSezonu;
 
    ObservableList<String> konie = FXCollections.observableArrayList();
    ObservableList<String> starty = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            konie = daneHistoryczne.polaDoComboBox("konie","nazwa");
        } catch (SQLException ex) {
            Logger.getLogger(WynikiKoniaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        WyborKonia.setItems(konie);
    }
    
    public void dodajBiegi() throws SQLException{
         
        starty = daneHistoryczne.polaDoComboBox("gonitwa","YEAR(`data gonitwy`)");
        
        WyborSezonu.setItems(starty);
    }
    
    @FXML
    public void goDotychczasoweWyniki() throws IOException{  
        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    }
      
    @FXML
    public void goNewWindow() throws IOException{
        
             if(WyborKonia.getSelectionModel().getSelectedItem() == null){
            
            AllertBoxController.getTekst("Wybierz konia");
            pokaz.showAlertBox();
            
        }else if(WyborSezonu.getSelectionModel().getSelectedItem() == null){
            
            AllertBoxController.getTekst("Wybierz sezon");
            pokaz.showAlertBox();
            
        }else{
            TabelaKoniController.getKon(WyborKonia.getSelectionModel().getSelectedItem().toString());
            TabelaKoniController.getStart(WyborSezonu.getSelectionModel().getSelectedItem().toString());
            pokaz.showNewWindow("fxml/statystyka/kon/TabelaKoni.fxml");
        } 
    }
}
