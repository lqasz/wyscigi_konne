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
import java.util.logging.Level;
import java.util.logging.Logger;
import wyscigi_konne.GUI.fxml.statystyka.gonitwa.TabelaGonitwController;

/**
 *
 * @author Admin
 */
public class PolaczZBaza implements IDanePolaczenia
{
    private Connection polaczenie;
    private Statement uchwytDoBazy;
    
    /**
     * Konstruktor łączący się z bazą danych
     */
    public PolaczZBaza()
    {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
            this.polaczenie = DriverManager.getConnection("jdbc:mysql://" + SERWER_BAZY_DANYCH + ":" + PORT_BAZY_DANYCH + "/" + NAZWA_BAZY_DANYCH + "?useUnicode=true&characterEncoding=UTF-8", UZYTKOWNIK, HASLO);
            this.uchwytDoBazy = this.polaczenie.createStatement();
        } catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TabelaGonitwController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda zwracająca ustawione połączenie z bazą danych
     * @return Statment
     */
    public Statement zwrocUchwyt()
    {
        return this.uchwytDoBazy;
    }
    
    /**
     * Metoda zwracająca ustawione połączenie z bazą danych
     * @return Statment
     */
    public Connection zwrocPolaczenie()
    {
        return this.polaczenie;
    }
}
