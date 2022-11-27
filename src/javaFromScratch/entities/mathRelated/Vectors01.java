package javaFromScratch.entities.mathRelated;

import java.util.Scanner;

public class Vectors01 { // vetores é como chamam os arrays, e diferenciam listas de vetores
    Scanner sc = new Scanner(System.in);

    public void vectorArr01() {

        System.out.println("Qual será o tamanho do array?");

        int arrInit = sc.nextInt();

        double[] theVector = new double[arrInit]; // um array precisa começar com o seu tamanho já definido, semelhante
                                                  // ao GO

        for (int i = 0; i < arrInit; i += 1) {
            System.out.println("Insira o valor da altura " + i + ": ");
            theVector[i] = sc.nextDouble();
        }

        double theVectSum = 0.0;

        for (int i = 0; i < theVector.length; i++) {
            theVectSum += theVector[i];
        }

        System.out.printf("AVERAGE HEIGHT : %.2f", theVectSum / theVector.length);

        sc.close();
    }
}
