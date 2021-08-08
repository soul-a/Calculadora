/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ryan
 */
public class CalculadoraController {
    
    private String ultimoChars = "";
    private int qtdeVirgula = 0;
    
    /**
     * Função para escrever no preview da calculadora
     * @param texto
     * @param txt_Preview 
     */
    public void EscrevePreview(String texto, JTextField txt_Preview) {
        try {
            if(VerificaRepeticaoOpMat(texto, txt_Preview)) {
                txt_Preview.setText(txt_Preview.getText() + texto);
                ultimoChars = texto;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(
                    null,
                    "Código do erro - " + e,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        System.out.println(qtdeVirgula);
    }
    
    /**
     * Verifica operadores repetidos e a variação entre els
     * @param texto
     * @param txt_Preview
     * @return 
     */
    private boolean VerificaRepeticaoOpMat(String texto, JTextField txt_Preview) {
        
        // Se o caracteres digitado agora for repetido ele retorna false
        if(texto.contains(ultimoChars) && 
                (texto.contains("+") || 
                texto.contains("-") || 
                texto.contains("*") || 
                texto.contains("/") ||
                texto.contains(","))) {
            
            return false;
        } 
        // Caso não verifica se o caractere vem seguido de outro caractere de operação fazendo assim a substituição
        else { 
            switch(texto) {
                case "+":
                    if(ultimoChars.contains("-") || ultimoChars.contains("*") || ultimoChars.contains("/")) {
                        txt_Preview.setText(
                                txt_Preview.getText()
                                        .substring(0, txt_Preview.getText().length() - 1)
                        );
                        qtdeVirgula = 0;
                        return true;
                    }
                    break;
                case "-":
                    if(ultimoChars.contains("+") || ultimoChars.contains("*") || ultimoChars.contains("/")) {
                        txt_Preview.setText(
                                txt_Preview.getText()
                                        .substring(0, txt_Preview.getText().length() - 1)
                        );
                        qtdeVirgula = 0;
                        return true;
                    }
                    break;
                case "*":
                    if(ultimoChars.contains("+") || ultimoChars.contains("-") || ultimoChars.contains("/")) {
                        txt_Preview.setText(
                                txt_Preview.getText()
                                        .substring(0, txt_Preview.getText().length() - 1)
                        );
                        qtdeVirgula = 0;
                        return true;
                    }
                    break;
                case "/":
                    if(ultimoChars.contains("+") || ultimoChars.contains("-") || ultimoChars.contains("*")) {
                        txt_Preview.setText(
                                txt_Preview.getText()
                                        .substring(0, txt_Preview.getText().length() - 1)
                        );
                        qtdeVirgula = 0;
                        return true;
                    }
                    break;
                case ",": 
                    qtdeVirgula += 1;
                    
                    if(qtdeVirgula <= 1) {
                        return true;
                    }
                    else {
                        return false;
                    }
            }
            
            if(!"0123456789".contains(texto) && "+-*/".contains(texto)) {
                qtdeVirgula = 0;
            }
            
        }
        return true;
    }
}
