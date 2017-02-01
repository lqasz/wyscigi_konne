package wyscigi_konne.GUI.fxml.statystyka.jezdziec;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DaneTabeliJezdzcow {
    
    private final SimpleStringProperty Data;
    private final SimpleStringProperty NazwaKonia;
    private final SimpleIntegerProperty NrStartowy;
    private final SimpleIntegerProperty Kolejnosc;
    
    public DaneTabeliJezdzcow(String Data, String NazwaKonia, int NrStartowy, int Kolejnosc){
        this.Data = new SimpleStringProperty(Data);
        this.NazwaKonia = new SimpleStringProperty(NazwaKonia);
        this.NrStartowy = new SimpleIntegerProperty(NrStartowy);
        this.Kolejnosc = new SimpleIntegerProperty(Kolejnosc);
    }
    
    public String getData(){
        return Data.get();
    }
    
    public String getNazwaKonia(){
        return NazwaKonia.get();
    }
    
    public Integer getNrStartowy(){
        return NrStartowy.get();
    }
    
    public Integer getKolejnosc(){
        return Kolejnosc.get();
    }
    
}
