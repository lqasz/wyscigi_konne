/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public abstract class PolaczZBaza 
{
    private Connection polaczenie;
    protected Statement uchwytDoBazy;
    
    private final String SERWER_BAZY_DANYCH = "localhost";
    private final String PORT_BAZY_DANYCH = "3306";
    private final String NAZWA_BAZY_DANYCH = "test";
    private final String UZYTKOWNIK = "root";
    private final String HASLO = "";
    
    /**
     * Konstruktor łączący się z bazą danych
     */
    public PolaczZBaza()
    {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
            this.polaczenie = DriverManager.getConnection("jdbc:mysql://" + SERWER_BAZY_DANYCH + ":" + PORT_BAZY_DANYCH + "/" + NAZWA_BAZY_DANYCH + "?useUnicode=true&characterEncoding=UTF-8", UZYTKOWNIK, HASLO);
            this.uchwytDoBazy = this.polaczenie.createStatement();
        } catch(ClassNotFoundException | SQLException Wyjątek) {
            System.out.println(Wyjątek);
        }
    }
    
    /**
     * Metoda zwracająca ustawione połączenie z bazą danych
     * @return Statment
     */
    public Statement zwrocPolaczenie()
    {
        return this.uchwytDoBazy;
    }
}
