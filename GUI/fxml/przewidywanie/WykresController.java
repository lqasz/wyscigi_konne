package wyscigikonne.fxml.przewidywanie;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import wyscigikonne.DaneDoTestow;

public class WykresController implements Initializable {
   
   DaneDoTestow test = new DaneDoTestow();

    @FXML PieChart wykres;
    
    ObservableList<Data> dane = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
        dane.clear();
        
        DaneDoTestow test = new DaneDoTestow();
        for(int i = 0; i < test.tabela.length; i++){
            
            dane.add(new PieChart.Data(test.tabelaI[i],test.tabela[i]));
        }
        wykres.setLegendSide(Side.RIGHT);
        wykres.setData(dane);
    }
}    
    

