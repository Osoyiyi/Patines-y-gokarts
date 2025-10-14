//Godínez Hernández Alberto Carlos
//González Flor José Luis
//Lara Hernandez Alexis Arath
//Redondo Perez Rainy
//Trejo Hernández Joshua

package Pck_Fecha;

import javax.swing.JOptionPane;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;
    
    public Fecha (int d, int m, int a){
        this.dia = d;
        this.mes = m;
        this.anio = a;
    }
    public Fecha (){
        this(1,1,1920);
    }
    public void setFecha(int dia, int mes, int anio){
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    protected boolean bisiesto(){
        if((this.anio%4==0 && this.anio%100!=0) || (this.anio%400==0)){
            return true;
        }else{
            return false;
        }
    }
    public boolean fechaCorrecta(){
        boolean diaCorrecto = false, mesCorrecto = false, anioCorrecto = false;
        
        mesCorrecto = (mes >= 1 && mes <= 12);
        anioCorrecto = (anio >= 1920);
        
        switch(mes){
            case 2:
                if(bisiesto()){
                    diaCorrecto = (dia >= 1 && dia <= 29);
                }else{
                    diaCorrecto = (dia >= 1 && dia <= 28);
                }
            break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                diaCorrecto = (dia >= 1 && dia <= 31);
            case 4:
            case 6:
            case 9:
            case 11:
                diaCorrecto = (dia >= 1 && dia <= 30);
            break;
        }
        if (diaCorrecto && mesCorrecto && anioCorrecto){
            return true; //fecha correcta
        }else{
            JOptionPane.showMessageDialog(null, "La fecha es incorrecta", "Fecha no válida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    @Override
    public String toString(){
        return dia + "/" + mes + "/" + anio;
    }
}
