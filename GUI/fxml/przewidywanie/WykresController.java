package wyscigi_konne.GUI.fxml.przewidywanie;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import wyscigi_konne.Predykcja.GrupowanieZespolow;

public class WykresController implements Initializable {

    @FXML PieChart wykres;
    
    GrupowanieZespolow wynik;
    static HashMap<String, Double> mapa = new HashMap();
    ObservableList<Data> dane = FXCollections.observableArrayList();
    static ObservableList<ElementyTabeli> daneDoWykresu = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){ 
 
        int i, suma;
        suma=0;
        for(i=0;i<daneDoWykresu.size();i++){
            suma += mapa.get(daneDoWykresu.get(i).getKon());
        }
        
        for(i=0;i<daneDoWykresu.size();i++){
            
            dane.add(new PieChart.Data("Zespół: " + daneDoWykresu.get(i).getJezdziec() 
                                           + ", " + daneDoWykresu.get(i).getKon() +" "+ (int)((mapa.get(daneDoWykresu.get(i).getKon()) / suma)*100) +"%",
                                                    mapa.get(daneDoWykresu.get(i).getKon())));
        }
 
        wykres.setLegendSide(Side.LEFT);
        wykres.setData(dane);
    }
    
    public static void getDane(ObservableList<ElementyTabeli> daneTabeli) {
        daneDoWykresu = daneTabeli;
    }
    
    public static void getMape(HashMap<String, Double> mapa){
        WykresController.mapa = mapa;
    }
}    
    

