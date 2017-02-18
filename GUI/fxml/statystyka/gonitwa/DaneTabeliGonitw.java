package wyscigi_konne.GUI.fxml.statystyka.gonitwa;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DaneTabeliGonitw {
    
    private final SimpleIntegerProperty Kolejnosc;
    private final SimpleStringProperty NazwaKonia;
    private final SimpleStringProperty Jezdziec;
    private final SimpleIntegerProperty NrStartowy;
    
    public DaneTabeliGonitw(int Kolejnosc, String NazwaKonia, String Jezdziec, int NrStartowy){
        
        this.Kolejnosc = new SimpleIntegerProperty(Kolejnosc);
        this.NazwaKonia = new SimpleStringProperty(NazwaKonia);
        this.Jezdziec = new SimpleStringProperty(Jezdziec);
        this.NrStartowy = new SimpleIntegerProperty(NrStartowy);
    }
    
    public int getKolejnosc(){
        return Kolejnosc.get();
    }
    
    public String getNazwaKonia(){
        return NazwaKonia.get();
    }
        
    public String getJezdziec(){
        return Jezdziec.get();
    }
            
    public int getNrStartowy(){
        return NrStartowy.get();
    }
}
