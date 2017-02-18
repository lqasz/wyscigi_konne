package wyscigi_konne.GUI.fxml.statystyka.gonitwa;

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


public class WynikiGonitwController implements Initializable{
    
    private WyscigiKonne pokaz;
    private DaneHistoryczne daneHistoryczne = new DaneHistoryczne();

    @FXML BorderPane tlo;
    
    @FXML Label opisD;
    @FXML Label opisB;
    
    @FXML Button wyniki;
    @FXML Button powrot;
    
    @FXML public ComboBox WyborDaty;
    @FXML public ComboBox WyborBiegu;

    ObservableList<String> daty = FXCollections.observableArrayList();
    ObservableList<Integer> biegi = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            daty = daneHistoryczne.polaDoComboBox("gonitwa","data gonitwy");
        } catch (SQLException ex) {
            Logger.getLogger(WynikiGonitwController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        WyborDaty.setItems(daty);
    }
    
    public void dodajBiegi() throws SQLException{
         
        biegi = daneHistoryczne.iloscGonitw(WyborDaty.getSelectionModel().getSelectedItem().toString());
        
        WyborBiegu.setItems(biegi);
    }

    @FXML
    public void goDotychczasoweWyniki() throws IOException{  
        
        pokaz.showView("fxml/statystyka/DotychczasoweWyniki.fxml");
    }
   
    @FXML
    public void goNewWindow() throws IOException{
            
        if(WyborDaty.getSelectionModel().getSelectedItem() == null){
            
            AllertBoxController.getTekst("Wybierz datę gonitwy");
            pokaz.showAlertBox();
            
        }else if(WyborBiegu.getSelectionModel().getSelectedItem() == null){
            
            AllertBoxController.getTekst("Wybierz wyścig");
            pokaz.showAlertBox();
            
        }else{
            TabelaGonitwController.getDate(WyborDaty.getSelectionModel().getSelectedItem().toString());
            TabelaGonitwController.getNumer(WyborBiegu.getSelectionModel().getSelectedItem().toString());
            pokaz.showNewWindow("fxml/statystyka/gonitwa/TabelaGonitw.fxml");
        } 

    }
}
