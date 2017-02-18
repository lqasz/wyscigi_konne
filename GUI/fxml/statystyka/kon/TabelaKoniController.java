package wyscigi_konne.GUI.fxml.statystyka.kon;

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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wyscigi_konne.GUI.fxml.statystyka.gonitwa.TabelaGonitwController;
import wyscigi_konne.Predykcja.DaneHistoryczne;

public class TabelaKoniController implements Initializable {


    private static String kon;
    private static String start;
    
    private DaneHistoryczne daneHistoryczne = new DaneHistoryczne();
    private static final String[] parametry = new String[]{"data gonitwy","jezdziec","nr startowy","miejsce"};
       
    @FXML TableView<DaneTabeliKoni> Tabela;
    @FXML TableColumn<DaneTabeliKoni, String> Data;
    @FXML TableColumn<DaneTabeliKoni, String> Jezdziec;
    @FXML TableColumn<DaneTabeliKoni, Integer> NrStartowy;
    @FXML TableColumn<DaneTabeliKoni, Integer> Kolejnosc;

    @FXML LineChart<String, Number> Wykres;
    
    ObservableList<HashMap<String, String>> Wynik = FXCollections.observableArrayList();
    ObservableList<DaneTabeliKoni> Wyniki = FXCollections.observableArrayList();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Data.setCellValueFactory(new PropertyValueFactory<DaneTabeliKoni, String>("Data"));
        Jezdziec.setCellValueFactory(new PropertyValueFactory<DaneTabeliKoni, String>("Jezdziec"));
        NrStartowy.setCellValueFactory(new PropertyValueFactory<DaneTabeliKoni, Integer>("NrStartowy"));
        Kolejnosc.setCellValueFactory(new PropertyValueFactory<DaneTabeliKoni, Integer>("Kolejnosc"));
        
        XYChart.Series<String, Number> DaneDoWykresu = new XYChart.Series<>();
        
        try {
            Wynik = daneHistoryczne.zwrocDaneGonitwDlaObiektu("kon", kon, start, parametry);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaKoniController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        String data = "";
        String jezdziec = "";
        int nrStartowy = 0;
        int kolejnosc = 0;
        
        System.out.println(Wynik);
        for(HashMap<String, String> map: Wynik){
            for(String klucz: map.keySet()) {
                switch(klucz) {
                   
                    case "data gonitwy":
                        data = map.get(klucz).trim();
                        break;
                    case "jezdziec":
                        jezdziec = map.get(klucz).trim();
                        break;
                    case "nr startowy":
                        nrStartowy = Integer.valueOf(map.get(klucz));
                        break;
                    case "miejsce":
                        kolejnosc = Integer.valueOf(map.get(klucz));
                    break;
                }
                 
            }
            Wyniki.add(new DaneTabeliKoni(data,jezdziec,nrStartowy,kolejnosc));
        }
        
        Tabela.setItems(Wyniki);
        
        int i;
        for(i = 0;i < Wyniki.size(); i++){
            
            DaneDoWykresu.getData().add(new XYChart.Data(Wyniki.get(i).getData(),Wyniki.get(i).getKolejnosc()*-1));
        }
   
        Wykres.getData().addAll(DaneDoWykresu);
    } 
    
    public static void getKon(String kon){
        TabelaKoniController.kon = kon;
    }
    
    public static void getStart(String start){
        TabelaKoniController.start = start;
    }
}

