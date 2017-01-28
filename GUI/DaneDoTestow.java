package wyscigikonne;

import java.util.Random;

public class DaneDoTestow {
    
    public final int[] tabela = new int[5];
    public final String[] tabelaD = new String[5];
    public final String[] tabelaI = new String[5];
    private final Random generator = new Random();
    
    public DaneDoTestow(){
        
        mocKoni();
        nazwyKoni();
    }
    
    private void mocKoni(){
        for(int i = 0; i < tabela.length; i++){
            tabela[i] = generator.nextInt();
        }
    }
    
    private void nazwyKoni(){
        
        tabelaI[0] = "Ab";
        tabelaI[1] = "Bc";
        tabelaI[2] = "Cd";
        tabelaI[3] = "De";
        tabelaI[4] = "Ef";        
    }
    
    public void dystanse(){
         
      tabelaD[0] = "1400";
      tabelaD[1] = "1600";
      tabelaD[2] = "1800";
      tabelaD[3] = "2000";
      tabelaD[4] = "2400";  
    } 
}
