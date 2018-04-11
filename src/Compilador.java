
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
 * @author Ianc Samac
 */
public class Compilador {
    
    ArrayList<Token> tokens;
    int status;
    String palavraAtual;
    char letraAnterior;
    boolean hasLetra;
    int qtdLinha;
    
    public Compilador() {
        
        tokens = new ArrayList<Token>();
        status = 0;
        letraAnterior=' ';
        hasLetra = false;
        palavraAtual = "";
        
    }
    
    public void compilar(){
        AnaliseLexico al = new AnaliseLexico();
        FileReader file = null;
        BufferedReader lerArq = null;
        String linha = "yy";
        qtdLinha = 0;
        try {
            file = new FileReader("entrada.txt");
            lerArq = new BufferedReader(file);
            linha = lerArq.readLine();
            while (linha != null){
                qtdLinha++;
                for(int i=0;i<linha.length();i++){
                    char c = linha.charAt(i);
                    //envia para o analizador o estado e a palavra atual, junto com a entrada
                    //o 0 indica que o ainda não é o fim da linha ou arquivo
                    if(hasLetra){
                        hasLetra = !hasLetra;
                        next(al.nextStep(status, letraAnterior, palavraAtual, 0),letraAnterior, 0);
                    }
                    next(al.nextStep(status, c, palavraAtual, 0),c, 0);
                }
                if(hasLetra){
                        hasLetra = !hasLetra;
                        next(al.nextStep(status, letraAnterior, palavraAtual, 0),letraAnterior, 0);
                    }
                //envia um caractere qlqr junto com o digito 1 para informar um fim de linha/arquivo
                next(al.nextStep(status, ' ', palavraAtual, 1),' ', 1);
                linha = lerArq.readLine();
                
            }
           
            listar();
        }catch(IOException e){
            System.out.println("Arquivo de entrada não encontrado!");
        }
    }
    
    public void next(int test, char c, int end){
       //System.out.println("Palavra Atual: "+palavraAtual+"//Entrada: "+c + " -->"+test);
        if(test < 0 || test >30){
            letraAnterior = c;
            hasLetra = true;
        }
        if(test == -1){
            add("Palavra reservada");
        }else if(test == -2){
            add("Identificador");   
        }else if(test == -3){
            add("Numero");           
        }else if(test == -4){
            add("Operador Aritmetico");           
        }else if(test == -5){
            add("Operador Relacional");           
        }else if(test == -6){
            add("Operador Logico");           
        }else if(test == -7){
            add("Delimitador");           
        }else if(test == -8){
            add("Cadeia de Caracteres");           
        }else if(test == 33){
            System.out.println("Comentario"); 
            status = 0;
            palavraAtual = "";
        }else if(test == 32){
            add("Error na linha "+qtdLinha);
        }else{
            if(c!=' ')
                palavraAtual += c;
            status = test;
            
        }
                       
    }
    
    
    //Adiciona um token a lista
    public  void add(String tipo){
       // System.out.println("Acgou Token "+palavraAtual);
        Token t = new Token(tipo, palavraAtual,qtdLinha);
        tokens.add(t);
        palavraAtual = "";
        status = 0;
    }
    
    //Lista todos os Tokens
    public  void listar(){
        Iterator it = tokens.iterator();
        Token aux = null;
        while(it.hasNext()){
            aux = (Token)it.next();
            System.out.println(aux.getTipo()+";"+aux.getValor());
        }
    }
}
