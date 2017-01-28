/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class WyliczWskazniki extends PolaczZBaza 
{
    private HashMap<String, Integer> stanToru;
    
    public WyliczWskazniki() throws SQLException
    {
        this.stanToru = this.pobierzStanyToru();
    }
    
    /**
     *
     * @throws SQLException
     */
    public void wybierzWskaznikiDlaKoni() throws SQLException
    {
        HashMap<String, HashMap<String, Integer>> strukturaDanych = new HashMap<>();
        DaneHistoryczne daneHistoryczne = new DaneHistoryczne();
        ResultSet wynikZapytania = this.uchwytDoBazy.executeQuery("SELECT `nazwa` FROM `konie` WHERE `usunieto` = 0");
        
        while(wynikZapytania.next()) {
            String[] daneKonia = null;
            HashMap<String, Integer> daneWskaznikowe;
            String nazwa = wynikZapytania.getString("nazwa");
            HashMap<Integer, HashMap<String, String>> daneGonitwKonia = daneHistoryczne.zwrocDaneGonitwDlaObiektu("koń", nazwa);

            if(strukturaDanych.get(nazwa) == null) {
                daneWskaznikowe = new HashMap<>();
                daneKonia = daneHistoryczne.zwrocDaneKonia(nazwa);
            } else {
                daneWskaznikowe = strukturaDanych.get(nazwa);
            }
            
            System.out.println(nazwa);
            for(HashMap<String, String> obiekt: daneGonitwKonia.values()) {
                this.przetworzDaneGonitwy(daneWskaznikowe, obiekt);
            }
        }
    }
    
    private String przetworzDaneKonia(String[] dane)
    {
        String wynik = "";
        
        if(Integer.valueOf(dane[1]) < 3) {
            wynik += "<wiek>1</wiek>";
        } else {
            wynik += "<wiek>2</wiek>";
        }

        if(dane[2].equals("")) {
            wynik += "<rasa>1</rasa>";
        } else {
            wynik += "<rasa>2</rasa>";
        }

        switch(dane[3]) {
            case "1":
                wynik += "<grupa>3</grupa>";
                break;
            case "2":
                wynik += "<grupa>2</grupa>";
                break;
            default:
                wynik += "<grupa>1</grupa>";
                break;
        }
        
        return wynik;
    }
    
    private HashMap<String, Integer> przetworzDaneGonitwy(HashMap<String, Integer> daneWskaznikowe, HashMap<String, String> daneGonitw)
    {   
        int wartoscWskaznika;
        
        for(String klucz: daneGonitw.keySet()){
            wartoscWskaznika = (daneWskaznikowe.get(klucz) != null) ? daneWskaznikowe.get(klucz) : 0;
            
            switch(klucz) {
                case "miejsce":
                    int miejsce = (daneGonitw.get(klucz) != null) ? Integer.valueOf(daneGonitw.get(klucz)) : 100;
                    wartoscWskaznika += (100 - miejsce);
                    break;
                case "stan toru":
                    int wynik = (stanToru.get(daneGonitw.get(klucz)) != null) ? stanToru.get(daneGonitw.get(klucz)) : 0;
                    
                    
                    break;
                case "styl":
                    break;
                case "wycofano":
                    break;
                case "dystans":
                    break;
                case "czas":
                    break;
                case "rekordy":
                    break;
                case "jezdziec":
                    break;
                case "temperatura":
                    break;
                case "odleglosci":
                    break;   
            }
            
            
            daneWskaznikowe.put(klucz, wartoscWskaznika);
        }
        
        return daneWskaznikowe;
    }
    
    private HashMap<String, Integer> pobierzStanyToru() throws SQLException
    {
        HashMap<String, Integer> stan = new HashMap<>();
        ResultSet wynikZapytania = this.uchwytDoBazy.executeQuery("SELECT DISTINCT `stan toru` FROM `informacje`");
        
        while(wynikZapytania.next()) {
            String klucz = wynikZapytania.getString("stan toru");
            stan.put(klucz, klucz.trim().length());
        }
        
        return stan;
    }
}
