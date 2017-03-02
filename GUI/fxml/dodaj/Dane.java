
package wyscigi_konne.GUI.fxml.dodaj;

import java.sql.ResultSet;
import java.sql.SQLException;
import wyscigi_konne.Predykcja.PolaczZBaza;


public class Dane extends PolaczZBaza  
{   
    public int zwrocIdKonia(String kon, String wiek, String grupa) throws SQLException
    {
        ResultSet wynikZapytania;
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        
        String zapytanie = "SELECT id "
                            + "FROM konie "
                            + "WHERE nazwa = '"+kon+"'";        
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);
        
        if (!wynikZapytania.next())
        {
            String insert = "INSERT "
                            + "INTO `konie`(`nazwa`, `wiek`, `grupa`) "
                            + "VALUES ('"+kon+"','"+wiek+"','"+grupa+"')"; 
            
            polaczZBaza.zwrocUchwyt().executeUpdate(insert);
        }
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);
        wynikZapytania.next();
        int id = wynikZapytania.getInt("id");
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
        
        return id;
    }
    
    public int zwrocIdDzokeja(String jezdziec, String waga) throws SQLException
    {
        ResultSet wynikZapytania;
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        
        String zapytanie = "SELECT id "
                            + "FROM dzokeje "
                            + "WHERE jezdziec = '"+jezdziec+"'";        
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);
        
        if (!wynikZapytania.next())
        {
            String insert = "INSERT "
                            + "INTO `dzokeje`(`jezdziec`, `waga`) "
                            + "VALUES ('"+jezdziec+"', '"+waga+"')"; 
            
            polaczZBaza.zwrocUchwyt().executeUpdate(insert);
        }
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);
        wynikZapytania.next();
        int id = wynikZapytania.getInt("id");
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
        
        return id;
    }
    
    public void dodajInformacje(String dystans, String czas, String temperatura, String styl, String odleglosci, String stantoru, String rekordy) throws SQLException
    {
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        String insert = "INSERT "
                        + "INTO `informacje`(`dystans`, `czas`, `temperatura`, `styl`, `odleglosci`, `stan toru`, `rekordy`) "
                        + "VALUES ('"+dystans+"', '"+czas+"', '"+temperatura+"', '"+styl+"', '"+odleglosci+"', '"+stantoru+"', '"+rekordy+"')"; 

        polaczZBaza.zwrocUchwyt().executeUpdate(insert);      
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
    }
    
    public int zwrocIdOstatniejInformacji() throws SQLException
    {
        ResultSet wynikZapytania;
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        
        String zapytanie = "SELECT max(id) as id FROM informacje";        
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);        
        wynikZapytania.next();
        int id = wynikZapytania.getInt("id");
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
        
        return id;
    }
    
    public void dodajGonitwa(int idKonia, int idDzokeja, String miejsce, String nrStartowy, String dataGonitwy, String nrGonitwy) throws SQLException
    {
        String insert;
        PolaczZBaza polaczZBaza = new PolaczZBaza();

        if (miejsce.equals("0"))
        {
        insert = "INSERT "
                        + "INTO `gonitwa`(`id konia`, `id dzokeja`, `wycofano`, `miejsce`, `nr startowy`, `data gonitwy`, `nr gonitwy`) "
                        + "VALUES ('"+idKonia+"', '"+idDzokeja+"', 'Wycofano przez lekarza', '"+miejsce+"', '"+nrStartowy+"', '"+dataGonitwy+"', '"+nrGonitwy+"')"; 
        } else
            { 
                insert = "INSERT "
                        + "INTO `gonitwa`(`id konia`, `id dzokeja`, `wycofano`, `miejsce`, `nr startowy`, `data gonitwy`, `nr gonitwy`) "
                        + "VALUES ('"+idKonia+"', '"+idDzokeja+"', '', '"+miejsce+"', '"+nrStartowy+"', '"+dataGonitwy+"', '"+nrGonitwy+"')";
            }

        polaczZBaza.zwrocUchwyt().executeUpdate(insert);
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
    }
    
    public int zwrocIdOstatniaGonitwa() throws SQLException
    {
        ResultSet wynikZapytania;
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        
        String zapytanie = "SELECT max(id) as id FROM gonitwa";        
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);        
        wynikZapytania.next();
        int id = wynikZapytania.getInt("id");
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
        
        return id;
    }
    
    public void dodajGonitwaInformacje(int idInformacji, int idGonitwy) throws SQLException
    {
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        String insert = "INSERT "
                        + "INTO `gonitwainformacje`(`id informacji`, `id gonitwy`) "
                        + "VALUES ('"+idInformacji+"', '"+idGonitwy+"')"; 

        polaczZBaza.zwrocUchwyt().executeUpdate(insert);
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
    }
    
    public void dodajZespol(int idKonia, int idDzokeja, String styl) throws SQLException
    {
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        String insert = "INSERT "
                        + "INTO `zespol`(`id konia`, `id dzokeja`, `styl`) "
                        + "VALUES ('"+idKonia+"', '"+idDzokeja+"', '"+styl+"')"; 
            
        polaczZBaza.zwrocUchwyt().executeUpdate(insert);

        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
    }
    
    public int zwrocIdZespol() throws SQLException
    {
        ResultSet wynikZapytania;
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        
        String zapytanie = "SELECT max(id) as id FROM zespol";        
        
        wynikZapytania = polaczZBaza.zwrocUchwyt().executeQuery(zapytanie);        
         wynikZapytania.next();
        int id = wynikZapytania.getInt("id");
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
        
        return id;
    }
    
    public void dodajWskazniki(int idZespolu) throws SQLException
    {
        PolaczZBaza polaczZBaza = new PolaczZBaza();
        String insert = "INSERT "
                        + "INTO `wskazniki`(`id zespolu`) "
                        + "VALUES ('"+idZespolu+"')"; 
            
        polaczZBaza.zwrocUchwyt().executeUpdate(insert);
        
        polaczZBaza.zwrocUchwyt().close();
        polaczZBaza.zwrocPolaczenie().close();
    }

}
