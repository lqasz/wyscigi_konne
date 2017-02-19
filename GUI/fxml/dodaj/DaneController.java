
package wyscigi_konne.GUI.fxml.dodaj;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class DaneController 
{
    @FXML
    public TextField Kolejnosc1;
    @FXML
    public TextField Kolejnosc2;
    @FXML
    public TextField Kolejnosc3;
    @FXML
    public TextField Kolejnosc4;
    @FXML
    public TextField Kolejnosc5;
    @FXML
    public TextField Kolejnosc6;
    @FXML
    public TextField Kolejnosc7;
    @FXML
    public TextField Kolejnosc8;
    @FXML
    public TextField Kolejnosc9;
    @FXML
    public TextField Kolejnosc10;
    
    @FXML
    public TextField NazwaKonia1;
    @FXML
    public TextField NazwaKonia2;
    @FXML
    public TextField NazwaKonia3;
    @FXML
    public TextField NazwaKonia4;
    @FXML
    public TextField NazwaKonia5;
    @FXML
    public TextField NazwaKonia6;
    @FXML
    public TextField NazwaKonia7;
    @FXML
    public TextField NazwaKonia8;
    @FXML
    public TextField NazwaKonia9;
    @FXML
    public TextField NazwaKonia10;
    
    @FXML
    public TextField Jezdziec1;
    @FXML
    public TextField Jezdziec2;
    @FXML
    public TextField Jezdziec3;
    @FXML
    public TextField Jezdziec4;
    @FXML
    public TextField Jezdziec5;
    @FXML
    public TextField Jezdziec6;
    @FXML
    public TextField Jezdziec7;
    @FXML
    public TextField Jezdziec8;
    @FXML
    public TextField Jezdziec9;
    @FXML
    public TextField Jezdziec10;
    
    @FXML
    public TextField Waga1;
    @FXML
    public TextField Waga2;
    @FXML
    public TextField Waga3;
    @FXML
    public TextField Waga4;
    @FXML
    public TextField Waga5;
    @FXML
    public TextField Waga6;
    @FXML
    public TextField Waga7;
    @FXML
    public TextField Waga8;
    @FXML
    public TextField Waga9;
    @FXML
    public TextField Waga10;
    
    @FXML
    public TextField NrStart1;
    @FXML
    public TextField NrStart2;
    @FXML
    public TextField NrStart3;
    @FXML
    public TextField NrStart4;
    @FXML
    public TextField NrStart5;
    @FXML
    public TextField NrStart6;
    @FXML
    public TextField NrStart7;
    @FXML
    public TextField NrStart8;
    @FXML
    public TextField NrStart9;
    @FXML
    public TextField NrStart10;
    
    @FXML
    public TextField Data;
    @FXML
    public TextField Dystans;
    @FXML
    public TextField Wiek;
    @FXML
    public TextField Grupa;
    @FXML
    public TextField Czas;
    @FXML
    public TextField Temperatura;
    @FXML
    public TextField Styl;
    @FXML
    public TextField Odleglosci;
    @FXML
    public TextField StanToru;
    @FXML
    public TextField Rekordy;
    
    Dane Dane;
    
    @FXML
    private void handleButtonAction_Zapisz(ActionEvent event) throws SQLException 
    {   
        String data = Data.getText();
        String dystans = Dystans.getText();
        String wiek = Wiek.getText();
        String grupa = Grupa.getText();
        String czas = Czas.getText();
        String temperatura = Temperatura.getText();
        String styl = Styl.getText();
        String odleglosci = Odleglosci.getText();
        String stanToru = StanToru.getText();
        String rekordy = Rekordy.getText();
        
        String[] Kolejnosc = new String[9];
        Kolejnosc[0] = Kolejnosc1.getText();
        Kolejnosc[1] = Kolejnosc2.getText();
        Kolejnosc[2] = Kolejnosc3.getText();
        Kolejnosc[3] = Kolejnosc4.getText();
        Kolejnosc[4] = Kolejnosc5.getText();
        Kolejnosc[5] = Kolejnosc6.getText();
        Kolejnosc[6] = Kolejnosc7.getText();
        Kolejnosc[7] = Kolejnosc8.getText();
        Kolejnosc[8] = Kolejnosc9.getText();
        Kolejnosc[9] = Kolejnosc10.getText();
       
        String[] NazwaKonia = new String[9];
        NazwaKonia[0] = NazwaKonia1.getText();
        NazwaKonia[1] = NazwaKonia2.getText();
        NazwaKonia[2] = NazwaKonia3.getText();
        NazwaKonia[3] = NazwaKonia4.getText();
        NazwaKonia[4] = NazwaKonia5.getText();
        NazwaKonia[5] = NazwaKonia6.getText();
        NazwaKonia[6] = NazwaKonia7.getText();
        NazwaKonia[7] = NazwaKonia8.getText();
        NazwaKonia[8] = NazwaKonia9.getText();
        NazwaKonia[9] = NazwaKonia10.getText();
        
        String[] Jezdziec = new String[9];
        Jezdziec[0] = Jezdziec1.getText();
        Jezdziec[1] = Jezdziec2.getText();
        Jezdziec[2] = Jezdziec3.getText();
        Jezdziec[3] = Jezdziec4.getText();
        Jezdziec[4] = Jezdziec5.getText();
        Jezdziec[5] = Jezdziec6.getText();
        Jezdziec[6] = Jezdziec7.getText();
        Jezdziec[7] = Jezdziec8.getText();
        Jezdziec[8] = Jezdziec9.getText();
        Jezdziec[9] = Jezdziec10.getText();
        
        String[] Waga = new String[9];
        Waga[0] = Waga1.getText();
        Waga[1] = Waga2.getText();
        Waga[2] = Waga3.getText();
        Waga[3] = Waga4.getText();
        Waga[4] = Waga5.getText();
        Waga[5] = Waga6.getText();
        Waga[6] = Waga7.getText();
        Waga[7] = Waga8.getText();
        Waga[8] = Waga9.getText();
        Waga[9] = Waga10.getText();
        
        String[] NrStart = new String[9];
        NrStart[0] = NrStart1.getText();
        NrStart[1] = NrStart2.getText();
        NrStart[2] = NrStart3.getText();
        NrStart[3] = NrStart4.getText();
        NrStart[4] = NrStart5.getText();
        NrStart[5] = NrStart6.getText();
        NrStart[6] = NrStart7.getText();
        NrStart[7] = NrStart8.getText();
        NrStart[8] = NrStart9.getText();
        NrStart[9] = NrStart10.getText();       
                
        Dane.dodajInformacje(dystans, czas, temperatura, styl, odleglosci, stanToru, rekordy);
        
        for(int i=0;i<10;i++)
        {
            if(Kolejnosc[i] != null)
            {
                int idKonia = Dane.zwrocIdKonia(NazwaKonia[i], wiek, grupa);
                int idDzokeja = Dane.zwrocIdDzokeja(Jezdziec[i], Waga[i]);
                Dane.dodajGonitwa(idKonia, idDzokeja, Kolejnosc[i], NrStart[i], data);
                
                int idInformacji = Dane.zwrocIdOstatniejInformacji();
                int idGonitwa = Dane.zwrocIdOstatniaGonitwa();                
                Dane.dodajGonitwaInformacje(idInformacji, idGonitwa);
                
                Dane.dodajZespol(idKonia, idDzokeja, styl);
                int idZespolu = Dane.zwrocIdZespol();
                Dane.dodajWskazniki(idZespolu);                                                 
            }
        }        
    }    

}
    

