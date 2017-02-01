package wyscigi_konne.GUI.fxml.statystyka.jezdziec;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelaJezdzcowController implements Initializable {
    
    @FXML TableView<DaneTabeliJezdzcow> Tabela;
    @FXML TableColumn<DaneTabeliJezdzcow, String> Data;
    @FXML TableColumn<DaneTabeliJezdzcow, String> NazwaKonia;
    @FXML TableColumn<DaneTabeliJezdzcow, Integer> NrStartowy;
    @FXML TableColumn<DaneTabeliJezdzcow, Integer> Kolejnosc;

    @FXML LineChart<String, Number> Wykres;
    
    ObservableList<DaneTabeliJezdzcow> Wyniki = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Data.setCellValueFactory(new PropertyValueFactory<DaneTabeliJezdzcow, String>("Data"));
        NazwaKonia.setCellValueFactory(new PropertyValueFactory<DaneTabeliJezdzcow, String>("NazwaKonia"));
        NrStartowy.setCellValueFactory(new PropertyValueFactory<DaneTabeliJezdzcow, Integer>("NrStartowy"));
        Kolejnosc.setCellValueFactory(new PropertyValueFactory<DaneTabeliJezdzcow, Integer>("Kolejnosc"));
        
        XYChart.Series<String, Number> DaneDoWykresu = new XYChart.Series<>();
        
        Wyniki.add(new DaneTabeliJezdzcow("12/12/2016","Abdul",4,5));
        Wyniki.add(new DaneTabeliJezdzcow("12/13/2016","Cbdul",2,1));
        Wyniki.add(new DaneTabeliJezdzcow("12/14/2016","Dbdul",6,2));
        Wyniki.add(new DaneTabeliJezdzcow("12/15/2016","Fbdul",5,3));
        Wyniki.add(new DaneTabeliJezdzcow("12/16/2016","Tbdul",2,4));
        Wyniki.add(new DaneTabeliJezdzcow("12/17/2016","Rbdul",1,6));
        Wyniki.add(new DaneTabeliJezdzcow("12/18/2016","Wbdul",7,7));
        
        Tabela.setItems(Wyniki);
        
        int i;
        for(i = 0;i < Wyniki.size(); i++){
            
            DaneDoWykresu.getData().add(new XYChart.Data(Wyniki.get(i).getData(),Wyniki.get(i).getKolejnosc()*-1));
        }
   
        Wykres.getData().addAll(DaneDoWykresu);
    }       
}
