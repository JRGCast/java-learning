package javaFromScratch.utils;

public class HandlerException extends Exception {
  /*
   * poderia ser extends RuntimeException, a única diferença apontada na aula é
   * que o RuntimeException não obriga vc a tratar no compilador, outra boa fonte:
   * https://stackoverflow.com/questions/19857008/extending-exception-
   * runtimeexception-in-java
   */

  public HandlerException(String message) {
    /*
     * Aqui essa classe vai simplemente repassar uma string quando for instanciada
     */
    super(message);
  }

}
