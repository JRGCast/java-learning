package javaFromScratch;

import java.util.Locale;
import java.util.Scanner;

public class DataInput {

    public Scanner sc = new Scanner(System.in);

    public void userInput() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("DATA INPUT");
        Locale PTBR = new Locale("pt", "BR"); // não existe diretamente no locale o ptBR, ex.: Locale.BR, porém, é possível pegar os dados da linguagem assim
        Locale.setDefault(PTBR);
        System.out.println("Digite seu nome:\n");
        String inputName = sc.next();
        System.out.println("Digite sua idade:\n");
        Integer inpiutAge = sc.nextInt();
        System.out.println("Digite seu salário DE ACORDO COM A LOCALIDADE BR:\n");
        double inputSal = sc.nextDouble();
        System.out.printf("Seu nome é: %s, sua idade é %d e seu salário é %.2f%n ", inputName, inpiutAge, inputSal);
        System.out.println("Utilizando o next, ele lerá todo o dado que não esteja separado, ex:\n");
        System.out.println("Digite sua rua, nº sua casa e o preço da passagem de ônibus:\n");
        String inputStreet = sc.next();
        Byte inputHouse = sc.nextByte();
        Double inputBusPass = sc.nextDouble();
        System.out.println("Rua: " + inputStreet + " Casa: " + inputHouse + " Busão: " + inputBusPass);
        System.out.println("Já o nextLine lerá todo o dado da linha, isto é, tudo que escrever antes de digitar ENTER. Teste você mesmo:\n");
        String inputOne = sc.nextLine();
        String inputTwo = sc.nextLine();
        System.out.println(inputOne + "\n" + inputTwo + " " + "tenha atenção às quebras de linhas pendentes");
        sc.close();
    }
    public void theDoWhile() {
    String doOrStop;
    Integer numberToPrint;

    do {
        System.out.println("Qual número deseja imprimir?");
        numberToPrint = sc.nextInt();
        System.out.println("Você quis imprimir o número: " + numberToPrint);
        System.out.println("Continuar? Se quiser parar digite stop");
        doOrStop = sc.next();
    } while (!doOrStop.equals("stop"));
    sc.close();
    }
}
