package Lexico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class Token {
    private String tipo, valor;
    private int linha;
    
    public Token(String tipo, String valor, int linha){
        this.tipo = tipo;
        this.valor = valor;
        this.linha = linha;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String getValor(){
        return valor;
    }
    
    public int getLinha(){
        return linha;
    }
}
