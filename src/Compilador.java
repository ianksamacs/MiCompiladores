
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class Compilador {
    
    static ArrayList<Token> tokens;
    static int status, lStatus;
    static String palavraAtual;
    
    
    public static void main(String[] args) {
        tokens = new ArrayList<Token>();
        status = 0;
        lStatus = status;
        palavraAtual = "";
        int test;
        AnaliseLexico al = new AnaliseLexico();
        FileReader file = null;
        BufferedReader lerArq = null;
        String linha = "yy";
        try {
            file = new FileReader("entrada.txt");
            lerArq = new BufferedReader(file);
            linha = lerArq.readLine();
            while (linha != null){
                  for(int i=0;i<linha.length();i++){
                      char c = linha.charAt(i);
                      test = al.nextStep(status, c, palavraAtual, 0);
                      next(test,c, 0);
                  }
                  test = al.nextStep(status, 'z', palavraAtual, 1); 
                  next(test,'x', 1);
                  linha = lerArq.readLine();
            }
            listar();
        }catch(IOException e){
            System.out.println("Arquivo de entrada não encontrado!");
        }
    }
    
    public static void next(int test, char c, int end){
        if(end != 1 && c != ' '){
            palavraAtual += c;
            //System.out.println("status: "+status+" palavra: "+palavraAtual+" entrada: "+c);
        }
        if(test == -1){
            add("Palavra reservada");
        }else if(test == -2){
            add("Identificador");   
        }else if(test == -4){
            add("Numero");                  
        }else if(test == 32){
            System.out.println("Error");
            status = 0;
            palavraAtual = "";
        }else{
            lStatus = status;
            status = test;
        }
                       
    }
    
    public static void add(String tipo){
        Token t = new Token(tipo, palavraAtual);
        tokens.add(t);
        palavraAtual = "";
        status = 0;
    }
    
    public static void listar(){
        Iterator it = tokens.iterator();
        Token aux = null;
        while(it.hasNext()){
            aux = (Token)it.next();
            System.out.println(aux.getTipo()+";"+aux.getValor());
        }
    }
}
