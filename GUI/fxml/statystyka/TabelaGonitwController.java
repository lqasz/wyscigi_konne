package wyscigi_konne.GUI.fxml.statystyka;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelaGonitwController implements Initializable {
    
    @FXML TableView<DaneTabeliGonitw> TabelaWynikowGonitw;
    @FXML TableColumn<DaneTabeliGonitw, Integer> Kolejnosc;
    @FXML TableColumn<DaneTabeliGonitw, String> NazwaKonia;
    @FXML TableColumn<DaneTabeliGonitw, String> Jezdziec;
    @FXML TableColumn<DaneTabeliGonitw, Integer> NrStartowy;
    
    ObservableList<DaneTabeliGonitw> WynikGonitwy = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){

        Kolejnosc.setCellValueFactory(new PropertyValueFactory<DaneTabeliGonitw,Integer>("kolejnosc"));
        NazwaKonia.setCellValueFactory(new PropertyValueFactory<DaneTabeliGonitw,String>("nazwaKonia"));
        Jezdziec.setCellValueFactory(new PropertyValueFactory<DaneTabeliGonitw,String>("jezdziec"));
        NrStartowy.setCellValueFactory(new PropertyValueFactory<DaneTabeliGonitw,Integer>("nrStartowy"));
        
                
        WynikGonitwy.add(new DaneTabeliGonitw(1, "Rafał", "Koleś GIT", 5));
        WynikGonitwy.add(new DaneTabeliGonitw(2, "Płotka","Geralt", 3));
        WynikGonitwy.add(new DaneTabeliGonitw(3, "Brego", "Aragorn", 4));
        WynikGonitwy.add(new DaneTabeliGonitw(4, "Osioł", "Shrek", 6));
        WynikGonitwy.add(new DaneTabeliGonitw(5, "Szerszeń", "Wojtas",1));
        WynikGonitwy.add(new DaneTabeliGonitw(6, "Rafał", "Paweł", 2));

        TabelaWynikowGonitw.setItems(WynikGonitwy);
    }    
    
}
