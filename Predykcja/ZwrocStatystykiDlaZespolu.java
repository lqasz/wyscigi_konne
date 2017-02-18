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
public class ZwrocStatystykiDlaZespolu
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
        String[] daty = daneStatystyczne.zwrocPrzedzialDat(nazwaKonia);
        
        int srednia = daneStatystyczne.obliczStatystykeDlaObiektu("AVG", daty[0], daty[1], "kon", nazwaKonia),
                maksimum = daneStatystyczne.obliczStatystykeDlaObiektu("MAX", daty[0], daty[1], "kon", nazwaKonia),
                minimum = daneStatystyczne.obliczStatystykeDlaObiektu("MIN", daty[0], daty[1], "kon", nazwaKonia),
                odchylenieStandardowe = daneStatystyczne.obliczStatystykeDlaObiektu("STD", daty[0], daty[1], "kon", nazwaKonia);
        
        analityka.put("srednia", 100 - srednia);
        analityka.put("maksimum", 100 - maksimum);
        analityka.put("minimum", 100 - minimum);
        analityka.put("odchylenieStandardowe", 100 - odchylenieStandardowe);
        
        return analityka;
    }
}
