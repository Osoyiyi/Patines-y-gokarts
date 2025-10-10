
package Pck_Examen;

import javax.swing.JOptionPane;

public class Hora {
    private int num1;
    private int num2;
    
    public Hora(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public Hora(){
        this(0,0);
    }

    public void setHora(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    
//    public boolean horaCorrecta(){
//        boolean num1Corr = false, num2Corr = false;
//        num1Corr = (num1 >= 0 && num1 < 24);
//        num2Corr = (num2 >= 0 && num2 <60);
//        if(num1Corr && num2Corr){
//            return true;
////        }else{
////            
////        }
//    }
}
