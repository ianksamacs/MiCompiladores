/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomp.uefs.br.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ianc Samac
 */
public class Teste {
    
    
    
    public static void main(String[] args) {
        
        
        
        FileReader file = null;
        BufferedReader lerArq = null;
        String linha = "yy";
       
       
        try {
            file = new FileReader("entrada.txt");
            lerArq = new BufferedReader(file);
            linha = lerArq.readLine();
            while (linha != null){
                if(linha != null)
                    separador(linha, " ");
                linha = lerArq.readLine();
            }
        }catch(IOException e){
            System.out.println("Arquivo de entrada não encontrado!");
        }
        
    }

    
    static void temDelimitador(String l){
        if(l.contains(";"))
            separador(l,";");
        else if(l.contains(","))
            separador(l,",");
        else if(l.contains("("))
            separador(l,"(");
        else if(l.contains(")"))
            separador(l,")");
        else if(l.contains("{"))
            separador(l,"{");
        else if(l.contains("}"))
            separador(l,"}");
        else if(l.contains("["))
            separador(l,"[");
        else if(l.contains("]"))
            separador(l,"]");
        else{
        verificar(l);
        }
    }
    
    static void separador(String l, String d){
        String array[] = l.split(d);
        for(int i=0;i<array.length;i++)
            temDelimitador(array[i]);
    }
    
    static int verificar(String l){
        
        if(verificarPR(l)){
            System.out.println("Palavra Reservada : "+l);
        }else if(verificarIdent(l)){
            System.out.println("Identificador : " +l);
        }else{
            System.out.println("Desconhecida : "+l);
        }
       return 0;
    }
    
    static boolean verificarPR(String l){
        String pr[] = {"const", "var", "struct", "typedef", "procedure", "function", "return", "start", "if",
                       "then", "else", "while", "scan", "print", "int", "float", "bool", "string", "true",
                       "false", "extends"};
        for(int i=0; i<pr.length;i++){
            if(pr[i].equals(l))
                return true;
        }
        return false;
    }
    
    static boolean verificarIdent(String l){
        if(!Character.isLetter(l.charAt(0))){
            return false;
        }else{
            for(int i=1; i<l.length();i++){
                if(!Character.isLetter(l.charAt(i)) && !Character.isDigit(l.charAt(i)) && l.charAt(i) != '_'){
                    return false;
                }
            }
        }
            return true;
}

}