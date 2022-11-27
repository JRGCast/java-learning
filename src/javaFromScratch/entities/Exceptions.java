package javaFromScratch.entities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions {
  Scanner sc = new Scanner(System.in);

  public Exceptions() {
  }

  public void breakApp() {
    // veja que qualquer posição fora do comprimento do array irá quebrar o App
    String[] vect1 = sc.nextLine().split(" ");
    int posit = sc.nextInt();
    System.out.println(vect1[posit]);
    System.out.println("This message will not appear if app is broken");
  }

  public void tryCatched() {
    // aqui, porém, por estar dentro do bloco try/catch, a exceção é 'catched'
    try {
      String[] vect1 = sc.nextLine().split(" ");
      int posit = sc.nextInt();
      System.out.println(vect1[posit]);

      // é possível fazer múltiplos catchs para as exceções
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("captured error:" + e);
      e.printStackTrace(); // o stack trace serve para 'traçar o caminho' que ocasionou o erro, é aquele
                           // padrão que vemos de 'at CLASSX, line X em várias linhas'
    } catch (InputMismatchException e) {
      System.out.println("captured error: " + e);
      e.printStackTrace();
    } finally {
      System.out.println("this is always happen, with or without exceptions");
    }

    System.out.println("End of program: This message will not appear if app is broken");
  }
}
