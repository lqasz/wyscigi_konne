/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class ZwrocStatystykiDlaZespolu implements IPrzetworzenieDanych
{
    private final String nazwaKonia;
    
    public ZwrocStatystykiDlaZespolu(String kon, String dzokej)
    {
        this.nazwaKonia = kon;
    }
    
    public HashMap<String, Integer> zwrocRozszerzoneDane(HashMap<String, Integer> dane) throws SQLException
    {
        dane.putAll(this.zwrocAnalityke());
        return dane;
    }
    
    private HashMap<String, Integer> zwrocAnalityke() throws SQLException
    {
        HashMap<String, Integer> analityka = new HashMap<>();
        DaneStatystyczne daneStatystyczne = new DaneStatystyczne();
        
        int srednia = daneStatystyczne.obliczStatystykeDlaObiektu("AVG", "2016-04-17", "2016-04-17", "kon", nazwaKonia),
                maksimum = daneStatystyczne.obliczStatystykeDlaObiektu("MAX", "2016-04-17", "2016-04-17", "kon", nazwaKonia),
                minimum = daneStatystyczne.obliczStatystykeDlaObiektu("MIN", "2016-04-17", "2016-04-17", "kon", nazwaKonia),
                odchylenieStandardowe = daneStatystyczne.obliczStatystykeDlaObiektu("STD", "2016-04-17", "2016-04-17", "kon", nazwaKonia);
        
        analityka.put("srednia", 100 - srednia);
        analityka.put("maksimum", 100 - maksimum);
        analityka.put("minimum", 100 - minimum);
        analityka.put("odchylenieStandardowe", 100 - odchylenieStandardowe);
        
        return analityka;
    }
    
    
    
    @Override
    public void Standaryzuj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
