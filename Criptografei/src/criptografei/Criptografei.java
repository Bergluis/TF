package criptografei;

import java.util.Scanner;

public class Criptografei {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o codigo: ");
        int chave = entrada.nextInt();
        System.out.println("Digite a senha: ");
        String senha = entrada.next();
        //String[] alfabeto = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "@", "#", "$", "%", "&", "*", "-", "+"};
        while(chave > 70){
            chave -= 70;
        }
        String senhaPronta = criptografa(senha,chave);
        System.out.println("Senha criptografada: " + senhaPronta);
        String senhaNormal = descriptografa(senhaPronta, chave);
        System.out.println("Senha descriptografada: " + senhaNormal);
    }
    
    static public String criptografa(String senha, int chave){
        boolean x = false;
        int indiceSenha = 0, indiceAlfabeto = 0, numAlfabeto = 1, tamanhoSenha = senha.length();
        String senhaPronta = "";
        String[] embaralhado1 = {"N","f","$","M","K","L","2","*","t","P","D","a","@","W","g","U","j","H","h","s","-","d","F","9","k","J","G","0","X","q","z","x","5","Z","S","T","u","&","p","6","n","%","O","1","+","7","4","C","R","o","y","Q","A","v","b","#","e","r","c","3","Y","w","V","i","8","l","m","I","B","E"};
        String[] embaralhado2 = {"0","H","X","6","4","g","Q","B","n","o","R","m","D","L","7","e","2","b","5","8","t","#","f","*","9","P","p","V","O","Y","@","$","w","d","k","j","z","U","l","c","W","T","S","K","x","I","u","Z","+","G","3","v","h","&","r","a","F","1","J","E","C","s","q","y","N","M","A","%","-","i"};
        String[] embaralhado3 = {"u","F","R","*","1","C","r","y","@","w","L","%","m","j","n","G","#","&","9","H","U","6","g","Q","M","l","b","t","D","f","P","Z","+","W","2","S","X","4","v","x","V","q","s","8","-","d","e","7","0","J","3","O","$","c","p","h","5","E","Y","T","I","z","K","N","B","i","A","a","o","k"};
        String[] usando = new String[70];
        for(int i = 0; i < senha.length(); i++){
           while(x==false){
               //selecionando alfabeto para o caractere
               boolean b = false;
               while(b==false){
                   if(numAlfabeto == 1){
                        usando = embaralhado1;
                        b=true;
                    } else if(numAlfabeto == 2){
                        usando = embaralhado2;
                        b=true;
                    } else if(numAlfabeto == 3){
                        usando = embaralhado3;
                        b=true;
                    } else if(numAlfabeto>3){
                        numAlfabeto -= 3;
                    }
               }
               //separando cada caractere
                        if(senha.length() <= indiceSenha){
                            x=true;
                            break;
                        }
                    char caractereSenha = senha.charAt(indiceSenha);
                    String caractereSenhaString = ""+caractereSenha;
                    if(caractereSenhaString.equals(usando[indiceAlfabeto])){
                        int indiceCaractereCriptografado = indiceAlfabeto+chave;
                        if(indiceCaractereCriptografado > 69){
                            indiceCaractereCriptografado -= 70;
                        }
                        senhaPronta = senhaPronta+usando[indiceCaractereCriptografado];
                        indiceSenha++;
                        numAlfabeto++;
                        indiceAlfabeto = 0;
                        
                     } else {
                        indiceAlfabeto++;
                     }
            }
        }            
        return senhaPronta;
    }
    
    static public String descriptografa(String senha, int chave){
        boolean x = false;
        int indiceSenha = 0, indiceAlfabeto = 0, numAlfabeto = 1, tamanhoSenha = senha.length();
        String senhaPronta = "";
        String[] embaralhado1 = {"N","f","$","M","K","L","2","*","t","P","D","a","@","W","g","U","j","H","h","s","-","d","F","9","k","J","G","0","X","q","z","x","5","Z","S","T","u","&","p","6","n","%","O","1","+","7","4","C","R","o","y","Q","A","v","b","#","e","r","c","3","Y","w","V","i","8","l","m","I","B","E"};
        String[] embaralhado2 = {"0","H","X","6","4","g","Q","B","n","o","R","m","D","L","7","e","2","b","5","8","t","#","f","*","9","P","p","V","O","Y","@","$","w","d","k","j","z","U","l","c","W","T","S","K","x","I","u","Z","+","G","3","v","h","&","r","a","F","1","J","E","C","s","q","y","N","M","A","%","-","i"};
        String[] embaralhado3 = {"u","F","R","*","1","C","r","y","@","w","L","%","m","j","n","G","#","&","9","H","U","6","g","Q","M","l","b","t","D","f","P","Z","+","W","2","S","X","4","v","x","V","q","s","8","-","d","e","7","0","J","3","O","$","c","p","h","5","E","Y","T","I","z","K","N","B","i","A","a","o","k"};
        String[] usando = new String[70];
        for(int i = 0; i < senha.length(); i++){
           while(x==false){
               //selecionando alfabeto para o caractere
               boolean b = false;
               while(b==false){
                   if(numAlfabeto == 1){
                        usando = embaralhado1;
                        b=true;
                    } else if(numAlfabeto == 2){
                        usando = embaralhado2;
                        b=true;
                    } else if(numAlfabeto == 3){
                        usando = embaralhado3;
                        b=true;
                    } else if(numAlfabeto>3){
                        numAlfabeto -= 3;
                    }
               }
               //separando cada caractere
                        if(senha.length() <= indiceSenha){
                            x=true;
                            break;
                        }
                    char caractereSenha = senha.charAt(indiceSenha);
                    String caractereSenhaString = ""+caractereSenha;
                    if(caractereSenhaString.equals(usando[indiceAlfabeto])){
                        int indiceCaractereDescriptografado = indiceAlfabeto-chave;
                        if(indiceCaractereDescriptografado > 69){
                            indiceCaractereDescriptografado -= 70;
                        } else if(indiceCaractereDescriptografado < 0){
                            indiceCaractereDescriptografado += 70;
                        }
                        senhaPronta = senhaPronta+usando[indiceCaractereDescriptografado];
                        indiceSenha++;
                        numAlfabeto++;
                        indiceAlfabeto = 0;
                        
                     } else {
                        indiceAlfabeto++;
                     }
            }
        }            
        return senhaPronta;
    }
    
}
