/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class WyscigiKonne
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try {
            WyliczWskazniki Test = new WyliczWskazniki();
            Test.wybierzWskaznikiDlaZespolu();
            
        } catch (SQLException ex) {
            Logger.getLogger(WyscigiKonne.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
}
