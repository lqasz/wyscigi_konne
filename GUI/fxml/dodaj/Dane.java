
package wyscigi_konne.GUI.fxml.dodaj;

import java.sql.ResultSet;
import java.sql.SQLException;
import wyscigi_konne.Predykcja.PolaczZBaza;


public class Dane extends PolaczZBaza  
{
    public Dane()
    {
        
    }
    
    public int zwrocIdKonia(String kon, String wiek, String grupa) throws SQLException
    {
        ResultSet wynikZapytania;
        
        String zapytanie = "SELECT id "
                            + "FROM konie "
                            + "WHERE nazwa = '"+kon+"'";        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        
        if (!wynikZapytania.next())
        {
            String insert = "INSERT "
                            + "INTO `konie`(`nazwa`, `wiek`, `grupa`) "
                            + "VALUES ('"+kon+"','"+wiek+"','"+grupa+"')"; 
            
            this.uchwytDoBazy.executeUpdate(insert);
        }
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        wynikZapytania.next();
        int id = wynikZapytania.getInt("id");

        return id;
    }
    
    public int zwrocIdDzokeja(String jezdziec, String waga) throws SQLException
    {
        ResultSet wynikZapytania;
        
        String zapytanie = "SELECT id "
                            + "FROM dzokeje "
                            + "WHERE jezdziec = '"+jezdziec+"'";        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        
        if (!wynikZapytania.next())
        {
            String insert = "INSERT "
                            + "INTO `dzokeje`(`jezdziec`, `waga`) "
                            + "VALUES ('"+jezdziec+"', '"+waga+"')"; 
            
            this.uchwytDoBazy.executeUpdate(insert);
        }
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);
        wynikZapytania.next();
        int id = wynikZapytania.getInt("id");

        return id;
    }
    
    public void dodajInformacje(String dystans, String czas, String temperatura, String styl, String odleglosci, String stantoru, String rekordy) throws SQLException
    {
            String insert = "INSERT "
                            + "INTO `informacje`(`dystans`, `czas`, `temperatura`, `styl`, `odleglosci`, `stan toru`, `rekordy`) "
                            + "VALUES ('"+dystans+"', '"+czas+"', '"+temperatura+"', '"+styl+"', '"+odleglosci+"', '"+stantoru+"', '"+rekordy+"')"; 
            
            this.uchwytDoBazy.executeUpdate(insert);             
        
    }
    
    public int zwrocIdOstatniejInformacji() throws SQLException
    {
        ResultSet wynikZapytania;
        
        String zapytanie = "SELECT max(id) FROM informacje";        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);        
         
        int id = wynikZapytania.getInt("id");

        return id;
    }
    
    public void dodajGonitwa(int idKonia, int idDzokeja, String miejsce, String nrStartowy, String dataGonitwy) throws SQLException
    {
            String insert;
            String zero = Integer.toString(0);
            if (miejsce == zero)
            {
            insert = "INSERT "
                            + "INTO `gonitwa`(`id konia`, `id dzokeja`, `wycofano`, `miejsce`, `nr startowy`, `data gonitwy`) "
                            + "VALUES ('"+idKonia+"', '"+idDzokeja+"', 'Wycofano przez lekarza', '"+miejsce+"', '"+nrStartowy+"', '"+dataGonitwy+"')"; 
            } else
                {
                    insert = "INSERT "
                            + "INTO `gonitwa`(`id konia`, `id dzokeja`, `miejsce`, `nr startowy`, `data gonitwy`) "
                            + "VALUES ('"+idKonia+"', '"+idDzokeja+"', '"+miejsce+"', '"+nrStartowy+"', '"+dataGonitwy+"')";
                }
            
            this.uchwytDoBazy.executeUpdate(insert);         
    }
    
    public int zwrocIdOstatniaGonitwa() throws SQLException
    {
        ResultSet wynikZapytania;
        
        String zapytanie = "SELECT max(id) FROM gonitwa";        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);        
         
        int id = wynikZapytania.getInt("id");

        return id;
    }
    
    public void dodajGonitwaInformacje(int idInformacji, int idGonitwy) throws SQLException
    {
            
            String insert = "INSERT "
                            + "INTO `gonitwainformacje`(`id informacji`, `id gonitwy`) "
                            + "VALUES ('"+idInformacji+"', '"+idGonitwy+"')"; 
            
            this.uchwytDoBazy.executeUpdate(insert);           
    }
    
    public void dodajZespol(int idKonia, int idDzokeja, String styl) throws SQLException
    {            
        String insert = "INSERT "
                        + "INTO `zespol`(`id konia`, `id dzokeja`, `styl`) "
                        + "VALUES ('"+idKonia+"', '"+idDzokeja+"', '"+styl+"')"; 
            
        this.uchwytDoBazy.executeUpdate(insert);           
    }
    
    public int zwrocIdZespol() throws SQLException
    {
        ResultSet wynikZapytania;
        
        String zapytanie = "SELECT max(id) FROM zespol";        
        
        wynikZapytania = this.uchwytDoBazy.executeQuery(zapytanie);        
         
        int id = wynikZapytania.getInt("id");

        return id;
    }
    
    public void dodajWskazniki(int idZespolu) throws SQLException
    {            
        String insert = "INSERT "
                        + "INTO `wskazniki`(`id`, `id zespolu`) "
                        + "VALUES ('"+idZespolu+"', '"+idZespolu+"')"; 
            
        this.uchwytDoBazy.executeUpdate(insert);           
    }

}
