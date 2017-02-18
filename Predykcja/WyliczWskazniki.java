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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class WyliczWskazniki extends PolaczZBaza implements IPodstaweInformacje
{
    private final HashMap<String, Integer> rasaKonia;
    private final HashMap<String, Integer> jezdziec;
    private final HashMap<String, Integer> stanToru;
    private final HashMap<String, Integer> stylZespolu;
    private final HashMap<String, Integer> wycofanoZespol;
    
    public WyliczWskazniki() throws SQLException
    {
        this.rasaKonia = this.przeksztalcNaWskazniki("SELECT DISTINCT `rasa` FROM `konie` WHERE `rasa` not like ''", "rasa");
        this.jezdziec = this.przeksztalcNaWskazniki("SELECT DISTINCT `jezdziec` FROM `dzokeje`", "jezdziec");
        this.stanToru = this.przeksztalcNaWskazniki("SELECT DISTINCT `stan toru` FROM `informacje`", "stan toru");
        this.stylZespolu = this.przeksztalcNaWskazniki("SELECT DISTINCT `styl` FROM `informacje`", "styl");
        this.wycofanoZespol = this.przeksztalcNaWskazniki("SELECT DISTINCT `wycofano` FROM `gonitwa`", "wycofano");
    }
    
    /**
     *
     * @throws SQLException
     */
    public void wybierzWskaznikiDlaZespolu() throws SQLException
    {
        ArrayList<String[]> daneZespolu = new ArrayList<>();
        DaneHistoryczne daneHistoryczne = new DaneHistoryczne();
        HashMap<String, HashMap<String, Integer>> strukturaDanych = new HashMap<>();
        ResultSet zapytanieZespol = this.uchwytDoBazy.executeQuery("SELECT `id`, `id konia`, `id dzokeja` FROM `zespol`");
        
        while(zapytanieZespol.next()) {
            String idZespolu = zapytanieZespol.getString("id");
            String idKonia = zapytanieZespol.getString("id konia");
            String idDzokeja = zapytanieZespol.getString("id dzokeja");
            
            daneZespolu.add(new String[]{idZespolu, idKonia, idDzokeja});
        }
        
        for(String[] dane: daneZespolu) {
            String[] daneKonia = null;
            HashMap<String, Integer> daneWskaznikowe;
            
            ResultSet zapytanieNazwaKonia = this.uchwytDoBazy.executeQuery("SELECT `nazwa` FROM `konie` WHERE `id` = '"+ dane[1] +"'");
            zapytanieNazwaKonia.next();
            String nazwaKonia = zapytanieNazwaKonia.getString("nazwa");
            String[] parametry = new String[]{"miejsce", "stan toru", "styl", "wycofano", "dystans", "rekordy", "jezdziec", "temperatura"};
            
            ObservableList<HashMap<String, String>> daneGonitwKonia = daneHistoryczne.zwrocDaneGonitwDlaObiektu("koń", nazwaKonia, "2016", parametry);

            if(strukturaDanych.get(dane[0]) == null) {
                daneWskaznikowe = new HashMap<>();
                daneKonia = daneHistoryczne.zwrocDaneKonia(nazwaKonia);
            } else {
                daneWskaznikowe = strukturaDanych.get(dane[0]);
            }
            
            for(HashMap gonitwa: daneGonitwKonia) {
                daneWskaznikowe = this.przetworzDaneGonitwy(daneWskaznikowe, gonitwa);
            }
            
            daneWskaznikowe.putAll(przetworzDaneKonia(daneKonia));
            strukturaDanych.put(dane[0], daneWskaznikowe);
        }
        
        this.dodajDaneDoBazy(strukturaDanych);
    }
    
    private void dodajDaneDoBazy(HashMap<String, HashMap<String, Integer>> strukturaDanych) throws SQLException
    {
        int srednia = 0,
            iterator = 0;
        
        this.uchwytDoBazy.executeUpdate("TRUNCATE `wskazniki`");
        for(String idZespolu: strukturaDanych.keySet()) {
            String[] czlonkowieZespolu = this.zwrocCzlonkowZespolu(Integer.valueOf(idZespolu));
            
            ZwrocStatystykiDlaZespolu grupowanieZespolow = new ZwrocStatystykiDlaZespolu(czlonkowieZespolu[0], czlonkowieZespolu[1]);
            strukturaDanych.put(idZespolu, grupowanieZespolow.zwrocRozszerzoneDane(strukturaDanych.get(idZespolu)));
            
            iterator++;
            srednia += (grupowanieZespolow.zwrocRozszerzoneDane(strukturaDanych.get(idZespolu))).get("srednia");
        }
        
        srednia /= iterator;
        for(String idZespolu: strukturaDanych.keySet()) {
            String XML = "<zespol>"+ idZespolu.trim();
            HashMap<String, Integer> daneWskaznikowe = strukturaDanych.get(idZespolu);
            XML = daneWskaznikowe.keySet().stream().map((klucz) -> "\n  <"+ klucz +">"+ daneWskaznikowe.get(klucz) +"</"+ klucz +">").reduce(XML, String::concat);
            XML += "\n  <rozstep>"+ (daneWskaznikowe.get("minimum") - daneWskaznikowe.get("maksimum")) +"</rozstep>";
            XML += "\n  <roznicaSrednich>"+ Math.abs(daneWskaznikowe.get("srednia") - srednia) +"</roznicaSrednich>";
            XML += "\n  <minimumNaSrednia>"+ (Double.valueOf(daneWskaznikowe.get("minimum")) / Double.valueOf(srednia)) +"</minimumNaSrednia>";
            XML += "\n  <maksimumNaSrednia>"+ (Double.valueOf(daneWskaznikowe.get("maksimum")) / Double.valueOf(srednia)) +"</maksimumNaSrednia>";
            XML += "\n  <sredniaNaSrednia>"+ (Double.valueOf(daneWskaznikowe.get("srednia")) / Double.valueOf(srednia)) +"</sredniaNaSrednia>";
            XML += "\n</zespol>";
            
            this.uchwytDoBazy.executeUpdate("INSERT INTO `wskazniki`(`id zespolu`, `metadane`) VALUES('"+ idZespolu +"', '"+ XML.trim() +"')");
        }
    }
    
    /**
     *
     */
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
    
    /**
     *
     */
    private HashMap<String, Integer> przetworzDaneGonitwy(HashMap<String, Integer> daneWskaznikowe, HashMap<String, String> daneGonitw)
    {   
        int wartoscWskaznika;
        
        for(String klucz: daneGonitw.keySet()){
            int wartosc = 1;
            wartoscWskaznika = (daneWskaznikowe.get(klucz) != null) ? daneWskaznikowe.get(klucz) : 0;

            switch(klucz) {
                case "miejsce":
                    wartosc = (!"0".equals(daneGonitw.get(klucz))) ? (100 - Integer.valueOf(daneGonitw.get(klucz))) : 0;
                    break;
                case "stan toru":
                    wartosc = (stanToru.get(daneGonitw.get(klucz)) != null) ? stanToru.get(daneGonitw.get(klucz)) : 1;
                    break;
                case "styl":
                    wartosc = (stylZespolu.get(daneGonitw.get(klucz)) != null) ? stylZespolu.get(daneGonitw.get(klucz)) : 1;
                    break;
                case "wycofano":
                    wartosc = (wycofanoZespol.get(daneGonitw.get(klucz)) != 0) ? 1 : 0;
                    break;
                case "dystans":
                    wartosc = Integer.valueOf(daneGonitw.get(klucz));
                    break;
                case "rekordy":
                    wartosc = (!"".equals(daneGonitw.get(klucz))) ? 100 : 0;
                    break;
                case "jezdziec":
                    wartosc = jezdziec.get(daneGonitw.get(klucz));
                    break;
                case "temperatura":
                    if(daneGonitw.get(klucz) != null) {
                        char[] tablicaZnakow = (daneGonitw.get(klucz).trim()).toCharArray();

                        for(int i = 0; i < tablicaZnakow.length; i++) {
                            wartosc += (int)(tablicaZnakow[i]);
                        }
                    }
                    break;  
            }
            
            if(!"data gonitwy".equals(klucz) && !"nr startowy".equals(klucz) && !"odleglosci".equals(klucz) && !"czas".equals(klucz)) {
                wartoscWskaznika += wartosc;
                
                if("stan toru".equals(klucz)) {
                    klucz = "stan_tory";
                }
                
                daneWskaznikowe.put(klucz, wartoscWskaznika);
            }
        }
        
        return daneWskaznikowe;
    }
    
    /**
     *
     * @param zapytanie
     * @param pole
     * @return
     */
    @Override
    public final HashMap<String, Integer> przeksztalcNaWskazniki(String zapytanie, String pole)
    {
        try {
            HashMap<String, Integer> dane = new HashMap<>();
            ResultSet wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
            
            while(wynikZapytania.next()) {
                int wartosc = 0;
                String klucz = wynikZapytania.getString(pole);
                char[] tablicaZnakow = (klucz.trim()).toCharArray();
                
                for(int i = 0; i < tablicaZnakow.length; i++) {
                    wartosc += (int)(tablicaZnakow[i]);
                }
                
                dane.put(klucz, wartosc);
            }
            
            return dane;
        } catch (SQLException ex) {
            Logger.getLogger(WyliczWskazniki.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param idZespolu
     * @return
     */
    @Override
    public String[] zwrocCzlonkowZespolu(int idZespolu) 
    {
        try {
            ResultSet wynikZapytania = this.uchwytDoBazy.executeQuery("SELECT `nazwa`, `jezdziec` "
                    + "FROM `zespol` INNER JOIN `dzokeje` ON (`dzokeje`.`id` = `id dzokeja`) "
                    + "INNER JOIN `konie` ON(`konie`.`id` = `id konia`)" 
                    + "WHERE `zespol`.`id` = '"+ idZespolu +"'");
            
            wynikZapytania.next();
            String kon = wynikZapytania.getString("nazwa");
            String dzokej = wynikZapytania.getString("jezdziec");
            
            return new String[]{kon, dzokej};
        } catch (SQLException ex) {
            Logger.getLogger(WyliczWskazniki.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String zwrocIdObiektu(String nazwa, String tabela) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}