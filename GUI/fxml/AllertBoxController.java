package wyscigi_konne.GUI.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AllertBoxController implements Initializable {
  
    private final String komunikat;
    
    @FXML Label info;
    
    public AllertBoxController(String komunikat){
        
        this.komunikat = komunikat;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        info.setText(komunikat);  
    }      
}
