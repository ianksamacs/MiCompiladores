package Sintatico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Lexico.Token;
import java.util.ArrayList;
import javax.swing.JOptionPane;



/**
 *
 * @author Joimar
 */
public class Parser {
    
  private  ArrayList<Token> tokens;
  private int contador = 0;
  private int tamanho = 0;
  private  ArrayList<String> erros = new ArrayList();
  
  public Parser(ArrayList<Token> tokens)
  {
  
      this.tokens = tokens;
      tamanho = tokens.size();
      
  }
  
  public boolean Programa()
  {
  
      if(escopoGlobal() && start() && funcoesOuProcedimentos())
      {
      
          System.out.println("Sem erros sintaticos");
          return true;
      }
      else return false;
  
  }
  
  public boolean escopoGlobal()
  {
      //follow
      if(tokens.get(contador).getValor().equals("start"))
      {
          if(start()) return true;
          else return false;
      }
      //else if(){}
      return false;
  }
  
  public boolean funcoesOuProcedimentos()
  {
  
      return false;
  
  }
    
  public boolean start()
  {
      if(tokens.get(contador).getValor().equals("start"))
      {
      
          contador++;
          if(bloco())
          {
          
              return true;
          }
      }
      return false;
  }
  
  public boolean bloco()
  {
      
    if(tokens.get(contador).getValor().equals("bloco") && array())
    {
      
        System.out.println("Bloco");
        return true;
    }
    else 
    { 
        System.out.println("Erro: sem Bloco");
        return false;
    }
             
  }
  
  public boolean comando()
  {
      
      if(tokens.get(contador).getValor().equals("comando"))
      {
      
          return true;
      }
      return false;
  }
  
  public boolean If()
  {
    boolean certo = true;
    if(tokens.get(contador).getValor().equals("if") && array()){ exibe(); contador++;}
    else{ certo = false; erroAdd("if",tokens.get(contador)); if(array()) contador++;}
    
    if(tokens.get(contador).getValor().equals("(") && array()){ exibe(); contador++;}
    else{certo = false; erroAdd("(" ,tokens.get(contador)); if(array()) contador++;}
    
    if(comando() && array()){exibe(); contador++;}
    else{certo = false; if(array()) contador++;}
    
    if(tokens.get(contador).getValor().equals(")") && array()){ exibe(); contador++;}
    else{certo = false; exibe(); 
    erroAdd(")",tokens.get(contador)); if(array()){ contador++;}}
    
    if(tokens.get(contador).getValor().equals("then") && array()){exibe(); contador++;}
    else{certo = false; erroAdd("then",tokens.get(contador)); if(array()) contador++;}
    
    if(comando() && array()){exibe(); contador++;}
    else{ certo = false; if(array()) contador++;}
    
    if(Else() && array()){exibe(); contador++;}
    else{certo = false; if(array()) contador++;}
    System.out.print(erros);
    return certo;
  }
  
  
  public boolean expressaoLogicaOuRelacional()
  {
  
      if(tokens.get(contador).getValor().equals("expressaoLogicaOuRelacional"))
      {
      
          return true;
      }
      else return false;
  
  }
  
  public boolean Else()
  {
  
      if(tokens.get(contador).getValor().equals("else")){return true;}
      else if(comando()) return true;
      else return false;
  
  }
  
  public void Analisar()
  {
  
      If();
  
  }
  
  public boolean array()  //para o nao sair do escopo da lista
  {
      if(contador<tamanho-1) return true;
      else return false;
  
  }
  
  public void exibe()
  {
  
      System.out.println(tokens.get(contador).getValor());
  
  }
  
  public void erroAdd(String erro, Token token)
  {
  
      erros.add("Esperado "+ erro + " na linha " + token.getLinha());
  }
  
  
}
