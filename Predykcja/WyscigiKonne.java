/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

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
            String[] dane = new String[]{"jezdziec", "nazwa", "nr gonitwy", "miejsce"};
            DaneHistoryczne Test = new DaneHistoryczne();
            System.out.println(Test.zwrocDaneGonitwDlaObiektu("kon", "Invisible Dubai", "2016", dane));
            
        } catch (SQLException ex) {
            Logger.getLogger(WyscigiKonne.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
}
