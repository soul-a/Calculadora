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
    }
    
    private boolean VerificaRepeticaoOpMat(String texto, JTextField txt_Preview) {
        
        System.out.println(ultimoChars);
        
        if(texto.contains(ultimoChars) && 
                (texto.contains("+") || 
                texto.contains("-") || 
                texto.contains("*") || 
                texto.contains("/"))) {
            
            return false;
        } 
        else { 
            switch(texto) {
                case "+":
                    if(ultimoChars.contains("-") || ultimoChars.contains("*") || ultimoChars.contains("/")) {
                        txt_Preview.setText(
                                txt_Preview.getText()
                                        .substring(0, txt_Preview.getText().length() - 1)
                        );
                        return true;
                    }
                case "-":
                    if(ultimoChars.contains("+") || ultimoChars.contains("*") || ultimoChars.contains("/")) {
                        txt_Preview.setText(
                                txt_Preview.getText()
                                        .substring(0, txt_Preview.getText().length() - 1)
                        );
                        return true;
                    }
                case "*":
                    if(ultimoChars.contains("+") || ultimoChars.contains("-") || ultimoChars.contains("/")) {
                        txt_Preview.setText(
                                txt_Preview.getText()
                                        .substring(0, txt_Preview.getText().length() - 1)
                        );
                        return true;
                    }
                case "/":
                    if(ultimoChars.contains("+") || ultimoChars.contains("-") || ultimoChars.contains("*")) {
                        txt_Preview.setText(
                                txt_Preview.getText()
                                        .substring(0, txt_Preview.getText().length() - 1)
                        );
                        return true;
                    }
            }
        }
        return true;
    }
}
