package wyscigi_konne.GUI.fxml.przewidywanie;

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
import wyscigi_konne.GUI.WyscigiKonne;

public class SymulacjaController implements Initializable {
    
    private WyscigiKonne pokaz;
         
    @FXML public ComboBox WyborDystansu; 
    @FXML public ComboBox WyborKonia;
    @FXML public ComboBox WyborJezdzca;
    
    @FXML public TableView<ElementyTabeli> Tabela;
    @FXML public TableColumn<ElementyTabeli,String> KolumnaKoni;
    @FXML public TableColumn<ElementyTabeli,String> KolumnaJezdzcow;
   
    ObservableList<String> Dystans = FXCollections.observableArrayList();
    ObservableList<String> Konie = FXCollections.observableArrayList();
    ObservableList<String> Jezdzcy = FXCollections.observableArrayList();
    
    static volatile ObservableList<ElementyTabeli> daneTabeli = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Konie.add("Rafał");
        Konie.add("Franek");
        
        Jezdzcy.add("Piotr");
        Jezdzcy.add("Paweł");
    
        WyborDystansu.setItems(Dystans);
        WyborKonia.setItems(Konie);
        WyborJezdzca.setItems(Jezdzcy);
        
        KolumnaKoni.setCellValueFactory(new PropertyValueFactory<ElementyTabeli,String>("kon"));
        KolumnaJezdzcow.setCellValueFactory(new PropertyValueFactory<ElementyTabeli,String>("jezdziec"));
    }    
    
    //Metoda dodaje zespół do tabeli i usuwa wybrane pozycje z Comboboxów
    @FXML
    private void dodajZespol(ActionEvent event) {
        
        daneTabeli.add(new ElementyTabeli(WyborKonia.getSelectionModel().getSelectedItem().toString(), 
                                          WyborJezdzca.getSelectionModel().getSelectedItem().toString()));
        Konie.remove(WyborKonia.getSelectionModel().getSelectedItem().toString());
        Jezdzcy.remove(WyborJezdzca.getSelectionModel().getSelectedItem().toString());
       
        Tabela.setItems(daneTabeli); 
        WyborKonia.setItems(Konie);
        WyborJezdzca.setItems(Jezdzcy);
    }
    
    //Metoda usuwa zaznaczony zespół z tabeli i dodaje wybrane pozycje z Comboboxów
    @FXML
    private void usunZespol(ActionEvent event){
        
       ElementyTabeli selectedItem = Tabela.getSelectionModel().getSelectedItem();
       Konie.add(selectedItem.getKon());
       Jezdzcy.add(selectedItem.getJezdziec());
       
       Tabela.getItems().remove(selectedItem);
       WyborKonia.setItems(Konie);
       WyborJezdzca.setItems(Jezdzcy);
    }
    
    //Metoda otwiera nowe okno
    @FXML
    private void goNewWindow(ActionEvent event) throws IOException {
        
        WykresController.setDane(daneTabeli);
        pokaz.showNewWindow("fxml/przewidywanie/Wykres.fxml");
        
    }

    //Metoda zmienia widok bieżącego okna
    @FXML
    private void goMainItems(ActionEvent event) throws IOException {
        
        pokaz.showView("fxml/MainItems.fxml");
    }
}

