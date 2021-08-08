/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;

/**
 *
 * @author Ryan
 */
public class OperacaoMatematicaEntity {
    
    // Atributos estaticos e constantes para que facilite a comparação na hora de efetuar as operações
    public final static int SOMA = 0;
    public final static int SUBTRACAO = 1;
    public final static int MULTIPLICACAO = 2;
    public final static int DIVISAO = 3;
    
    // Atributo que recebera todos os valores e operadores vindos do usuário
    private List<Double> valores;
    private String[] operadores;
    private double resultado;

    public List<Double> getValores() {
        return valores;
    }

    public void setValores(List<Double> valores) {
        this.valores = valores;
    }

    public String[] getOperadores() {
        return operadores;
    }

    public void setOperadores(String[] operadores) {
        this.operadores = operadores;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
    
}
