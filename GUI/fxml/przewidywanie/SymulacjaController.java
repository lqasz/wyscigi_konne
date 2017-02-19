package wyscigi_konne.GUI.fxml.przewidywanie;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import wyscigi_konne.GUI.WyscigiKonne;
import wyscigi_konne.GUI.fxml.AllertBoxController;
import wyscigi_konne.Predykcja.DaneHistoryczne;
import wyscigi_konne.Predykcja.GrupowanieZespolow;


public class SymulacjaController implements Initializable {
    
    private WyscigiKonne pokaz;
    
    private DaneHistoryczne daneHistoryczne = new DaneHistoryczne();

    
    @FXML BorderPane tlo;
    
    @FXML Label opisWD;
    @FXML Label opisWK;
    @FXML Label opisWJ;
    
    @FXML Button dodaj;
    @FXML Button usun;
    @FXML Button wyniki;
    @FXML Button powrot;

         
    @FXML public ComboBox WyborDystansu; 
    @FXML public ComboBox WyborKonia;
    @FXML public ComboBox WyborJezdzca;
    
    @FXML public TableView<ElementyTabeli> Tabela;
    @FXML public TableColumn<ElementyTabeli,String> KolumnaKoni;
    @FXML public TableColumn<ElementyTabeli,String> KolumnaJezdzcow;
    
    ObservableList<String> dystans = FXCollections.observableArrayList();
    ObservableList<String> konie = FXCollections.observableArrayList();
    ObservableList<String> jezdzcy = FXCollections.observableArrayList();
    
    static volatile ObservableList<ElementyTabeli> daneTabeli = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        KolumnaKoni.setCellValueFactory(new PropertyValueFactory<ElementyTabeli,String>("kon"));
        KolumnaJezdzcow.setCellValueFactory(new PropertyValueFactory<ElementyTabeli,String>("jezdziec"));
        
        try {
            dystans = daneHistoryczne.polaDoComboBox("informacje", "dystans");
        } catch (SQLException ex) {
            Logger.getLogger(SymulacjaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WyborDystansu.setItems(dystans);
        
        try {
            konie = daneHistoryczne.polaDoComboBox("konie", "nazwa");
        } catch (SQLException ex) {
            Logger.getLogger(SymulacjaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WyborKonia.setItems(konie);
        
        try {
            jezdzcy = daneHistoryczne.polaDoComboBox("dzokeje", "jezdziec");
        } catch (SQLException ex) {
            Logger.getLogger(SymulacjaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WyborJezdzca.setItems(jezdzcy);        
    } 
    
    public HashMap<String[],String> zwrocZespoly(){
        
        String dystans;
        
        HashMap<String[], String> zespoly = new HashMap<>();
            for(int i = 0; i<daneTabeli.size();i++){
                dystans = WyborDystansu.getSelectionModel().getSelectedItem().toString();
                zespoly.put(new String[]{daneTabeli.get(i).getKon().toString(), daneTabeli.get(i).getJezdziec().toString()},dystans);   
            }
            
            System.out.println(zespoly);
            
        return zespoly;
    }
    
    //Metoda dodaje zespół do tabeli i usuwa wybrane pozycje z Comboboxów
    @FXML
    private void dodajZespol(ActionEvent event) throws IOException {
         if(WyborKonia.getSelectionModel().getSelectedItem() == null){
            
            AllertBoxController.getTekst("Wybierz konia");
            pokaz.showAlertBox();
            
         }else if(WyborJezdzca.getSelectionModel().getSelectedItem() == null){
             
            AllertBoxController.getTekst("Wybierz jeźdżca");
            pokaz.showAlertBox();
            
         }else{
            
            daneTabeli.add(new ElementyTabeli(WyborKonia.getSelectionModel().getSelectedItem().toString(), 
                                          WyborJezdzca.getSelectionModel().getSelectedItem().toString()));
            konie.remove(WyborKonia.getSelectionModel().getSelectedItem().toString());
            jezdzcy.remove(WyborJezdzca.getSelectionModel().getSelectedItem().toString());
       
            Tabela.setItems(daneTabeli); 
            WyborKonia.setItems(konie);
            WyborJezdzca.setItems(jezdzcy);
        }
    }
    
    //Metoda usuwa zaznaczony zespół z tabeli i dodaje wybrane pozycje z Comboboxów
    @FXML
    private void usunZespol(ActionEvent event){
        
       ElementyTabeli selectedItem = Tabela.getSelectionModel().getSelectedItem();
       konie.add(selectedItem.getKon());
       jezdzcy.add(selectedItem.getJezdziec());
       
       Tabela.getItems().remove(selectedItem);
       WyborKonia.setItems(konie);
       WyborJezdzca.setItems(jezdzcy);
    }
    
    private HashMap<String, Double> pobierzWyniki() throws SQLException, ParserConfigurationException, SAXException, IOException{
        
        GrupowanieZespolow grupowanieZespolow = new GrupowanieZespolow(zwrocZespoly());
        HashMap<String, Double> wskazniki = grupowanieZespolow.zwrocWskaznikiDlaZespolow();
        System.out.println(wskazniki);
        return wskazniki;
    } 
    
    //Metoda otwiera nowe okno
    @FXML
    private void goNewWindow(ActionEvent event) throws IOException, SQLException, ParserConfigurationException, SAXException {
    
        if(WyborDystansu.getSelectionModel().getSelectedItem() == null){
            
            AllertBoxController.getTekst("Wybierz dystans gonitwy");
            pokaz.showAlertBox();
            
        }else if(daneTabeli.size()<2){
            
            AllertBoxController.getTekst("Wybierz co najmniej 2 zespoły");
            pokaz.showAlertBox();
            
        }else{
            WykresController.getMape(pobierzWyniki());

            WykresController.getDane(daneTabeli);  
            pokaz.showNewWindow("fxml/przewidywanie/Wykres.fxml");
        } 
    }

    //Metoda zmienia widok bieżącego okna
    @FXML
    private void goMainItems(ActionEvent event) throws IOException{

        pokaz.showView("fxml/MainItems.fxml");
    }
}

