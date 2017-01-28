/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class DaneHistoryczne extends PolaczZBaza 
{
    /**
     *
     * @param dataGonitwy
     * @return
     * @throws SQLException
     */
    public HashMap<Integer, HashMap<String, String>> zwrocDaneGonitwy(String dataGonitwy) throws SQLException
    {
        int iterator = 0;
        ResultSet wynikZapytania;
        HashMap<Integer, HashMap<String, String>> daneWynikowe = new HashMap();
        String zapytanie = "SELECT `nazwa`, `jezdziec`, `miejsce`, `nr startowy`,`dystans`, `czas`, `temperatura`, `styl`, `odleglosci`, `stan toru`, `rekordy` "
                            + "FROM `informacje` "
                            + "INNER JOIN `gonitwainformacje` ON(`informacje`.`id` = `id informacji`) "
                            + "INNER JOIN `gonitwa` ON(`gonitwa`.`id` = `id gonitwy`) "
                            + "INNER JOIN `dzokeje` ON(`dzokeje`.`id` = `id dzokeja`) "
                            + "INNER JOIN `konie` ON(`konie`.`id` = `id konia`) "
                            + "WHERE `data gonitwy` = '"+ dataGonitwy +"' AND `gonitwa`.`usunieto` = 0 "
                            + "ORDER BY `dystans` ASC";
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        while(wynikZapytania.next()) {
            String dystans = wynikZapytania.getString("dystans");
            String miejsce = wynikZapytania.getString("miejsce");
            String numerStartowy = wynikZapytania.getString("nr startowy");
            String jezdziec = wynikZapytania.getString("jezdziec");
            String nazwaKonia = wynikZapytania.getString("nazwa");
            String czas = wynikZapytania.getString("czas");
            String temperatura = wynikZapytania.getString("temperatura");
            String styl = wynikZapytania.getString("styl");
            String odleglosci = wynikZapytania.getString("odleglosci");
            String stanToru = wynikZapytania.getString("stan toru");
            String rekordy = wynikZapytania.getString("rekordy");
            
            HashMap<String, String> dane = new HashMap();
            dane.put("dystans", dystans);
            dane.put("miejsce", miejsce);
            dane.put("numerStartowy", numerStartowy);
            dane.put("jezdziec", jezdziec);
            dane.put("nazwa konia", nazwaKonia);
            dane.put("czas", czas);
            dane.put("temperatura", temperatura);
            dane.put("styl", styl);
            dane.put("odleglosci", odleglosci);
            dane.put("stan toru", stanToru);
            dane.put("rekordy", rekordy);
            
            daneWynikowe.put(iterator, dane);
            iterator++;
        }
        
        return daneWynikowe;
    }
    
    /**
     *
     * @param nazwaKonia
     * @return
     * @throws SQLException
     */
    public String[] zwrocDaneKonia(String nazwaKonia) throws SQLException
    {
        ResultSet wynikZapytania;
        String zapytanie = "SELECT `wiek`, `rasa`, `grupa` "
                            + "FROM `konie` "
                            + "WHERE `nazwa` = '"+ nazwaKonia +"' ";
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        wynikZapytania.next();
        String wiek = wynikZapytania.getString("wiek");
        String rasa = wynikZapytania.getString("rasa");
        String grupa = wynikZapytania.getString("grupa");
        
        return new String[]{wiek, rasa, grupa};
    }
    
    /**
     *
     * @param dzokej
     * @return
     * @throws SQLException
     */
    public int zwrocDaneDzokeja(String dzokej) throws SQLException
    {
        ResultSet wynikZapytania;
        String zapytanie = "SELECT `waga` "
                            + "FROM `dzokeje` "
                            + "WHERE `jezdziec` = '"+ dzokej +"' ";
        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        wynikZapytania.next();
        int waga = wynikZapytania.getInt("waga");

        return waga;
    }
    
    public HashMap<Integer, HashMap<String, String>> zwrocDaneZespolu(String obiekt, String nazwaObiektu) throws SQLException
    {
        int iterator = 0;
        ResultSet wynikZapytania;
        String nazwaWiersza1, nazwaWiersza2;
        HashMap<Integer, HashMap<String, String>> daneWynikowe = new HashMap();
        
        if("dzokej".equals(obiekt)) {
            nazwaWiersza1 = "nazwa";
            nazwaWiersza2 = "jezdziec";
        } else {
            nazwaWiersza1 = "jezdziec";
            nazwaWiersza2 = "nazwa";
        }
        
        String zapytanie = "SELECT `"+ nazwaWiersza1 +"`, `rekord`, `styl` "
                            + "FROM `zespol` "
                            + "INNER JOIN `konie` ON(`konie`.`id` = `id konia`) "
                            + "INNER JOIN `dzokeje` ON(`dzokeje`.`id` = `id dzokeja`) "
                            + "WHERE `"+ nazwaWiersza2 +"` = '"+ nazwaObiektu +"' ";
        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        while(wynikZapytania.next()) {
            String nazwa = wynikZapytania.getString(nazwaWiersza1);
            String rekord = wynikZapytania.getString("rekord");
            String styl = wynikZapytania.getString("styl");
            
            HashMap<String, String> dane = new HashMap();
            dane.put(nazwaWiersza1, nazwa);
            dane.put("rekord", rekord);
            dane.put("styl", styl);
            
            daneWynikowe.put(iterator, dane);
            iterator++;
        }
        
        return daneWynikowe;
    }
    
    /**
     *
     * @param obiekt
     * @param nazwaObiektu
     * @return
     * @throws SQLException
     */
    public HashMap<Integer, HashMap<String, String>> zwrocDaneGonitwDlaObiektu(String obiekt, String nazwaObiektu) throws SQLException
    {
        int iterator = 0;
        ResultSet wynikZapytania;
        String nazwaWiersza1, nazwaWiersza2;
        HashMap<Integer, HashMap<String, String>> daneWynikowe = new HashMap();
        
        if("dzokej".equals(obiekt)) {
            nazwaWiersza1 = "nazwa";
            nazwaWiersza2 = "jezdziec";
        } else {
            nazwaWiersza1 = "jezdziec";
            nazwaWiersza2 = "nazwa";
        }
        
        String zapytanie = "SELECT `miejsce`, `nr startowy`, `data gonitwy`, `"+ nazwaWiersza1 +"`, `wycofano`, `dystans`, `czas`, `temperatura`, `styl`, `odleglosci`, `stan toru`, `rekordy` "
                            + "FROM `konie` "
                            + "INNER JOIN `gonitwa` ON(`konie`.`id` = `id konia`) "
                            + "INNER JOIN `dzokeje` ON(`dzokeje`.`id` = `id dzokeja`) "
                            + "INNER JOIN `gonitwainformacje` ON(`gonitwa`.`id` = `id gonitwy`)"
                            + "INNER JOIN `informacje` ON(`informacje`.`id` = `id informacji`)"
                            + "WHERE `"+ nazwaWiersza2 +"` = '"+ nazwaObiektu +"' "
                            + "ORDER BY `data gonitwy` ASC";
        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        while(wynikZapytania.next()) {
            String miejsce = wynikZapytania.getString("miejsce");
            String nrStartowy = wynikZapytania.getString("nr startowy");
            String dataGonitwy = wynikZapytania.getString("data gonitwy");
            String obiektGonitwy = wynikZapytania.getString(nazwaWiersza1);
            String wycofano = wynikZapytania.getString("wycofano");
            String dystans = wynikZapytania.getString("dystans");
            String czas = wynikZapytania.getString("czas");
            String temperatura = wynikZapytania.getString("temperatura");
            String styl = wynikZapytania.getString("styl");
            String odleglosci = wynikZapytania.getString("odleglosci");
            String stanToru = wynikZapytania.getString("stan toru");
            String rekordy = wynikZapytania.getString("rekordy");
            
            HashMap<String, String> dane = new HashMap();
            dane.put("miejsce", miejsce);
            dane.put("nr startowy", nrStartowy);
            dane.put("data gonitwy", dataGonitwy);
            dane.put(nazwaWiersza1, obiektGonitwy);
            dane.put("wycofano", wycofano);
            dane.put("dystans", dystans);
            dane.put("czas", czas);
            dane.put("temperatura", temperatura);
            dane.put("styl", styl);
            dane.put("odleglosci", odleglosci);
            dane.put("stan toru", stanToru);
            dane.put("rekordy", rekordy);
            
            daneWynikowe.put(iterator, dane);
            iterator++;
        }
        
        return daneWynikowe;
    }
    
    /**
     *
     * @param obiekt
     * @param nazwaObiektu
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> daneDoWykresuDlaObiektu(String obiekt, String nazwaObiektu) throws SQLException
    {
        ResultSet wynikZapytania;
        String nazwaWiersza;
        ArrayList<Integer> daneWynikowe = new ArrayList<>();
        
        if("dzokej".equals(obiekt)) {
            nazwaWiersza = "jezdziec";
        } else {
            nazwaWiersza = "nazwa";
        }
        
        String zapytanie = "SELECT `miejsce` FROM `gonitwa` "
                + "INNER JOIN `konie` ON(`konie`.`id` = `id konia`) "
                + "INNER JOIN `dzokeje` ON(`dzokeje`.`id` = `id dzokeja`) "
                + "WHERE `"+ nazwaWiersza +"` = '"+ nazwaObiektu +"' AND `gonitwa`.`usunieto` = 0";
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        while(wynikZapytania.next()) {
            daneWynikowe.add(wynikZapytania.getInt("miejsce"));
        }
        
        return daneWynikowe;
    }
    
    // TODO: 
}