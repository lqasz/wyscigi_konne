/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public interface IPodstaweInformacje 
{
    public String[] zwrocCzlonkowZespolu(int idZespolu);
    public HashMap<String, Integer> przeksztalcNaWskazniki(String zapytanie, String pole);
}
