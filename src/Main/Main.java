package Main;

import Lexico.Compilador;
import Sintatico.Parser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class Main {
    public static void main(String[] args) {
        
        Compilador com = new Compilador();
        com.compilar();
        com.getTokens();
        Parser sintatico = new Parser(com.getTokens());
        sintatico.Analisar();
        
    }
}
