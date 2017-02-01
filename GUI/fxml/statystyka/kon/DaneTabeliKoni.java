package wyscigi_konne.GUI.fxml.statystyka.kon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DaneTabeliKoni {

    private final SimpleStringProperty Data;
    private final SimpleStringProperty Jezdziec;
    private final SimpleIntegerProperty NrStartowy;
    private final SimpleIntegerProperty Kolejnosc;
    
    public DaneTabeliKoni(String Data, String Jezdziec, int NrStartowy, int Kolejnosc){
        this.Data = new SimpleStringProperty(Data);
        this.Jezdziec = new SimpleStringProperty(Jezdziec);
        this.NrStartowy = new SimpleIntegerProperty(NrStartowy);
        this.Kolejnosc = new SimpleIntegerProperty(Kolejnosc);
    }
    
    public String getData(){
        return Data.get();
    }
    
    public String getJezdziec(){
        return Jezdziec.get();
    }
    
    public Integer getNrStartowy(){
        return NrStartowy.get();
    }
    
    public Integer getKolejnosc(){
        return Kolejnosc.get();
    }
    
}
