package wyscigi_konne.GUI.fxml.statystyka.kon;

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

public class TabelaKoniController implements Initializable {

       
    @FXML TableView<DaneTabeliKoni> Tabela;
    @FXML TableColumn<DaneTabeliKoni, String> Data;
    @FXML TableColumn<DaneTabeliKoni, String> Jezdziec;
    @FXML TableColumn<DaneTabeliKoni, Integer> NrStartowy;
    @FXML TableColumn<DaneTabeliKoni, Integer> Kolejnosc;

    @FXML LineChart<String, Number> Wykres;
    
    ObservableList<DaneTabeliKoni> Wyniki = FXCollections.observableArrayList();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Data.setCellValueFactory(new PropertyValueFactory<DaneTabeliKoni, String>("Data"));
        Jezdziec.setCellValueFactory(new PropertyValueFactory<DaneTabeliKoni, String>("Jezdziec"));
        NrStartowy.setCellValueFactory(new PropertyValueFactory<DaneTabeliKoni, Integer>("NrStartowy"));
        Kolejnosc.setCellValueFactory(new PropertyValueFactory<DaneTabeliKoni, Integer>("Kolejnosc"));
        
        XYChart.Series<String, Number> DaneDoWykresu = new XYChart.Series<>();
        
        Wyniki.add(new DaneTabeliKoni("12/12/2016","Abdul",4,5));
        Wyniki.add(new DaneTabeliKoni("12/13/2016","Cbdul",2,1));
        Wyniki.add(new DaneTabeliKoni("12/14/2016","Dbdul",6,2));
        Wyniki.add(new DaneTabeliKoni("12/15/2016","Fbdul",5,3));
        Wyniki.add(new DaneTabeliKoni("12/16/2016","Tbdul",2,4));
        Wyniki.add(new DaneTabeliKoni("12/17/2016","Rbdul",1,6));
        Wyniki.add(new DaneTabeliKoni("12/18/2016","Wbdul",7,7));
        
        Tabela.setItems(Wyniki);
        
        int i;
        for(i = 0;i < Wyniki.size(); i++){
            
            DaneDoWykresu.getData().add(new XYChart.Data(Wyniki.get(i).getData(),Wyniki.get(i).getKolejnosc()*-1));
        }
   
        Wykres.getData().addAll(DaneDoWykresu);
    }       
}

