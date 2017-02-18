package wyscigi_konne.GUI.fxml.przewidywanie;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

public class WykresController implements Initializable {

    @FXML PieChart wykres;
   
    ObservableList<Data> dane = FXCollections.observableArrayList();
    static ObservableList<ElementyTabeli> daneDoWykresu = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){ 
 
        int i;
        for(i=0;i<daneDoWykresu.size();i++){
            dane.add(new PieChart.Data("Jeździec:  " + daneDoWykresu.get(i).getJezdziec() + " na koniu: " + daneDoWykresu.get(i).getKon(),i));
        }
 
        wykres.setLegendSide(Side.RIGHT);
        wykres.setData(dane);
    }  

    public static void getDane(ObservableList<ElementyTabeli> daneTabeli) {
        daneDoWykresu = daneTabeli;
    }
}    
    

