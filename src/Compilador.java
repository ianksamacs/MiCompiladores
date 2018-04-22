
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
    ArrayList<Token> tokensErro;
    int status;
    String palavraAtual;
    char letraAnterior;
    boolean hasLetra;
    int qtdLinha;
    
    public Compilador() {
        
        tokens = new ArrayList<Token>();
        tokensErro = new ArrayList<Token>();
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
        Iterator it;
        
        ArrayList<String> entradas = new ArrayList<String>();
        Arquivo arq = new Arquivo();
        entradas = arq.lerCodigos();
        it = entradas.iterator();
         qtdLinha = 0;
         String aux;
        try {
            while(it.hasNext()){
            aux = (String) it.next();
            file = new FileReader("entrada/"+aux);
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
            arq.escreverSaidaLexico(tokens, tokensErro, aux);
            tokens = new ArrayList<Token>();
            tokensErro = new ArrayList<Token>();
            status = 0;
            letraAnterior=' ';
            hasLetra = false;
            palavraAtual = "";
            qtdLinha = 0;
           }
            
        }catch(IOException e){
            System.out.println("ERROR!");
        }
    }
    
    public void next(int test, char c, int end){
       //System.out.println("Palavra Atual: "+palavraAtual+"//Entrada: "+c + " -->"+test);
        if(test < 0 || test >30){
            letraAnterior = c;
            hasLetra = true;
        }
        if(test == -1){
            add("PRE");
        }else if(test == -2){
            add("IDE");   
        }else if(test == -3){
            add("NRO");           
        }else if(test == -4){
            add("DEL");           
        }else if(test == -5){
            add("REL");           
        }else if(test == -6){
            add("LOG");           
        }else if(test == -7){
            add("ART");           
        }else if(test == -8){
            add("CCA");           
        }else if(test == -9){
            String numero = palavraAtual.substring(0, palavraAtual.length()-1);
            add("NRO", numero);
            add("DEL", ".");
        }else if(test == 33){
            status = 0;
            palavraAtual = "";
        }else if(test == 32){
            addErro("Err ");
        }else{
            if(c!=' ')
                palavraAtual += c;
            else if(status==11 && status==12 && status==14 && status==24 && status==22)
                palavraAtual += c;
            status = test;
            
        }
                       
    }
    
    
    //Adiciona um token a lista
    public  void add(String tipo){
        Token t = new Token(tipo, palavraAtual,qtdLinha);
        tokens.add(t);
        palavraAtual = "";
        status = 0;
    }
    
    public  void addErro(String tipo){
        Token t = new Token(tipo, palavraAtual,qtdLinha);
        tokensErro.add(t);
        palavraAtual = "";
        status = 0;
        System.out.println("Error");
    }
    
    public  void add(String tipo, String value){
        Token t = new Token(tipo, value,qtdLinha);
        tokens.add(t);
        palavraAtual = "";
        status = 0;
    }
    
    //Lista todos os Tokens
    public  void listar() throws IOException{
        Iterator it = tokens.iterator();
        Token aux = null;
        while(it.hasNext()){
            aux = (Token)it.next();
            System.out.println(aux.getTipo()+";"+aux.getValor());
        }
    }
    
    public  void exportarErros() throws IOException{
        Iterator it = tokens.iterator();
        Token aux = null;
        FileWriter file = new FileWriter("saida.txt");
        BufferedWriter saidaArq = new BufferedWriter(file);
        while(it.hasNext()){
            aux = (Token)it.next();
            if(aux.getTipo().equals("Erro")){
               saidaArq.write(aux.getTipo()+";"+aux.getValor()+"\n"); 
            }
        }
        saidaArq.close();
    }
}
