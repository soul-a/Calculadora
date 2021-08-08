/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.OperacaoMatematicaEntity;
import Model.OperacaoMatematicaModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ryan
 */
public class OperacaoMatematicaController {
    
    private OperacaoMatematicaEntity opMatEntity = new OperacaoMatematicaEntity();
    private OperacaoMatematicaModel opMatModel = new OperacaoMatematicaModel();
    
    public void ProcessaValoresOperadores(JTextField txt_Preview, JLabel lbl_Preview) {
        
        String texto = txt_Preview.getText();
        
        if("+-*/,".contains(texto.substring(texto.length() - 1, texto.length()))) {
            JOptionPane.showMessageDialog(
                    null,
                    "Não é possível prosseguir, a operação não pode terminar com nenhum destes operadores: \n\"+ - * /\" ou foi enviado somente números sem nenhuma operação presente",
                    "Ocorreu um erro ao processar",
                    0);
            return;
        }
        // Caso for enviado somente números, ele tenta fazer a conversão se a conversão for um sucesso ele retorna
        else {
            try {
                Double.parseDouble(texto);
                
                JOptionPane.showMessageDialog(
                        null,
                        "Não é possível prosseguir, a operação não pode terminar com nenhum destes operadores: \n\"+ - * /\" ou foi enviado somente números sem nenhuma operação presente",
                        "Ocorreu um erro ao processar",
                        0);
                
                return;
            } catch (Exception e) {}
        }
            
        // Varáveis para separar os operadores e os valores
        List<Double> valores = new ArrayList<>();
        String[] valoresStr = {};
        String[] operadores = {};
        
        // Separa os operadores
        operadores = texto.replace(",", "")
                .replace("0", "")
                .replace("1", "")
                .replace("2", "")
                .replace("3", "")
                .replace("4", "")
                .replace("5", "")
                .replace("6", "")
                .replace("7", "")
                .replace("8", "")
                .replace("9", "")
                .split("");
        
        // Separa os valores em uma String antes de converter para Double
        valoresStr = texto.replace(",", ".")
                .replace("+", ";")
                .replace("-", ";")
                .replace("*", ";")
                .replace("/", ";")
                .split(";");
        
        // Percorre o vetor de valores que está em String convertendo para Double a adicionando à lista de valores
        for(String s : valoresStr) {
            valores.add(Double.parseDouble(s));
        }
        
        // Exibe os valores para parâmetro
        for(String s : operadores) {
            System.out.println("Op:" + s);
        }
        for(double s : valores) {
            System.out.println("Valores:" + s);
        }
        
        opMatEntity.setValores(valores);
        opMatEntity.setOperadores(operadores);
        
        if(CalculaValores(opMatEntity)) {
            lbl_Preview.setText(texto);
            txt_Preview.setText(String.valueOf(opMatEntity.getResultado()).replace(".", ","));
        }
        
    }
    
    private boolean CalculaValores(OperacaoMatematicaEntity opMatEnt) {
        
        double resultadoDinamico = .0;
        int sizeValores = opMatEnt.getValores().size();
        System.out.println(sizeValores);
        System.out.println(opMatEnt.getValores().get(0));
        System.out.println(opMatEnt.getValores().get(1));
        boolean flag = true;
        
        for(String operador : opMatEnt.getOperadores()) {
            
            for(int i = 0; i < sizeValores; i++) {
                
                switch(operador) {
                    case "+":
                        if(i == 0) {
                            
                            if(sizeValores == 2) {
                                resultadoDinamico = opMatModel.Soma(opMatEnt.getValores().get(i), opMatEnt.getValores().get(i + 1));
                                break;
                            }
                            
                            resultadoDinamico = opMatModel.Soma(opMatEnt.getValores().get(i), opMatEnt.getValores().get(i + 1));
                        }
                        else if(sizeValores != i + 1){
                            
                            int j = i + 1;
                            
                            if(sizeValores < i + 1)
                                j = i;
                            
                            resultadoDinamico = opMatModel.Soma(resultadoDinamico, opMatEnt.getValores().get(j));
                        }
                        break;
                        
                        
                    case "-":
                        if(i == 0) {
                            
                            if(sizeValores == 2) {
                                resultadoDinamico = opMatModel.Subtracao(opMatEnt.getValores().get(i), opMatEnt.getValores().get(i + 1));
                                break;
                            }
                            
                            resultadoDinamico = opMatModel.Subtracao(opMatEnt.getValores().get(i), opMatEnt.getValores().get(i + 1));
                        }
                        else if(sizeValores != i + 1){
                            
                            int j = i + 1;
                            
                            if(sizeValores < i + 1)
                                j = i;
                            
                            resultadoDinamico = opMatModel.Subtracao(resultadoDinamico, opMatEnt.getValores().get(j));
                        }
                        break;
                        
                        
                    case "*":
                        if(i == 0) {
                            
                            if(sizeValores == 2) {
                                resultadoDinamico = opMatModel.Multiplicacao(opMatEnt.getValores().get(i), opMatEnt.getValores().get(i + 1));
                                break;
                            }
                            
                            resultadoDinamico = opMatModel.Multiplicacao(opMatEnt.getValores().get(i), opMatEnt.getValores().get(i + 1));
                        }
                        else if(sizeValores != i + 1){
                            
                            int j = i + 1;
                            
                            if(sizeValores < i + 1)
                                j = i;
                            
                            resultadoDinamico = opMatModel.Multiplicacao(resultadoDinamico, opMatEnt.getValores().get(j));
                        }
                        break;
                        
                        
                    case "/":
                        if(opMatEnt.getValores().get(i) == 0 || opMatEnt.getValores().get(i + 1) == 0) {
                            
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Não foi possível comlpletar o cálculo, pois o resultado da operação anterior a divisão é 0",
                                    "Não foi possível realizar a divisão",
                                    i
                            );
                            flag = false;
                            break;
                        }
                        
                        if(i == 0) {
                            if(sizeValores == 2) {
                                resultadoDinamico = opMatModel.Divisao(opMatEnt.getValores().get(i), opMatEnt.getValores().get(i + 1));
                                break;
                            }
                            
                            resultadoDinamico = opMatModel.Divisao(opMatEnt.getValores().get(i), opMatEnt.getValores().get(i + 1));
                        }
                        else if(sizeValores != i + 1) {
                            int j = i + 1;
                            
                            if(sizeValores < i + 1)
                                j = i;
                            
                            resultadoDinamico = opMatModel.Multiplicacao(resultadoDinamico, opMatEnt.getValores().get(j));
                        }
                        else if(resultadoDinamico == .0) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Não foi possível comlpletar o cálculo, pois o resultado da operação anterior a divisão é 0",
                                    "Não foi possível realizar a divisão",
                                    i
                            );
                            
                            flag = false;
                        }
                        break;
                }
                System.out.println("Result: " + resultadoDinamico);
            }
        }
        
        if(flag)
            opMatEnt.setResultado(resultadoDinamico);
            
        
        return flag;
    }
}
