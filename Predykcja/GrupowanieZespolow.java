/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyscigi_konne.Predykcja;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author Admin
 */
public class GrupowanieZespolow 
{
    private String[] konie;
    private String[] dzokeje;
    private final KomponentInformacje informacje;
    private final HashMap<String, Double> wskaznikZespolu;
    
    public GrupowanieZespolow(HashMap<String[], String> zespoly) throws SQLException, ParserConfigurationException, SAXException, IOException
    {
        this.wskaznikZespolu = new HashMap<>();
        this.informacje = new KomponentInformacje();
        
        dopiszObiekty(zespoly);
        zwrocWskazniki();
    }
    
    private void dopiszObiekty(HashMap<String[], String> zespoly) throws SQLException
    {
        int i = 0;
        konie = new String[zespoly.size()];
        dzokeje = new String[zespoly.size()];
        
        for(String[] zespol: zespoly.keySet()) {
            konie[i] = informacje.zwrocIdObiektu(zespol[0].trim(), "konie");
            dzokeje[i] = informacje.zwrocIdObiektu(zespol[1].trim(), "dzokeje");
            
            i++;
        }
    }
    
    private void zwrocWskazniki() throws SQLException, ParserConfigurationException, SAXException, IOException
    {
        HashMap<String, HashMap<String, String>> daneZespolow = new HashMap<>();
        String[] nazwyTagow = new String[]{
            "miejsce", 
            "maksimum", 
            "dystans", 
            "rekordy", 
            "wiek", 
            "odchylenieStandardowe", 
            "grupa", 
            "wycofano",
            "srednia",
            "minimum",
            "rozstep",
            "roznicaSrednich",
            "minimumNaSrednia",
            "maksimumNaSrednia",
            "sredniaNaSrednia"
        };
        
        for(int i = 0; i < konie.length; i++) {
            HashMap<String, String> wskazniki = new HashMap();
            String idZespolu = (informacje.czyIstniejeZespol(konie[i], dzokeje[i])) ? informacje.zwrocIdZespolu() : informacje.zwrocZespolDlaKonia(konie[i]);
            String metadane = informacje.zwrocWskazniki(idZespolu);
            
            DocumentBuilderFactory fabryka = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fabryka.newDocumentBuilder();
            Document dokument = builder.parse(new InputSource(new StringReader(metadane)));
            Element elementGlowny = dokument.getDocumentElement();
            
            for(String tag: nazwyTagow) {
                NodeList lista = elementGlowny.getElementsByTagName(tag);
                if(lista.item(0) != null) {
                    NodeList podLista = lista.item(0).getChildNodes();
                    if(podLista.item(0) != null) {
                        wskazniki.put(tag, podLista.item(0).getNodeValue());
                    }
                }
            }
            
            daneZespolow.put(idZespolu, wskazniki);
        }
        
        przetworzWskaznikiZespolow(daneZespolow, nazwyTagow);
    }
    
    private void przetworzWskaznikiZespolow(HashMap<String, HashMap<String, String>> daneZespolow, String[] nazwyTagow)
    {
        double wartosc = 0;
        double maxWspolczynnik = 0;
        ArrayList<String> gotowe = new ArrayList<>();
        
        daneZespolow.keySet().forEach((idZespolu) -> {
            wskaznikZespolu.put(idZespolu, 0.0);
        });
        
        for(String idZespolu1: daneZespolow.keySet()) {
            double wspolczynnik = (wskaznikZespolu.get(idZespolu1) != 0.0) ? wskaznikZespolu.get(idZespolu1) : 1;
            HashMap<String, String> daneZespolu1 = daneZespolow.get(idZespolu1);

            for(String idZespolu2: daneZespolow.keySet()) {
                if((!idZespolu1.equals(idZespolu2)) && (!gotowe.contains(idZespolu2))) {
                    HashMap<String, String> daneZespolu2 = daneZespolow.get(idZespolu2);
                    
                    
                    for(String tag: nazwyTagow) {
                        if(daneZespolu1.get(tag) != null) {
                            switch(tag) {
                                case "maksimum":
                                case "odchylenieStandardowe":
                                case "srednia":
                                case "minimum":
                                case "minimumNaSrednia":
                                case "maksimumNaSrednia":
                                case "sredniaNaSrednia":
                                    wartosc = (Double.valueOf(daneZespolu1.get(tag))) / (Double.valueOf(daneZespolu2.get(tag)));
                                    break;
                                case "wycofano":
                                    wartosc = (Integer.valueOf(daneZespolu1.get(tag)) != 0) ? 0 : Integer.valueOf(daneZespolu1.get(tag)) * (-2);
                                    break;
                                case "grupa":
                                case "rekordy":
                                    System.out.println(daneZespolu1.get(tag));
                                    wartosc = Integer.valueOf(daneZespolu1.get(tag));
                                    break;
                                case "rozstep":
                                    wartosc = (Integer.valueOf(daneZespolu1.get(tag)) == 0) ? 1 : Integer.valueOf(daneZespolu1.get(tag));
                                    break;
                            }
                        
                            wspolczynnik += wartosc;
                        }
                    }
                    
                    wskaznikZespolu.put(idZespolu1, wspolczynnik);
                    wskaznikZespolu.put(idZespolu2, wspolczynnik);
                }
            }
            
            if(maxWspolczynnik < wspolczynnik)  maxWspolczynnik = wspolczynnik;
            
            gotowe.add(idZespolu1);
        }
        
        for(String idZespolu: wskaznikZespolu.keySet()) {
            double iterator = 0;
            double wsp = wskaznikZespolu.get(idZespolu)/maxWspolczynnik;
            
            for(double i = 0; i <= 1.1; i += 0.05) {
                if(wsp < i) {
                    wskaznikZespolu.put(idZespolu, iterator);
                    break;
                }
                iterator += 0.5;
            }
        }
    }
    
    public HashMap<String, Double> zwrocWskaznikiDlaZespolow()
    {
        HashMap<String, Double> wskaznikowDlaZespolow = new HashMap<>();
        
        wskaznikZespolu.keySet().forEach((idZespolu) -> {
            String[] zespol = informacje.zwrocCzlonkowZespolu(Integer.valueOf(idZespolu));
            wskaznikowDlaZespolow.put(zespol[0].trim(), wskaznikZespolu.get(idZespolu));
        });
        
        System.out.println(wskaznikowDlaZespolow);
        return wskaznikowDlaZespolow;
    }
}
