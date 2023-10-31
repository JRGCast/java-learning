package javaFromScratch.services;

import java.util.ArrayList;
import java.util.List;

// Ao parametrizar a classe, é possível ter o type safety para toda a classe,
// portanto, só será possível adicionar
public class ListHandler<T> {
  private List<T> list = new ArrayList<T>();

  public void addTolist(T t) {
    list.add(t);
  }

  public T getFirstNum() {
    if (list.isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    return list.get(0);
  }

  public void printThisList() {
    if (list.isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    for (T index : list) {
      System.out.println(index);
    }
  }

  public List<T> getList() {
    return list;
  }

  // este método funciona para qualquer tipo de lista,
  // porém paga-se o preço de não poder adicionar nada à listas do tipo
  // 'coringa'/wildcard
  public static void printAnyList(List<?> outerList) {
    for (Object index : outerList) {
      System.out.println(index);
    }
  }

  // este método funciona para qualquer tipo ou subtipo de lista do tipo Object
  // porém paga-se o preço de não poder adicionar nada à listas do tipo
  // 'coringa'/wildcard
  public static void printTypeNSubtypeList(List<? extends Object> outerList) {
    for (Object index : outerList) {
      System.out.println(index);
    }
  }

  // para visualizar os princípios GET/PUT, uninco co e contravariâncias
  public static void copyNumToSuperNum(List<? extends Number> source, List<? super Number> destiny) {
    for (Number src : source) {
      // ou seja, é possível apenas pegar os number da lista de origem, e é possível apenas adicionar à destiny
      destiny.add(src);
    }
    System.out.println("destiny list: " + destiny);
  }
}
