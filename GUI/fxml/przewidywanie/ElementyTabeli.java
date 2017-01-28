package wyscigikonne.fxml.przewidywanie;

import javafx.beans.property.SimpleStringProperty;

class ElementyTabeli{
    
    private final SimpleStringProperty kon;
    private final SimpleStringProperty jezdziec;
    
    public ElementyTabeli(String kon, String jezdziec){
        
        this.kon = new SimpleStringProperty(kon);
        this.jezdziec = new SimpleStringProperty(jezdziec);   
    }
    
    public String getKon(){
        return kon.get();
    }
    
    public String getJezdziec(){
        return jezdziec.get();
    }
    
}