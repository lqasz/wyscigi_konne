package wyscigi_konne.GUI.fxml.statystyka;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DaneTabeliGonitw {
    
    private final SimpleIntegerProperty kolejnosc;
    private final SimpleStringProperty nazwaKonia;
    private final SimpleStringProperty jezdziec;
    private final SimpleIntegerProperty nrStartowy;
    
    public DaneTabeliGonitw(int kolejnosc, String nazwaKonia, String jezdziec, int nrStartowy){
        
        this.kolejnosc = new SimpleIntegerProperty(kolejnosc);
        this.nazwaKonia = new SimpleStringProperty(nazwaKonia);
        this.jezdziec = new SimpleStringProperty(jezdziec);
        this.nrStartowy = new SimpleIntegerProperty(nrStartowy);
    }
    
    public int getKolejnosc(){
        return kolejnosc.get();
    }
    
    public String getNazwaKonia(){
        return nazwaKonia.get();
    }
        
    public String getJezdziec(){
        return jezdziec.get();
    }
            
    public int getNrstartowy(){
        return nrStartowy.get();
    }
}
