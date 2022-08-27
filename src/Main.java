/*
Exercicio 4

Victor Manuel
Marcos Paulo
Anderson Silva
 */

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String GetString = JOptionPane.showInputDialog(TelaInicial());
        String string = GetString.toLowerCase();
        Boolean resultadoAB = ChegarAB(string);
        System.out.println("Checando dados - String: " + string + " resultadoAB: " + resultadoAB);
        if (!resultadoAB) {
            // ENTRA CASO NÂO ENCONTRE NENHUMA SEQUENCIA DE AB
            System.out.println("CASO 1");
            JOptionPane.showMessageDialog(null, Erro(string));
        } else if (resultadoAB && string == "ab") {
            // ENTRA SE TIVER APENAS AB COMO STRING
            System.out.println("CASO 2");
            JOptionPane.showMessageDialog(null, Sucesso(string));
        } else if (resultadoAB && string != "ab") {
            // ENTRA SE TIVER TIVER SEQUENCIA DE A E B
            System.out.println("CASO 3");
            char[] charArr = string.toCharArray();
            int index = 0;
            while (index < charArr.length) {
                char letra = charArr[index];
                if (letra == 'a') {
                    index++;
                } else if (letra == 'b') {
                    if (letra == 'a') {
                        JOptionPane.showMessageDialog(null, Erro(string));
                        break;
                    }
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, Sucesso(string));
        } else {
            // ERRO
            System.out.println("CASO 4");
        }
        System.exit(0);
    }

    public static boolean ChegarAB(String string) {
        Pattern pattern = Pattern.compile("(ab)+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println("Match found");
            return true;
        } else {
            System.out.println("Match not found");
            return false;
        }
    }

    public static String TelaInicial() {
        String exibicao = "";
        exibicao += "===============================\n";
        exibicao += " A Gramatica: \n";
        exibicao += " G = <V, T, P, S> \n";
        exibicao += " V = {S, A, B} \n";
        exibicao += " T = {a,b} \n";
        exibicao += " P = { \n";
        exibicao += "     S -> ASB | AB \n";
        exibicao += "     A -> a \n";
        exibicao += "     B -> b \n";
        exibicao += " } ";
        exibicao += "\n===============================\n";
        exibicao += "Teste uma palavra com o alfabeto { a, b }: \n";
        return exibicao;
    }

    public static String Erro(String string) {
        String exibicao = "";
        exibicao += "===============================\n";
        exibicao += " A palavra " + string + " não pertence à linguagem gerada pela gramática G.";
        exibicao += "\n===============================\n";
        return exibicao;
    }

    public static String Sucesso(String string) {
        String exibicao = "";
        exibicao += "===============================\n";
        exibicao += " A palavra " + string + " pertence à linguagem gerada pela gramática G.";
        exibicao += "\n===============================\n";
        return exibicao;
    }
}