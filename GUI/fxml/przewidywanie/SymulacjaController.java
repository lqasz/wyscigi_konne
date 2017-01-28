package wyscigikonne.fxml.przewidywanie;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wyscigikonne.WyscigiKonne;

public class SymulacjaController implements Initializable {
    
    private WyscigiKonne pokaz;
    
    @FXML public ComboBox WyborDystansu; 
    @FXML public ComboBox WyborKonia;
    @FXML public ComboBox WyborJezdzca;
    
    @FXML public TableView<ElementyTabeli> Tabela;
    @FXML public TableColumn<ElementyTabeli,String> KolumnaKoni;
    @FXML public TableColumn<ElementyTabeli,String> KolumnaJezdzcow;
   
    ObservableList<Number> Dystans = FXCollections.observableArrayList();
    ObservableList<String> Konie = FXCollections.observableArrayList();
    ObservableList<String> Jezdzcy = FXCollections.observableArrayList();
    
    ObservableList<ElementyTabeli> daneTabeli = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       Dystans.add(1400);
        Dystans.add(1500);
        Dystans.add(1600);
        Dystans.add(1700);
        
        Konie.add("Koń Rafał");
        Konie.add("Mutang");
        Konie.add("Kucyk pony");
        
        Jezdzcy.add("Gandalf");
        Jezdzcy.add("Vegeta");
        Jezdzcy.add("Geralt z Rivi");
                 
        WyborDystansu.setItems(Dystans);
        WyborKonia.setItems(Konie);
        WyborJezdzca.setItems(Jezdzcy);
        
        KolumnaKoni.setCellValueFactory(new PropertyValueFactory<ElementyTabeli,String>("KolunaKoni"));
        KolumnaJezdzcow.setCellValueFactory(new PropertyValueFactory<ElementyTabeli,String>("KolumnaJezdzcow"));
        
        daneTabeli.add(new ElementyTabeli("Kot","Pies"));
        daneTabeli.add(new ElementyTabeli("Kaczka","Kura"));
        Tabela.setItems(daneTabeli);
        
    }    

    @FXML
    private void goNewWindow(ActionEvent event) throws IOException {
        
        pokaz.showNewWindow("fxml/przewidywanie/Wykres.fxml");
    }

    @FXML
    private void goMainItems(ActionEvent event) throws IOException {
        
        pokaz.showView("fxml/MainItems.fxml");
    }

    @FXML
    private void dodajZespol(ActionEvent event) {
        
        daneTabeli.add(new ElementyTabeli(WyborKonia.getValue().toString(),WyborJezdzca.getValue().toString()));
        Tabela.setItems(daneTabeli);
        System.out.println(WyborKonia.toString());
        
    }
}

/*
for(HashMap<String, String> obiekt: daneGonitwKonia.values()) {
                for(String wartosc: obiekt.keySet()) {
                    System.out.println(wartosc);
                    System.out.println(obiekt.get(wartosc));
                }
            }
*/