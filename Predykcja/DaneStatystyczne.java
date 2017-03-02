/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DaneStatystyczne
{
    public int obliczStatystykeDlaObiektu(String statystyka, String dataPoczatku, String dataKonca, String obiekt, String nazwaObiektu) throws SQLException
    {
        int wynik;
        String nazwaWiersza;
        ResultSet wynikZapytania;
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        
        if("dzokej".equals(obiekt)) {
            nazwaWiersza = "jezdziec";
        } else {
            nazwaWiersza = "nazwa";
        }
        
        String zapytanie = "SELECT "+ statystyka +"(`miejsce`) AS statystyka "
                            + "FROM `gonitwa` "
                            + "INNER JOIN `dzokeje` ON(`dzokeje`.`id` = `id dzokeja`) "
                            + "INNER JOIN `konie` ON(`konie`.`id` = `id konia`) "
                            + "WHERE `data gonitwy` BETWEEN '"+ dataPoczatku +"' AND '"+ dataKonca +"' AND `gonitwa`.`usunieto` = 0 AND `"+ nazwaWiersza +"` = '"+ nazwaObiektu +"'";
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);
        wynikZapytania.next();
        wynik = wynikZapytania.getInt("statystyka");
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
        
        return wynik;
    }
    
    public String[] zwrocPrzedzialDat(String nazwaKonia) throws SQLException
    {
        ResultSet wynikZapytania;
        String[] daty = new String[2];
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        
        String zapytanie = "SELECT MIN(`data gonitwy`) AS `pocz`, MAX(`data gonitwy`) AS `kon` "
                + "FROM `gonitwa` INNER JOIN `konie` ON(`id konia` = `konie`.`id`) "
                + "WHERE `gonitwa`.`usunieto` = 0 AND `nazwa` = '"+ nazwaKonia +"'";
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);
        wynikZapytania.next();
        
        daty[0] = wynikZapytania.getString("pocz");
        daty[1] = wynikZapytania.getString("kon");
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
        
        return daty;
    }
}
