/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class WyliczWskazniki extends PolaczZBaza 
{
    /**
     *
     * @throws SQLException
     */
    public void wybierzWskaznikiDlaKoni() throws SQLException
    {
        HashMap<String, HashMap<String, String>> strukturaDanych = new HashMap<>();
        DaneHistoryczne daneHistoryczne = new DaneHistoryczne();
        ResultSet wynikZapytania = this.uchwytDoBazy.executeQuery("SELECT `nazwa` FROM `konie` WHERE `usunieto` = 0");
        
        while(wynikZapytania.next()) {
            String nazwa = wynikZapytania.getString("nazwa");
            HashMap<String, String> daneWskaznikowe = (strukturaDanych.get(nazwa) != null) ? strukturaDanych.get(nazwa) : new HashMap<>();
            String[] daneKonia = daneHistoryczne.zwrocDaneKonia(nazwa);
            HashMap<Integer, HashMap<String, String>> daneGonitwKonia = daneHistoryczne.zwrocDaneGonitwDlaObiektu("koń", nazwa);
            
            
            
            for(HashMap<String, String> obiekt: daneGonitwKonia.values()) {
                for(String klucz: obiekt.keySet()) {
                    System.out.println(klucz);
                    System.out.println(obiekt.get(klucz));
                }
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
    
    private String przetworzDaneGonitwy(String klucz, String wartosc)
    {
        String wynik = "";
        
        switch(klucz) {
            case "miejsce":
                int pomocnicza = Math.abs(100 - (int)(Math.pow(Integer.valueOf(wartosc), 2)));
                wynik += "<miejsce>"+ pomocnicza +"</miejsce>";
                break;
            case "stan toru":
                break;
            case "styl":
                break;
            case "wycofano":
                if(wartosc.length() > 0) {
                    wynik += "<wycofano>10</wycofano>";
                } else {
                    wynik += "<wycofano>0</wycofano>";
                }
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
        
        return wynik;
    }
}
