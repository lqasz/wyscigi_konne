package wyscigi_konne.GUI.fxml.statystyka.gonitwa;

import wyscigi_konne.GUI.fxml.statystyka.gonitwa.DaneTabeliGonitw;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wyscigi_konne.Predykcja.DaneHistoryczne;

public class TabelaGonitwController implements Initializable {
    
    private static String data;
    private static String numer;
    private static final String[] parametry = new String[]{"miejsce","nazwa","jezdziec","nr startowy"};
    
    
    @FXML TableView<DaneTabeliGonitw> TabelaWynikowGonitw;
    @FXML TableColumn<DaneTabeliGonitw, Integer> Kolejnosc;
    @FXML TableColumn<DaneTabeliGonitw, String> NazwaKonia;
    @FXML TableColumn<DaneTabeliGonitw, String> Jezdziec;
    @FXML TableColumn<DaneTabeliGonitw, Integer> NrStartowy;
    
    private DaneHistoryczne daneHistoryczne = new DaneHistoryczne();
    
    ObservableList<HashMap<String, String>> Wynik = FXCollections.observableArrayList();
    ObservableList<DaneTabeliGonitw> WynikGonitwy = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){

        Kolejnosc.setCellValueFactory(new PropertyValueFactory<DaneTabeliGonitw,Integer>("Kolejnosc"));
        NazwaKonia.setCellValueFactory(new PropertyValueFactory<DaneTabeliGonitw,String>("NazwaKonia"));
        Jezdziec.setCellValueFactory(new PropertyValueFactory<DaneTabeliGonitw,String>("Jezdziec"));
        NrStartowy.setCellValueFactory(new PropertyValueFactory<DaneTabeliGonitw,Integer>("NrStartowy"));
        
        try {
            Wynik = daneHistoryczne.zwrocDaneGonitwy(data, numer, parametry);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaGonitwController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int kolejnosc = 0;
        String nazwaKonia = "";
        String jezdziec = "";
        int nrStartowy = 0;
        
        for(HashMap<String, String> map: Wynik){
            for(String klucz: map.keySet()) {
                switch(klucz) {
                    case "miejsce":
                        kolejnosc = Integer.valueOf(map.get(klucz));
                        break;
                    case "nazwa":
                        nazwaKonia = map.get(klucz).trim();
                        break;
                    case "jezdziec":
                        jezdziec = map.get(klucz).trim();
                        break;
                    case "nr startowy":
                        nrStartowy = Integer.valueOf(map.get(klucz));
                        break;
                }
                 
            }
            WynikGonitwy.add(new DaneTabeliGonitw(kolejnosc,nazwaKonia,jezdziec,nrStartowy));
        }
 
        TabelaWynikowGonitw.setItems(WynikGonitwy);
    } 
    

    public static void getDate(String data){
        TabelaGonitwController.data = data;
    }
    
    public static void getNumer(String numer){
        TabelaGonitwController.numer = numer;
    }
    
}
