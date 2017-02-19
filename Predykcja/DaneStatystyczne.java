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
public class DaneStatystyczne extends PolaczZBaza
{
    public int obliczStatystykeDlaObiektu(String statystyka, String dataPoczatku, String dataKonca, String obiekt, String nazwaObiektu) throws SQLException
    {
        String nazwaWiersza;
        ResultSet wynikZapytania;
        
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
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        wynikZapytania.next();
        return wynikZapytania.getInt("statystyka");
    }
    
    public String[] zwrocPrzedzialDat(String nazwaKonia) throws SQLException
    {
        ResultSet wynikZapytania;
        
        String zapytanie = "SELECT MIN(`data gonitwy`) AS `pocz`, MAX(`data gonitwy`) AS `kon` FROM `gonitwa` WHERE `usunieto` = 0";
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        wynikZapytania.next();
        
        return new String[] {wynikZapytania.getString("pocz"), wynikZapytania.getString("kon")};

    }
}
