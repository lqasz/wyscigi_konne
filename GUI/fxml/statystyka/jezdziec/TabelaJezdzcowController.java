package wyscigi_konne.GUI.fxml.statystyka.jezdziec;

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
import wyscigi_konne.Predykcja.DaneHistoryczne;

public class TabelaJezdzcowController implements Initializable {
    
    private static String jezdziec;
    private static String start;
    
    private DaneHistoryczne daneHistoryczne = new DaneHistoryczne();
    private static final String[] parametry = new String[]{"data gonitwy","nazwa","nr startowy","miejsce"};
    
    @FXML TableView<DaneTabeliJezdzcow> Tabela;
    @FXML TableColumn<DaneTabeliJezdzcow, String> Data;
    @FXML TableColumn<DaneTabeliJezdzcow, String> NazwaKonia;
    @FXML TableColumn<DaneTabeliJezdzcow, Integer> NrStartowy;
    @FXML TableColumn<DaneTabeliJezdzcow, Integer> Kolejnosc;

    @FXML LineChart<String, Number> Wykres;
    
    ObservableList<HashMap<String, String>> Wynik = FXCollections.observableArrayList();
    ObservableList<DaneTabeliJezdzcow> Wyniki = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Data.setCellValueFactory(new PropertyValueFactory<DaneTabeliJezdzcow, String>("Data"));
        NazwaKonia.setCellValueFactory(new PropertyValueFactory<DaneTabeliJezdzcow, String>("NazwaKonia"));
        NrStartowy.setCellValueFactory(new PropertyValueFactory<DaneTabeliJezdzcow, Integer>("NrStartowy"));
        Kolejnosc.setCellValueFactory(new PropertyValueFactory<DaneTabeliJezdzcow, Integer>("Kolejnosc"));
        
        XYChart.Series<String, Number> DaneDoWykresu = new XYChart.Series<>();
        XYChart.Series<String, Number> Srednia = new XYChart.Series<>();
        DaneDoWykresu.setName(jezdziec);
        Srednia.setName("średnia");
        
        try {
            Wynik = daneHistoryczne.zwrocDaneGonitwDlaObiektu("dzokej", jezdziec, start, parametry);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaJezdzcowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String data = "";
        String kon = "";
        int nrStartowy = 0;
        int kolejnosc = 0;
        
        System.out.println(Wynik);
        for(HashMap<String, String> map: Wynik){
            for(String klucz: map.keySet()) {
                switch(klucz) {
                   
                    case "data gonitwy":
                        data = map.get(klucz).trim();
                        break;
                    case "nazwa":
                        kon = map.get(klucz).trim();
                        break;
                    case "nr startowy":
                        nrStartowy = Integer.valueOf(map.get(klucz));
                        break;
                    case "miejsce":
                        kolejnosc = Integer.valueOf(map.get(klucz));
                    break;
                }
                 
            }
            Wyniki.add(new DaneTabeliJezdzcow(data,kon,nrStartowy,kolejnosc));
        }
        Tabela.setItems(Wyniki);
        
        int i;
 
        for(i = 0;i < Wyniki.size(); i++){
            
            DaneDoWykresu.getData().add(new XYChart.Data("wyscig: " + (i + 1) + " " + Wyniki.get(i).getData(),Wyniki.get(i).getKolejnosc()*-1));
        }
        Wykres.getData().addAll(DaneDoWykresu);
    } 
    
    public static void getJezdziec(String jezdziec){
        TabelaJezdzcowController.jezdziec = jezdziec;
    }
    
    public static void getStart(String start){
        TabelaJezdzcowController.start = start;
    }

}
