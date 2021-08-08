/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ryan
 */
public class OperacaoMatematicaModel {
    
    public double Soma(double valor, double valorD) {
        
        return valor + valorD;
    }
    
    public double Subtracao(double valor, double valorD) {
        
        return valor - valorD;
    }
    
    public double Multiplicacao(double valor, double valorD) {
       
        return valor * valorD;
    }
    
    public double Divisao(double valor, double valorD) {
        
        return valor / valorD;
    }
}
