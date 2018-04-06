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
    private String tipo;
    private String valor;
    
    public Token(String tipo, String value){
        this.tipo = tipo;
        valor = value;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String getValor(){
        return valor;
    }
}
