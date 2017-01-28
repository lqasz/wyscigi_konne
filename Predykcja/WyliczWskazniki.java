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
    private final HashMap<String, Integer> rasaKonia;
    private final HashMap<String, Integer> jezdziec;
    private final HashMap<String, Integer> stanToru;
    private final HashMap<String, Integer> stylZespolu;
    private final HashMap<String, Integer> wycofanoZespol;
    
    public WyliczWskazniki() throws SQLException
    {
        this.rasaKonia = this.pobierzInformacje("SELECT DISTINCT `rasa` FROM `konie` WHERE `rasa` not like ''", "rasa");
        this.jezdziec = this.pobierzInformacje("SELECT DISTINCT `jezdziec` FROM `dzokeje`", "jezdziec");
        this.stanToru = this.pobierzInformacje("SELECT DISTINCT `stan toru` FROM `informacje`", "stan toru");
        this.stylZespolu = this.pobierzInformacje("SELECT DISTINCT `styl` FROM `informacje`", "styl");
        this.wycofanoZespol = this.pobierzInformacje("SELECT DISTINCT `wycofano` FROM `gonitwa`", "wycofano");
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
            
            for(HashMap<String, String> obiekt: daneGonitwKonia.values()) {
                daneWskaznikowe = this.przetworzDaneGonitwy(daneWskaznikowe, obiekt);
            }
            
            daneWskaznikowe.putAll(przetworzDaneKonia(daneKonia));
            strukturaDanych.put(nazwa, daneWskaznikowe);
        }
        
        for(String nazwaKonia: strukturaDanych.keySet()) {
            String XML = "<kon>"+ nazwaKonia;
            HashMap<String, Integer> daneWskaznikowe = strukturaDanych.get(nazwaKonia);
            XML = daneWskaznikowe.keySet().stream().map((klucz) -> "\n  <"+ klucz +">"+ daneWskaznikowe.get(klucz) +"</"+ klucz +">").reduce(XML, String::concat);
            XML += "\n</kon>";
            
            System.out.println(XML);
        }
    }
    
    private HashMap<String, Integer> przetworzDaneKonia(String[] dane)
    {
        String nazwa;
        HashMap<String, Integer> podstawoweDane = new HashMap<>();
        
        for(int i = 0; i < dane.length; i++) {
            int wynik = 0;
            
            switch(i) {
                case 0:
                    nazwa = "wiek";
                    if(Integer.valueOf(dane[i]) < 3) {
                        wynik = 1;
                    } else {
                        wynik = 10 - Integer.valueOf(dane[i]);
                    }
                    break;
                case 1:
                    nazwa = "rasa";
                    if(!"".equals(dane[i])) {
                        wynik = (!"".equals(dane[i]) && rasaKonia.get(dane[i]) != null) ? rasaKonia.get(dane[i]) : 100;
                    } else {
                        wynik = 0;
                    }
                    break;
                default:
                    nazwa = "grupa";
                    wynik = 3 - Integer.valueOf(dane[i]);
                    break;
            }
            
            podstawoweDane.put(nazwa, wynik);
        }
        
        return podstawoweDane;
    }
    
    private HashMap<String, Integer> przetworzDaneGonitwy(HashMap<String, Integer> daneWskaznikowe, HashMap<String, String> daneGonitw)
    {   
        int wartoscWskaznika;
        
        for(String klucz: daneGonitw.keySet()){
            int wartosc = 1;
            wartoscWskaznika = (daneWskaznikowe.get(klucz) != null) ? daneWskaznikowe.get(klucz) : 0;
            
            switch(klucz) {
                case "miejsce":
                    wartosc = (daneGonitw.get(klucz) != null && !"0".equals(daneGonitw.get(klucz))) ? (100 - Integer.valueOf(daneGonitw.get(klucz))) : 0;
                    break;
                case "stan toru":
                    wartosc = (stanToru.get(daneGonitw.get(klucz)) != null) ? stanToru.get(daneGonitw.get(klucz)) : 1;
                    break;
                case "styl":
                    wartosc = (stylZespolu.get(daneGonitw.get(klucz)) != null) ? stylZespolu.get(daneGonitw.get(klucz)) : 1;
                    break;
                case "wycofano":
                    wartosc = (wycofanoZespol.get(daneGonitw.get(klucz)) != null) ? wycofanoZespol.get(daneGonitw.get(klucz)) : 0;
                    break;
                case "dystans":
                    wartosc = Integer.valueOf(daneGonitw.get(klucz));
                    break;
                case "czas":
                    break;
                case "rekordy":
                    wartosc = (!"".equals(daneGonitw.get(klucz))) ? 100 : 0;
                    break;
                case "jezdziec":
                    wartosc = jezdziec.get(daneGonitw.get(klucz));
                    break;
                case "temperatura":
                    break;
                case "odleglosci":
                    break;   
            }
            
            if(!"data gonitwy".equals(klucz)) {
                wartoscWskaznika += wartosc;
                daneWskaznikowe.put(klucz, wartoscWskaznika);
            }
        }
        
        return daneWskaznikowe;
    }
    
    private HashMap<String, Integer> pobierzInformacje(String zapytanie, String pole) throws SQLException
    {
        HashMap<String, Integer> dane = new HashMap<>();
        ResultSet wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        
        while(wynikZapytania.next()) {
            int wartosc = 0;
            String klucz = wynikZapytania.getString(pole);
            char[] tablicaZnakow = klucz.trim().toCharArray();
            
            for(int i = 0; i < tablicaZnakow.length; i++) {
                wartosc += (int)(tablicaZnakow[i]);
            }
            
            dane.put(klucz, wartosc);
        }
        
        return dane;
    }
}