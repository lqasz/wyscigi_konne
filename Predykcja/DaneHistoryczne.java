/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public ObservableList<HashMap<String, String>> zwrocDaneGonitwy(String dataGonitwy, String numerGonitwy, String[] parametry) throws SQLException
    {
       
        ResultSet wynikZapytania;
        ObservableList<HashMap<String, String>> daneWynikowe = FXCollections.observableArrayList();
        String zapytanie = "SELECT * "
                            + "FROM `informacje` "
                            + "INNER JOIN `gonitwainformacje` ON(`informacje`.`id` = `id informacji`) "
                            + "INNER JOIN `gonitwa` ON(`gonitwa`.`id` = `id gonitwy`) "
                            + "INNER JOIN `dzokeje` ON(`dzokeje`.`id` = `id dzokeja`) "
                            + "INNER JOIN `konie` ON(`konie`.`id` = `id konia`) "
                            + "WHERE `data gonitwy` = '"+ dataGonitwy +"' AND `gonitwa`.`usunieto` = 0 "
                            + "AND `nr gonitwy` = "+ numerGonitwy +" "
                            + "ORDER BY `dystans` ASC";
        
        System.out.println(zapytanie);
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        while(wynikZapytania.next()) {
            HashMap<String, String> dane = new HashMap();
            for(String parametr: parametry) {
                String cecha = wynikZapytania.getString(parametr);
                dane.put(parametr, cecha);
            }
            
            daneWynikowe.add(dane);
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
    
    public ObservableList<HashMap> zwrocDaneZespolu(String obiekt, String nazwaObiektu) throws SQLException
    {
        ResultSet wynikZapytania;
        String nazwaWiersza1, nazwaWiersza2;
        ObservableList<HashMap> daneWynikowe = FXCollections.observableArrayList();
        
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
            
            daneWynikowe.add(dane);
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
    public ObservableList<HashMap<String, String>> zwrocDaneGonitwDlaObiektu(String obiekt, String nazwaObiektu, String sezon, String[] parametry) throws SQLException
    {
        ResultSet wynikZapytania;
        String nazwaWiersza;
        ObservableList<HashMap<String, String>> daneWynikowe = FXCollections.observableArrayList();
        
        if("dzokej".equals(obiekt)) {
            nazwaWiersza = "jezdziec";
        } else {
            nazwaWiersza = "nazwa";
        }
        
        String zapytanie = "SELECT * "
                            + "FROM `konie` "
                            + "INNER JOIN `gonitwa` ON(`konie`.`id` = `id konia`) "
                            + "INNER JOIN `dzokeje` ON(`dzokeje`.`id` = `id dzokeja`) "
                            + "INNER JOIN `gonitwainformacje` ON(`gonitwa`.`id` = `id gonitwy`)"
                            + "INNER JOIN `informacje` ON(`informacje`.`id` = `id informacji`)"
                            + "WHERE `"+ nazwaWiersza +"` = '"+ nazwaObiektu +"' "
                                + "AND YEAR(`data gonitwy`) = '"+ sezon +"' "
                            + "ORDER BY `data gonitwy` ASC";
        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        while(wynikZapytania.next()) {
            HashMap<String, String> dane = new HashMap();
            
            for(String parametr: parametry) {
                String cecha = wynikZapytania.getString(parametr);
                dane.put(parametr, cecha);
            }
            
            daneWynikowe.add(dane);
        }
        
        return daneWynikowe;
    }
    
    /**
     *
     * @param tabela
     * @param pole
     * @return
     * @throws SQLException
     */
    public ObservableList<String> polaDoComboBox(String tabela, String pole) throws SQLException
    {   
        String zapytanie;
        ResultSet wynikZapytania;
        ObservableList<String> daneWynikowe = FXCollections.observableArrayList();
        
        if(pole.equals("YEAR(`data gonitwy`)")){
            zapytanie = "SELECT DISTINCT "+ pole +" AS `pole` FROM `"+ tabela +"` WHERE `usunieto` = 0";
        }else{
            zapytanie = "SELECT DISTINCT `"+ pole +"` AS `pole` FROM `"+ tabela +"` WHERE `usunieto` = 0";
        }
        
        System.out.println(zapytanie);
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        
        while(wynikZapytania.next()) {
            daneWynikowe.add(wynikZapytania.getString("pole").trim());
        }
        
        return daneWynikowe;
    }
    
    public ObservableList<Integer> iloscGonitw(String dataGonitwy) throws SQLException
    {
        ResultSet wynikZapytania;
        ObservableList<Integer> daneWynikowe = FXCollections.observableArrayList();
        
        String zapytanie = "SELECT DISTINCT `nr gonitwy` FROM `gonitwa` WHERE `usunieto` = 0 AND `data gonitwy` = '"+ dataGonitwy +"'";
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        
        while(wynikZapytania.next()) {
            daneWynikowe.add(wynikZapytania.getInt("nr gonitwy"));
        }
        
        return daneWynikowe;
    }

}