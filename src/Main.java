/*
Exercicio 4

Victor Manuel
Marcos Paulo
Anderson Silva
 */

import javax.swing.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String GetString = JOptionPane.showInputDialog(TelaInicial());
        String string = GetString.toLowerCase();
        Boolean resultadoAB = VerificarAB(string);
        System.out.println("Checando dados - String: " + string + " resultadoAB: " + resultadoAB);
        if (!resultadoAB) {
            // ENTRA CASO NÂO ENCONTRE NENHUMA SEQUENCIA DE AB
            System.out.println("IF 1");
            JOptionPane.showMessageDialog(null, Erro(string));
        } else if (resultadoAB && string == "ab") {
            // ENTRA SE TIVER APENAS AB COMO STRING
            System.out.println("IF 2");
            JOptionPane.showMessageDialog(null, Sucesso(string));
        } else if (resultadoAB && string != "ab") {
            // ENTRA SE TIVER TIVER SEQUENCIA DE A E B
            System.out.println("IF 3");
            VerificarSequencia(string);
            if (VerificarSequencia(string)) {
                JOptionPane.showMessageDialog(null, Sucesso(string));
            } else {
                JOptionPane.showMessageDialog(null, Erro(string));
            }
        } else {
            // ERRO
            System.out.println("CASO 4");
        }
        System.exit(0);
    }

    public static boolean VerificarAB(String string) {
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

    public static boolean VerificarSequencia(String string) {
        boolean resultado = false;
        char[] charArr = string.toCharArray();
        ArrayList<String> ListaDeA = new ArrayList<String>();
        ArrayList<String> ListaDeB = new ArrayList<String>();
        boolean statusDeA = true;
        int index = 0;
        while (index < charArr.length) {
            char letra = charArr[index];
            if (letra == 'b') {
                statusDeA = false;
            }
            if (letra == 'a' && statusDeA) {
                ListaDeA.add(String.valueOf(letra));
            } else if (letra == 'b' && !statusDeA) {
                ListaDeB.add(String.valueOf(letra));
            } else if (letra == 'a' && !statusDeA) {
                break;
            } else {
                break;
            }
            index++;
        }
        if (VerificarTamanho(ListaDeA, ListaDeB)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean VerificarTamanho(ArrayList ListaDeA, ArrayList ListaDeB) {
        System.out.println("Lista de A: " + ListaDeA + ListaDeA.toArray().length + "Lista de B: " + ListaDeB + ListaDeB.toArray().length);
        return ListaDeA.toArray().length == ListaDeB.toArray().length;
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
        return UTF8toISO(exibicao);
    }

    public static String Erro(String string) {
        String exibicao = "";
        exibicao += "===============================\n";
        exibicao += " A palavra " + string + " não pertence à linguagem gerada pela gramática G.";
        exibicao += "\n===============================\n";
        return UTF8toISO(exibicao);
    }

    public static String Sucesso(String string) {
        String exibicao = "";
        exibicao += "===============================\n";
        exibicao += " A palavra " + string + " pertence à linguagem gerada pela gramática G.";
        exibicao += "\n===============================\n";
        return UTF8toISO(exibicao);
    }

    public static String UTF8toISO(String str) {
        Charset utf8charset = StandardCharsets.UTF_8;
        Charset iso88591charset = StandardCharsets.ISO_8859_1;

        ByteBuffer inputBuffer = ByteBuffer.wrap(str.getBytes());

        // decode UTF-8
        CharBuffer data = utf8charset.decode(inputBuffer);

        // encode ISO-8559-1
        ByteBuffer outputBuffer = iso88591charset.encode(data);
        byte[] outputData = outputBuffer.array();

        return new String(outputData);
    }
}