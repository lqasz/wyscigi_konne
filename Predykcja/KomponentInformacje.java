/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class KomponentInformacje extends PolaczZBaza implements IPodstaweInformacje
{
    private String idZespolu;
    
    /**
     *
     * @param nazwa
     * @param tabela
     * @return
     * @throws SQLException
     */
    @Override
    public String zwrocIdObiektu(String nazwa, String tabela) throws SQLException
    {
        String id = "";
        ResultSet wynikZapytania;
        String pole = ("konie".equals(tabela)) ? "nazwa" : "jezdziec";
        
        String zapytanie = "SELECT `id` FROM `"+ tabela +"` WHERE `"+ pole +"` LIKE '%"+ nazwa +"%'";
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        while(wynikZapytania.next()) {
            id = wynikZapytania.getString("id").trim();
        }
        
        return id;
    }
    
    public boolean czyIstniejeZespol(String idKonia, String idDzokeja) throws SQLException
    {
        boolean wynik = false;
        ResultSet wynikZapytania;
        String zapytanie = "SELECT `id` FROM `zespol` WHERE `id konia` = '"+ idKonia +"' AND `id dzokeja` = '"+ idDzokeja +"'";
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        if(wynikZapytania.next()) {
            idZespolu = wynikZapytania.getString("id").trim();
            wynik = true;
        }
        
        return wynik;
    }
    
    public String zwrocZespolDlaKonia(String idKonia) throws SQLException
    {
        String id;
        ResultSet wynikZapytania;
        String zapytanie = "SELECT `id` FROM `zespol` WHERE `id konia` = '"+ idKonia +"' AND usunieto = 0 GROUP BY `id konia`";
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        wynikZapytania.next();
        id = wynikZapytania.getString("id").trim();
        
        return id;
    }
    
    public String zwrocWskazniki(String idZespolu) throws SQLException
    {
        String metadane;
        ResultSet wynikZapytania;
        String zapytanie = "SELECT `metadane` FROM `wskazniki` WHERE `id zespolu` = '"+ idZespolu +"'";
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        wynikZapytania.next();
        metadane = wynikZapytania.getString("metadane").trim();
        
        return metadane;
    }
    
    public String zwrocIdZespolu()
    {
        return idZespolu;
    }
    
    @Override
    public String[] zwrocCzlonkowZespolu(int idZespolu) {
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
    public HashMap<String, Integer> przeksztalcNaWskazniki(String zapytanie, String pole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
