/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class AnaliseLexico {
    
    int matriz[][];
    public AnaliseLexico(){ 
                                 //  L  N  E  -  _  +  =  /  *  !  <  >  ;  ,  (  )  {  }  [  ]  "  . En  S  &  |
       matriz = new int[][] /*00*/{{ 1, 3, 0, 4,31, 9,15,10, 8,17,15,15,21,21,21,21,21,21,21,21,22,21, 0,31,18,20},
                            /*01*/ { 1, 2,31,31, 2,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*02*/ { 2, 2,31,31, 2,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*03*/ {31, 3,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31, 6,31,31,31,31},
                            /*04*/ {31, 3, 5, 8,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*05*/ {31, 3, 5,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*06*/ {31, 7,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*07*/ {31, 7,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*08*/ {31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*09*/ {31,31,31,31,31, 8,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*10*/ {31,31,31,31,31,31,31,14,11,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*11*/ {11,11,11,11,11,11,11,11,12,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11},
                            /*12*/ {11,11,11,11,11,11,11,13,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11},
                            /*13*/ {33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33},
                            /*14*/ {14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,33,14,14,14},
                            /*15*/ {31,31,31,31,31,31,16,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*16*/ {31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*17*/ {31,31,31,31,31,31,16,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*18*/ {31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,19,31},
                            /*19*/ {31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*20*/ {31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,19},
                            /*21*/ {31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*22*/ {22,22,22,22,22,22,22,24,22,22,22,22,22,22,22,22,22,22,22,22,23,22,31,22,22,22},
                            /*23*/ {31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31},
                            /*24*/ {22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,31,22,22,22}};
                              
    }
    
    public int entrada(char c){
        if(Character.isLetter(c)){
            return 0;
        }else if(Character.isDigit(c)){
            return 1;
        }else if(Character.isWhitespace(c)){
            return 2;
        }else if(c == '-'){
            return 3;
        }else if(c == '.'){
            return 21;
        }else if(c == '*'){
            return 8;
        }else if(c == '/'){
            return 7;
        }else if(c == '+'){
            return 5;
        }else if(c == '='){
            return 6;
        }else if(c == '<'){
            return 10;
        }else if(c == '>'){
            return 11;
        }else if(c == '!'){
            return 9;
        }else if(c == '&'){
            return 24;
        }else if(c == '|'){
            return 25;
        }else if(c == ';'){
            return 12;
        }else if(c == ','){
            return 13;
        }else if(c == '('){
            return 14;
        }else if(c == ')'){
            return 15;
        }else if(c == '{'){
            return 16;
        }else if(c == '}'){
            return 17;
        }else if(c == '['){
            return 18;
        }else if(c == ']'){
            return 19;
        }else if(c == '"'){
            return 20;
        }else if(c == '_'){
            return 4;
        }else if(isSimbolo(c)){
            return 23;
        }
        return -1;
    }
    
    public int nextStep(int q0, char c, String l, int endl){
        int step = entrada(c),  nextS;
        if(endl==1){
            step = 22;
        }
        nextS= matriz[q0][step];
        if(nextS==33)
            return 33;
        if(nextS==31){
            //Se interroper no estado 1 verifica se é palavra reservada ou id
            if(q0==1){
                if(verificarPR(l)){
                    return -1;
                }else{
                    return -2;
                }
            //ID
            }else if(q0==2){
                return -2;
            //Numero
            }else if(q0 == 3 || q0 == 7){
                return -3;
            //Operador Aritimetico
            }else if(q0 == 4|| q0 == 8 || q0 == 9 || q0 == 5 || q0 == 10){
                return -4;
            //Operador Relacional
            }else if(q0 == 15|| q0 == 16){
                return -5;
            //Operador Logico
            }else if(q0 == 17|| q0 == 19){
                return -6;
            //Delimitador
            }else if(q0 == 21){
                return -7;
            //Cadeia de Caracteres
            }else if(q0 == 23){
                return -8;
            }else if(q0 == 6){
                return -9;
            }else{
                return 32;
            }
        }
        return nextS; //matriz[q0][entrada(c)];
    }
    
    //verifica se é palavra reservada
    private boolean verificarPR(String l){
        String pr[] = {"const", "var", "struct", "typedef", "procedure", "function", "return", "start", "if",
                       "then", "else", "while", "scan", "print", "int", "float", "bool", "string", "true",
                       "false", "extends"};
        for(int i=0; i<pr.length;i++){
            if(pr[i].equals(l))
                return true;
        }
        return false;
    }
    
    private boolean isSimbolo(char c){
        int d = Character.valueOf(c);
        if(c=='#'||c=='$'||d==92||c=='?'||c=='@'||c=='`'||c=='~'||c=='^'){
            return true;
        }else{
        return false;
        }
    }
}
