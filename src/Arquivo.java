/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joimar
 */
public class Arquivo
{
    
    private String localFile;
    private LineNumberReader linha = null;
    private FileReader reader = null;
    private Scanner scan = null;
    /**
     * Busca e retorna todos os códigos fonte presentes na pasta <i>/src/testes/entrada/</i>.
     *
     * @return Lista com os nomes dos códigos fonte presentes na pasta <i>/src/testes/entrada/</i>
     */
    public ArrayList<String> lerCodigos() {
        
        File raiz = new File("entrada/"); // Pasta com os códigos de entrada.
        ArrayList<String> codigos = new ArrayList<>(); // Nomes dos arquivos com os códigos de entrada.
        
        for (File f : raiz.listFiles()) { // Inserindo caminho dos códigos.
           if(f.exists()) codigos.add(f.getName());
        }
        
        
       //System.out.println(codigos);
        
        return codigos;
    }
    
    
    /**
     * Gera o arquivo de saída após a análise do código fonte. Neste arquivo de
     * saída, conterá todos os tokens encontrados no código fonte e os erros
     * encontrados (se houver).
     *
     * @param tokens Lista de tokens obtidos após a análise do código fonte
     * @param erros Erros obtidos após a análise do código fonte
     * 
     * @throws IOException Arquivo de saida não foi gerado com sucesso
     */
    public void escreverSaidaLexico(ArrayList<Token> tokens, ArrayList<Token> erros, String aux) throws IOException {

        FileWriter arq = new FileWriter("saida/" + aux + ".out", false); // Cria o arquivo de saída relacionado ao seu respectivo arquivo de entrada ("mesmo" nome). 

        PrintWriter gravar = new PrintWriter(arq);
            
            for (Token token : tokens) { // Insere os tokens no arquivo de saída.
                if(token.getLinha()<10){
                    gravar.println("0"+token.getLinha() + " " + token.getTipo() + " " + token.getValor());
                }else{
                    gravar.println(token.getLinha() + " " + token.getTipo() + " " + token.getValor());                   
                }
            }
        gravar.println(" ");
        if (erros.isEmpty()) { // Se não houver erros léxicos.
            gravar.printf("Nao ha erros lexicos\n");
        } else { // Se houver erros léxicos, os insere no arquivo de saída.    
            for (Token erro : erros) {
                String error = null;
                
                if(erro.getValor().charAt(0) == '/'){
                    error = "comentario_mal_formado";
                }else if(erro.getValor().charAt(0) == '"'){
                    error = "cadeia_mal_formada";
                }
                if(erro.getLinha()<10){
                    gravar.println("0"+erro.getLinha() + " " + erro.getValor() + " " + error);
                }else{
                    gravar.println(erro.getLinha() + " " + erro.getTipo() + " " + erro.getValor() + " " + error);                   
                }            
            }
        }
        arq.close();
    }
    
}